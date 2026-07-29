package com.nanxinda;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import org.springframework.data.redis.core.types.Expiration;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RedisLockLuaTest {
    private static final String OWNER_A = "owner-A";
    private static final String OWNER_B = "owner-B";
    private static final String LOCK_KEY = "order:lock";

    @Autowired
    private StringRedisTemplate redisTemplate;

    private static DefaultRedisScript<Long> redisScript;
    static {
        redisScript = new DefaultRedisScript<>();
        redisScript.setLocation(new ClassPathResource("unlock.lua"));
        redisScript.setResultType(Long.class);
    }
    //模拟演示旧有的
    //bug get阻塞导致锁超时释放并且被其他线程获取，阻塞停止执行删除操作
    // 致使其他线程锁被误删（本质：获取和删除不具备原子性）
    @Test
    public void demonstrateOldBug() throws InterruptedException {
        redisTemplate.opsForValue().set(LOCK_KEY,OWNER_A,Expiration.seconds(20));
        String value = redisTemplate.opsForValue().get(LOCK_KEY);
        Thread.sleep(400L);
        redisTemplate.opsForValue().set(LOCK_KEY,OWNER_B,Expiration.seconds(40));
        if(OWNER_A.equals(value)) {
            redisTemplate.delete(LOCK_KEY);
        }
        assertNotEquals(Boolean.TRUE,redisTemplate.hasKey(LOCK_KEY));
    }


    //测试线程A能够正常删除自己的锁
    @Test
    public void ownerShouldReleaseItsOwnerLock(){
        redisTemplate.opsForValue().set(LOCK_KEY,OWNER_A, Expiration.seconds(40));
        Long result = redisTemplate.execute(
                redisScript,
                Collections.singletonList(LOCK_KEY),
                OWNER_A);
        assertEquals(Long.valueOf(1L),result);
        assertNotEquals(Boolean.TRUE, redisTemplate.hasKey(LOCK_KEY));
    }
    //测试线程A无法删除线程B的锁
    @Test
    public void noOwnerShouldNotReleaseAnotherLock(){
        redisTemplate.opsForValue().set(LOCK_KEY,OWNER_B,Expiration.seconds(40));
        Long result = redisTemplate.execute(
                redisScript,
                Collections.singletonList(LOCK_KEY),
                OWNER_A);
        assertEquals(Long.valueOf(0L),result);
        assertEquals(Boolean.TRUE, redisTemplate.hasKey(LOCK_KEY));
    }
    //测试线程A阻塞超时释放自己的锁，线程B已经获取该锁，但线程A尝试删除线程B的锁
    @Test
    public void expiredOwnerShouldNotDeleteAnotherOwnerLock() throws InterruptedException {
        redisTemplate.opsForValue().set(LOCK_KEY,OWNER_A,Expiration.seconds(20));
        Thread.sleep(400L);
        redisTemplate.opsForValue().set(LOCK_KEY,OWNER_B,Expiration.seconds(40));
        Long result = redisTemplate.execute(
                redisScript,
                Collections.singletonList(LOCK_KEY),
                OWNER_A);
        assertEquals(Long.valueOf(0L),result);
        assertEquals(Boolean.TRUE, redisTemplate.hasKey(LOCK_KEY));
    }


}
