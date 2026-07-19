package com.nanxinda.shop.service.impl;

import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.repository.AbstractRepository;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nanxinda.shop.entity.Shop;
import com.nanxinda.shop.mapper.ShopMapper;
import com.nanxinda.shop.service.ShopService;
import com.nanxinda.system.dto.RedisData;
import com.nanxinda.system.dto.Result;
import com.nanxinda.system.utils.CacheClient;
import com.nanxinda.system.utils.RedisConstants;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.nanxinda.system.utils.RedisConstants.*;

@Slf4j
@Service
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop> implements ShopService {
    @Autowired
    StringRedisTemplate redisTemplate;
    @Autowired
    CacheClient cacheClient;

    public Result queryById(Long id){
        if(isHotShop(id)){
            //是热门门店，采用逻辑过期处理缓存过期的问题
            Shop shop = cacheClient.queryWithExpireTime(CACHE_HOT_SHOP_KEY, LOCK_SHOP_KEY
                    , id, Shop.class, this::getById,
                    30L, TimeUnit.MINUTES);
            if(shop==null){
                return Result.fail("店铺不存在");
            }
            return Result.ok(shop);
        }
        //如果不是热门门店，采用空值回填方式应对缓存击穿问题
        Shop shop = cacheClient.queryWithPassTrough(CACHE_SHOP_KEY, id, Shop.class, this::getById);
        if(shop==null){
            return Result.ok("店铺不存在");
        }
        return Result.ok(shop);
    }
    private boolean isHotShop(Long id){
        Boolean isMember = redisTemplate.opsForSet().isMember(CACHE_HOT_SHOP_IDS,String.valueOf(id));
        return BooleanUtil.isTrue(isMember);
    }

    @Override
    public Result queryByIdThroughLock(Long id) {
        //1.在redis中根据id查询商铺
        String shopString = redisTemplate.opsForValue().get(CACHE_SHOP_KEY+id);
        //2.如果存在，直接返回
        if(StrUtil.isNotBlank(shopString)){
            Shop shop = JSONUtil.toBean(shopString,Shop.class);
            return Result.ok(shop);
        }
        if(shopString!=null){
            return Result.fail("店铺不存在");
        }
        //3.如果缓存不存在，尝试获取锁（解决缓存击穿问题）
        //3.1获取失败，休眠一会，在去查询
        String lockKey = LOCK_SHOP_KEY + id;
        boolean locked = false;
        //NOTE 注意：需要一个额外变量来记录当前线程是否拿到锁
        // 因为如果一个线程没有拿到锁，但是报错来到finally会把已经拿到
        // 锁线程的锁直接删除掉，所以需要额外变量来记录当前线程是否拿到锁
        try {
            locked = tryLock(lockKey);
            if(!locked){
                Thread.sleep(30);
                return queryByIdThroughLock(id);
            }
            //NOTE 3.2 为了避免自己在等待锁/获取锁的过程中
            // 别的线程已经将缓存建立好，需要对缓存进行二次检查（价值：1.在等待锁/获取锁的过程
            // 可能会因为redis网络请求延迟、线程被CPU调度暂停等原因而导致时间很长
            // 所以完全有可能这段时间内已经建立好索引 2.如果不检查将会再查一次数据库、多序列化一次
            // 对象、多写一次redis，在高并发的情况下可能就要多次重复建立缓存）
            shopString = redisTemplate.opsForValue().get(CACHE_SHOP_KEY+id);
            if(StrUtil.isNotBlank(shopString)){
                Shop shop = JSONUtil.toBean(shopString,Shop.class);
                return Result.ok(shop);
            }

            //3.3如果再检查redis依旧没有建好，去mysql数据库查询id
            Shop shop = getById(id);
            log.debug("shop:{}",shop);
            //4.如果数据库不存在，返回错误信息
            if(shop==null){
                redisTemplate.opsForValue().set(CACHE_SHOP_KEY+id,"",1L,TimeUnit.MINUTES);
                return Result.fail("店铺不存在");
            }

            //5.如果数据库存在，存入redis，并返回信息和释放锁
            String shopJson = JSONUtil.toJsonStr(shop);
            redisTemplate.opsForValue().set(CACHE_SHOP_KEY+id,shopJson,LOGIN_SHOP_TTL, TimeUnit.MINUTES);
            return Result.ok(shop);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            if(locked) {
                unlock(lockKey);
            }
        }
    }

    private boolean tryLock(String key){
        Boolean b = redisTemplate.opsForValue().setIfAbsent(key, "1", 30, TimeUnit.SECONDS);
        //可能会存在空指针的问题，用工具类将True为true，其他都返回false
        return BooleanUtil.isTrue(b);
    }

    private void unlock(String key){
        redisTemplate.opsForValue().getAndDelete(key);
    }

    @Override
    @Transactional
    //将整个操作视为一个事务，操作失败进行回滚
    public Result update(Shop shop) {
        if(shop.getId()==null){
            return Result.fail("店铺id不能为空");
        }
        //先跟新数据库，再删除缓存
        boolean b = updateById(shop);
        if (!b) {
            return Result.fail("添加失败！");
        }
        redisTemplate.opsForValue().getAndDelete(CACHE_SHOP_KEY + shop.getId());
        return Result.ok();
    }

    private static final ExecutorService CACHE_REBUILD_EXECUTOR = Executors.newFixedThreadPool(10);
    @Override
    public Result queryByIdThroughExpireTime(Long id) {
//        1. 查询 Redis 缓存
        String string = redisTemplate.opsForValue().get(CACHE_SHOP_KEY + id);
//        2. 如果缓存不存在，直接返回空
        if(StrUtil.isBlank(string)){
            return Result.fail("商店信息不存在");
        }
//        3. 如果缓存存在，判断逻辑过期时间
        RedisData redisData = JSONUtil.toBean(string, RedisData.class);
        LocalDateTime expireTime = redisData.getExpireTime();
        log.debug("redisData:{}",redisData);
        if(expireTime.isAfter(LocalDateTime.now())) {
//        4. 如果没有过期，直接返回缓存数据
            return Result.ok(redisData.getData());
        }else {
//            5. 如果已经过期，尝试获取互斥锁
            if(tryLock(LOCK_SHOP_KEY+id)){
                //        6. 获取锁成功：
//                UpdateData data = new UpdateData()
//                        .setExpireTime(500L)
//                        .setShopId(id);
//                data.start();
                CACHE_REBUILD_EXECUTOR.submit(()->{
                    //NOTE 需要异常捕获来处理额外线程
                    // 1.如果执行更新数据出错，而又没有异常捕获，锁就无法释放
                    // 虽然锁设置有TTL，但在这段生存周期中缓存无法更新
                    // 2.额外线程出错也没有办法反应到前端，所以需要catch中日志打印
                    try {
                        expireTimeToRedis(id,500L);
                    } catch (Exception e) {
                        log.debug("缓存重建失败，shopId:{}",id,e);
                    }finally {
                        unlock(LOCK_SHOP_KEY+id);
                    }
                });
            }
//            6.2 当前线程直接返回旧数据
//             7. 获取锁失败：
//             7.1 直接返回旧数据
            return Result.ok(redisData.getData());
        }
    }

    //本地方法将逻辑过期时间写入redis(准备工作)
    public void expireTimeToRedis(Long id, Long seconds){
        //1.从数据库中根据id获取商店信息
        Shop shop = getById(id);
        //2.将商店信息以及过期时间存入redis中
        RedisData redisData = new RedisData()
                .setExpireTime(LocalDateTime.now().plusSeconds(seconds))
                .setData(shop);
        log.debug("redisData:{}",redisData);
        redisTemplate.opsForValue().set(CACHE_SHOP_KEY+id,JSONUtil.toJsonStr(redisData));
    }
//    @Data
//    @Accessors(chain = true)
//    private class UpdateData extends Thread{
//        private Long shopId;
//        private Long expireTime;
//        public void run(){
//             6.1 开启新线程重建缓存
//             6.2 当前线程直接返回旧数据
//             6.3 新线程查询数据库
//             6.4 新线程写入 Redis，并更新逻辑过期时间
//            Shop shop = getById(shopId);
//            RedisData redisData = new RedisData()
//                    .setExpireTime(LocalDateTime.now().plusSeconds(expireTime))
//                    .setData(shop);
//            redisTemplate.opsForValue().set(CACHE_SHOP_KEY+ shopId,JSONUtil.toJsonStr(redisData));
//            6.5 新线程释放锁
//            unlock(LOCK_SHOP_KEY+ shopId);
//            log.debug("redisData:{}",redisData);
//
//
//        }
//    }
    //NOTE 缓存重建在高并发的情况下可能会频繁触发，如果每次都是执行自定义线程会有性能问题：
    // 1.线程创建成本高，每次new Thread都要向操作系统申请线程资源，分配线程栈内存，创建和销毁
    // 线程都有成本，但是线程池回复用已有线程
    // 2.没有数量限制：虽然有锁一个商铺id通常只对应一个线程重建，但是如果多个id同时过期，就会
    // 可能瞬间需要创建大量线程，线程太多导致CPU需要频繁上下文切换，甚至内存耗尽
    // 3.不好管理任务：线程池可以控制核心线程数、最大线程数、队列长度、拒绝策略
    // 4.异常和生命周期不好处理：自定义线程异常容易丢失，也不好统一监控和关闭线程

}
//NOTE
// 缓存穿透：用户请求的数据在缓存和数据库中都不存在，但是不断发送请求导致
// 给数据库造成很大的压力
// 解决方案：
// 被动：    1.缓存null值：如果数据库查询不到该值，redis中缓存对应的空值
//             优点：实现简单，方便维护
//             缺点：额外占用redis内存、短期可能会存在数据不一致的问题
//           2.布隆过滤：在请求redis服务前，过滤器先判断数据是否存在
//             优点：占用内存少，在访问redis和数据库之前就拦截非法请求
//             缺点：存在误判的可能
// 主动：
//           1.增加id的复杂度，避免id被猜测的概率
//           2.做好数据基础格式校验
//           3.加强用户权限校验
//           4.做好热点参数的限流

