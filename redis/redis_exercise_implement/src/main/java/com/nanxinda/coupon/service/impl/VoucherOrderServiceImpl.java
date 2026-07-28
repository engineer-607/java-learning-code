package com.nanxinda.coupon.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nanxinda.coupon.entity.SeckillVoucher;
import com.nanxinda.coupon.entity.VoucherOrder;
import com.nanxinda.coupon.mapper.VoucherMapper;
import com.nanxinda.coupon.mapper.VoucherOrderMapper;
import com.nanxinda.coupon.service.SeckillVoucherService;
import com.nanxinda.coupon.service.VoucherOrderService;
import com.nanxinda.coupon.service.VoucherService;
import com.nanxinda.coupon.util.RedisIdWorker;
import com.nanxinda.coupon.util.RedisLock;
import com.nanxinda.login.utils.UserHolder;
import com.nanxinda.system.dto.Result;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ReflectiveScan;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Slf4j
public class VoucherOrderServiceImpl extends ServiceImpl<VoucherOrderMapper, VoucherOrder> implements VoucherOrderService {
    @Resource
    SeckillVoucherService seckillVoucherService;

    @Resource
    RedisIdWorker redisIdWorker;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public Result seckillVoucher(Long voucherId) {
        //1.查询优惠券
        SeckillVoucher voucher = seckillVoucherService.getById(voucherId);
        //2.判断秒杀是否开始
        if(LocalDateTime.now().isBefore(voucher.getBeginTime())){
            return Result.fail("优惠券抢购尚未开始");
        }
        //3.判断秒杀是否已经结束
        if(LocalDateTime.now().isAfter(voucher.getEndTime())){
            return Result.fail("优惠券抢购已经结束");
        }
        //4.判断库存是否充足
        if(voucher.getStock()<1){
            return Result.fail("库存不足");
        }
        //确保一人一单
        Long userId = UserHolder.getUser().getId();
        RedisLock lock = new RedisLock(redisTemplate,"order:"+voucherId+":"+userId);
        //NOTE order:voucherId:userId可以避免一个用户同一时间只能下单一钟类型的优惠券
//        synchronized (userId.toString().intern()){
        boolean isLock = lock.tryLock(40L);
        if(!isLock){
            return Result.fail("该顾客只能下一单");
        }
        //NOTE 需要先获取锁，然后代理再执行事务，避免释放锁到提交事务的间隙其他线程
        // 拿到锁导致数据不一致
        // userId.toString() 每次可能产生不同的 String 对象，直接 synchronized 会导致锁对象不一致。
        // intern() 会返回字符串常量池中同内容的规范化 String 对象，
        // 因此同一个 JVM 内，相同 userId 能拿到同一把锁。
        // 缺点：如果是集群服务器这种锁就会失效，因为每台服务器都有自己的字符串池
        // 锁不再具有唯一性
        // 解决方案：采用分布式锁
        try {
            VoucherOrderService proxy = (VoucherOrderService) AopContext.currentProxy();
            //NOTE 获取代理对象，由代理对象开启事务
            return proxy.createVoucherOrder(voucherId);
        } catch (IllegalStateException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
//        }
    }
    @Transactional
    //NOTE Spring在创建VoucherOrderServiceImpl这个bean时，会检查
    // 该Bean的类和方法上是否存在能够匹配的AOP增强，比如@Transactional
    // 如果存在会自动为目标对象创建一个代理对象
    public Result createVoucherOrder(Long voucherId) {
        //确保一人一单
        Long userId = UserHolder.getUser().getId();
        Long count = lambdaQuery()
                .eq(VoucherOrder::getUserId, userId)
                .eq(VoucherOrder::getVoucherId, voucherId)
                .count();
        if (count != 0) {
            return Result.fail("该用户已经购买过一单！");
        }

        //5.扣减库存
        boolean update = seckillVoucherService.lambdaUpdate()
                .eq(SeckillVoucher::getVoucherId, voucherId)
                .setDecrBy(SeckillVoucher::getStock, 1)
//                .eq(SeckillVoucher::getStock,voucher.getStock())//where id = ? and stock = ? CAS
                .gt(SeckillVoucher::getStock, 0) // where id = ? and stock > 0 sql直接判断
                .update();
        if (!update) {
            return Result.fail("库存不足");
        }
        VoucherOrder voucherOrder = new VoucherOrder()
                .setId(redisIdWorker.nextId("order"))
                .setUserId(userId)
                .setCreateTime(LocalDateTime.now())
                .setVoucherId(voucherId);
        log.debug("订单详情：{}", voucherOrder);
        save(voucherOrder);
        //6.返回订单id
        return Result.ok(voucherOrder.getId());

    }
}


/*
 * 并发控制方案对比
 *
 * ┌──────────────┬──────────────────────────────┬──────────────────────────────┬──────────────────────────────┐
 * │ 方案         │ 优点                         │ 缺点                         │ 适用场景                     │
 * ├──────────────┼──────────────────────────────┼──────────────────────────────┼──────────────────────────────┤
 * │ 业务字段 CAS │ 1. 不需要额外字段            │ 1. 更新前通常需要先查询旧值  │ 1. 关注某个字段是否被修改    │
 * │              │ 2. 冲突检测粒度较小          │ 2. 可能出现 ABA 问题         │ 2. 状态流转                  │
 * │ WHERE        │ 3. 只关心指定业务字段        │ 3. 无法发现其他字段被修改    │ 3. 库存必须保持原值时更新    │
 * │ stock=旧值   │                              │ 4. 多字段更新时条件较复杂    │                              │
 * ├──────────────┼──────────────────────────────┼──────────────────────────────┼──────────────────────────────┤
 * │ 版本号       │ 1. 能发现整行数据发生变化    │ 1. 需要额外维护 version 字段 │ 1. 通用实体更新              │
 * │ 乐观锁       │ 2. 避免 ABA 问题             │ 2. 任意字段修改都会产生冲突  │ 2. 修改前需要读取完整对象    │
 * │              │ 3. 条件统一，通用性强        │ 3. 冲突后需要重试或报错      │ 3. 不能覆盖他人修改的场景    │
 * │ WHERE        │ 4. 框架容易统一封装          │ 4. 高冲突场景失败率较高      │ 4. 读多写少的业务            │
 * │ version=旧值 │                              │                              │                              │
 * ├──────────────┼──────────────────────────────┼──────────────────────────────┼──────────────────────────────┤
 * │ SQL直接判断  │ 1. 不需要先查询库存          │ 1. 只能处理能写入一条 SQL    │ 1. 秒杀库存扣减              │
 * │              │ 2. 一条 UPDATE 原子完成      │    的简单业务条件            │ 2. 账户余额条件扣减          │
 * │ SET stock    │    判断和修改                │ 2. 不能直接处理多表逻辑      │ 3. 订单状态单向流转          │
 * │ = stock - 1  │ 3. 代码简单、性能较好        │ 3. 更新 0 行时失败原因模糊   │ 4. 简单计数器扣减            │
 * │ WHERE        │ 4. 可以防止库存扣成负数      │ 4. 高并发下仍争抢热点行      │                              │
 * │ stock > 0    │ 5. 不会因库存正常变化而失败  │ 5. 不能单独保证一人一单      │                              │
 * ├──────────────┼──────────────────────────────┼──────────────────────────────┼──────────────────────────────┤
 * │ 悲观锁       │ 1. 查询后数据不会被别人修改  │ 1. 线程需要等待，吞吐量较低  │ 1. 写操作多、冲突概率高      │
 * │              │ 2. 适合复杂的读取、计算、更新│ 2. 锁持有时间过长影响性能    │ 2. 必须先查询再复杂计算      │
 * │ SELECT ...   │ 3. 冲突时等待，不用反复重试  │ 3. 使用不当可能产生死锁      │ 3. 转账、结算等强一致业务    │
 * │ FOR UPDATE   │ 4. 一致性控制较强            │ 4. 必须放在数据库事务中      │ 4. 无法接受乐观锁频繁失败    │
 * └──────────────┴──────────────────────────────┴──────────────────────────────┴──────────────────────────────┘
 *
 * 补充：
 *
 * 1. 版本号法本质上也是 CAS：
 *
 *    业务字段 CAS：
 *    UPDATE voucher
 *    SET stock = stock - 1
 *    WHERE id = ? AND stock = 旧库存;
 *
 *    版本号 CAS：
 *    UPDATE voucher
 *    SET stock = stock - 1,
 *        version = version + 1
 *    WHERE id = ? AND version = 旧版本;
 *
 * 2. 秒杀扣库存通常优先使用：
 *
 *    UPDATE voucher
 *    SET stock = stock - 1
 *    WHERE id = ? AND stock > 0;
 *
 *    原因：业务只要求“当前还有库存就扣减”，
 *    不要求库存必须等于之前查询到的旧值。
 *
 * 3. 简单选择：
 *
 *    只需防止库存为负       → SQL 直接判断
 *    检查某个字段是否改变   → 业务字段 CAS
 *    检查整行数据是否改变   → 版本号乐观锁
 *    复杂处理期间禁止别人改 → 悲观锁
 */

/*
 * 分布式锁的实现
 *
 * 核心作用：
 * 分布式锁用于实现多个进程、多个服务实例之间的互斥，
 * 保证同一时刻只有一个进程能够执行临界区代码。
 *
 * ┌───────────┬──────────────────────────────┬────────┬────────┬──────────────────────────────┐
 * │ 实现方式  │ 互斥原理                     │ 高可用 │ 高性能 │ 安全性                       │
 * ├───────────┼──────────────────────────────┼────────┼────────┼──────────────────────────────┤
 * │ MySQL     │ 利用 MySQL 自身的互斥锁机制  │ 好     │ 一般   │ 连接断开后自动释放锁         │
 * ├───────────┼──────────────────────────────┼────────┼────────┼──────────────────────────────┤
 * │ Redis     │ 利用 SETNX 等互斥命令实现    │ 好     │ 好     │ 设置锁的超时时间，到期释放   │
 * ├───────────┼──────────────────────────────┼────────┼────────┼──────────────────────────────┤
 * │ ZooKeeper │ 利用节点的唯一性和有序性实现 │ 好     │ 一般   │ 使用临时节点，断开后自动删除 │
 * └───────────┴──────────────────────────────┴────────┴────────┴──────────────────────────────┘
 *
 * 1. MySQL 分布式锁
 *
 *    互斥方式：
 *    利用 MySQL 数据库自身的锁机制实现互斥。
 *
 *    优点：
 *    - 高可用性较好。
 *    - 客户端连接断开后，数据库可以自动释放锁。
 *
 *    缺点：
 *    - 数据库操作需要进行网络通信和磁盘操作。
 *    - 性能相对一般，不适合特别高并发的锁竞争场景。
 *
 *
 * 2. Redis 分布式锁
 *
 *    互斥方式：
 *    利用 SETNX 等具有互斥效果的 Redis 命令实现。
 *
 *    优点：
 *    - 高可用性较好。
 *    - 基于内存操作，性能较高。
 *
 *    安全措施：
 *    - 给锁设置超时时间。
 *    - 即使程序发生异常，没有主动释放锁，
 *      锁也会在到期后自动释放，避免死锁。
 *
 *
 * 3. ZooKeeper 分布式锁
 *
 *    互斥方式：
 *    利用 ZooKeeper 节点的唯一性和有序性实现互斥。
 *
 *    优点：
 *    - 高可用性较好。
 *    - 可以通过临时节点避免锁长期无法释放。
 *
 *    缺点：
 *    - 创建、删除节点等操作性能相对一般。
 *
 *    安全措施：
 *    - 使用临时节点保存锁。
 *    - 客户端断开连接后，临时节点会自动删除，
 *      从而自动释放锁。
 *
 *
 * 简单对比：
 *
 * MySQL：
 * 实现简单，但性能一般。
 *
 * Redis：
 * 性能较高，常用于高并发场景，需要正确设置锁的超时时间。
 *
 * ZooKeeper：
 * 临时节点机制更适合保证锁的安全释放，但性能一般。
 */