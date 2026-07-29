package com.nanxinda.coupon.util;

import cn.hutool.core.util.BooleanUtil;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.data.redis.core.types.Expiration;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

public class RedisLock implements Lock{

    private StringRedisTemplate redisTemplate;

    private String name;

    private static String KEY_PREFIX = "lock:";

    private static final String VALUE_PREFIX = UUID.randomUUID() + "-";
    //NOTE 使用UUID算法随机生成前缀，尽量保持锁value唯一，避免因集群服务器各自JVM内出现线程id相同的冲突而导致
    // 属于线程自己的锁被其他阻塞致使锁过期的线程误删（线程id在自己的JVM中自增，UUID重复出现
    // 概率极低可以忽略不计）

    private static DefaultRedisScript redisScript;
    static {
        redisScript = new DefaultRedisScript<>();
        redisScript.setLocation(new ClassPathResource("unlock.lua"));
    }
    public RedisLock(StringRedisTemplate redisTemplate, String name) {
        this.redisTemplate = redisTemplate;
        this.name = name;
    }

    @Override
    public boolean tryLock(Long timeouts) {
        Boolean b = redisTemplate.opsForValue()
                .setIfAbsent(KEY_PREFIX + name,VALUE_PREFIX + Thread.currentThread().getId(), Expiration.seconds(timeouts));
        return BooleanUtil.isTrue(b);
    }

    @Override
    public void unlock() {
        redisTemplate.execute(
                redisScript,
                Collections.singletonList(KEY_PREFIX + name),
                VALUE_PREFIX + Thread.currentThread().getId()
        );
    }


//    @Override
//    public void unlock() {
//        String threadId = VALUE_PREFIX + Thread.currentThread().getId();
//        if(threadId.equals(redisTemplate.opsForValue().get(KEY_PREFIX + name))) {
//            //NOTE 不能将VALUE_PREFIX + Thread.currentThread().getId()定义为一个额外的属性，
//            // 因为如果属性值是属于当前对象的，所以当前对象不对属性值修改不会受外界影响，但是redis真实值
//            // 可能已经发生变化了，比如线程A发生阻塞，锁失效，线程B获取锁，如果这个时候线程A到了释放锁
//            // 这步逻辑用的是属性和当前线程id进行比较就一定会把锁删掉
//            redisTemplate.delete(KEY_PREFIX + name);
//        }
//    }
}