//NOTE 缓存雪崩
// 缓存雪崩是指在同一时段大量的缓存key同时失效或者redis服务宕机，导致大量请求到达数据库，带来巨大压力
// 解决方案：
// 给不同的key的TTL添加随机值
// 利用Redis的集群提高服务的可用性
// 给缓存业务添加降级限流策略
// 给业务添加多级缓存

//NOTE 缓存击穿
// 一个高并发并且缓存重建业务比较复杂的key突然失效，无数的请求访问会
// 在瞬间给数据库带来巨大的冲击
// 两种方案：
//           1.互斥锁：   优点：没有额外的内存消耗、保证一致性、实现简单
//                        缺点：线程需要等待，性能受影响、可能有死锁风险
//           具体实现：
//                        1. 查询 Redis 缓存
//                        2. 如果缓存命中，直接返回
//                        3. 如果缓存未命中，尝试获取互斥锁
//                        4. 获取锁成功：
//                             *      4.1 查询数据库
//                             4.2 将数据写入 Redis
//                             4.3 释放锁
//                             4.4 返回数据
//                        5. 获取锁失败：
//                             *      5.1 休眠一会儿
//                             5.2 重新查询 Redis
//           2.逻辑过期： 优点：线程无需等待，性能较好
//                        缺点：不保证一致性、有额外内存消耗、实现复杂
//           具体实现：
//                        1. 查询 Redis 缓存
//                        2. 如果缓存不存在，直接返回空
//                        3. 如果缓存存在，判断逻辑过期时间
//                        4. 如果没有过期，直接返回缓存数据
//                        5. 如果已经过期，尝试获取互斥锁
//                        6. 获取锁成功：
//                             6.1 开启新线程重建缓存
//                             6.2 当前线程直接返回旧数据
//                             6.3 新线程查询数据库
//                             6.4 新线程写入 Redis，并更新逻辑过期时间
//                             6.5 新线程释放锁
//                        7. 获取锁失败：
//                             7.1 直接返回旧数据

