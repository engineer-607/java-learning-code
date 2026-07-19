package com.nanxinda.system.utils;

import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.nanxinda.system.dto.RedisData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static com.nanxinda.system.utils.RedisConstants.CACHE_NULL_TTL;

@Component
@Slf4j
public class CacheClient{
    @Autowired
    private StringRedisTemplate redisTemplate;
    //将任意Java对象序列化为json并存储在String类型的key中，并且可以设置TTL过期时间
    public void set(String key, Object value, Long time, TimeUnit unit){
        String jsonStr = JSONUtil.toJsonStr(value);
        redisTemplate.opsForValue().set(key,jsonStr,time,unit);
    }
    //将任意Java对象序列话为json并存储在String类型的key中，并且可以设置逻辑过期时间，
    //用于储存缓存击穿问题
    public void setWithLogicalExpire(String key,Object value,Long time,TimeUnit unit){
        RedisData redisData = new RedisData()
                .setData(value)
                .setExpireTime(LocalDateTime.now().plusSeconds(unit.toSeconds(time)));
        String jsonStr = JSONUtil.toJsonStr(redisData);
        redisTemplate.opsForValue().set(key,jsonStr);
    }

    //根据指定的key查询缓存，并反序列化为指定类型，利用缓存空值的方式解决缓存穿透问题
    public <R,ID> R queryWithPassTrough(String keyPreFix, ID id, Class<R> type, Function<ID,R> function){
        String string = redisTemplate.opsForValue().get(keyPreFix + id);
        if(StrUtil.isNotBlank(string)) {
            return JSONUtil.toBean(string, type);
        }
        if(string!=null){
            return null;
        }
        R apply = function.apply(id);
        if(apply==null) {
            set(keyPreFix+id,apply,CACHE_NULL_TTL,TimeUnit.MINUTES);
            return apply;
        }
        set(keyPreFix+id,apply,RedisConstants.CACHE_SHOP_TTL,TimeUnit.MINUTES);

        return apply;
    }

    private static final ExecutorService CACHE_REBUILD_EXECUTOR = Executors.newFixedThreadPool(10);
    //根据指定的key查询缓存，并反序列化为指定类型，需要利用逻辑过期解决缓存击穿问题
    public <R,ID> R queryWithExpireTime(String keyPreFix,String lockPreFix,ID id,Class<R> type,Function<ID,R> function, Long time, TimeUnit unit){
        //        1. 查询 Redis 缓存
        String string = redisTemplate.opsForValue().get(keyPreFix + id);
        //        2. 如果缓存不存在，直接返回空
        if(StrUtil.isBlank(string)){
            return null;
        }
        //        3. 如果缓存存在，判断逻辑过期时间
        RedisData redisData = JSONUtil.toBean(string, RedisData.class);
        LocalDateTime expireTime = redisData.getExpireTime();
        if(LocalDateTime.now().isAfter(expireTime)){
            boolean isLock = tryLock(lockPreFix+id);
            if(isLock) {
                CACHE_REBUILD_EXECUTOR.submit(() -> {
                    try {
                        R apply = function.apply(id);
                        setWithLogicalExpire(keyPreFix+id,apply,time,unit);
                    } catch (Exception e) {
                        log.debug("id{}写入缓存是被",id);
                    } finally {
                        unlock(lockPreFix+id);
                    }
                });
            }
        }
        //        4. 如果没有过期，直接返回缓存数据
        //        5. 如果已经过期，尝试获取互斥锁
        //            6.2 当前线程直接返回旧数据
        //             7. 获取锁失败：
        //             7.1 直接返回旧数据
        return JSONUtil.toBean((JSONObject) redisData.getData(),type);
    }
    private boolean tryLock(String key){
        Boolean b = redisTemplate.opsForValue().setIfAbsent(key, "1", 30, TimeUnit.SECONDS);
        //可能会存在空指针的问题，用工具类将True为true，其他都返回false
        return BooleanUtil.isTrue(b);
    }

    private void unlock(String key){
        redisTemplate.opsForValue().getAndDelete(key);
    }
}
