package com.nanxinda.coupon.util;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@Component
public class RedisIdWorker {
    private StringRedisTemplate redisTemplate;
    /**
     * 开始时间戳
     */
    private final static long BEGIN_SECONDS = 1767225600L;
    /**
     * 移动比特位
     */
    private final static int MOVE_BITS = 32;

    public RedisIdWorker(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /*
    * 全局 ID 生成器（64 bit）
    *
    * 为提高 ID 安全性，不直接使用 Redis 自增值，
    * 而是将时间戳与 Redis 序列号拼接成一个 long 类型 ID。
    *
    * ID 结构：
    * 1 bit  符号位：固定为 0，保证 ID 为正数
    * 31 bit 时间戳：以秒为单位，最多可使用约 69 年
    * 32 bit 序列号：秒内计数器，每秒最多生成 2^32 个不同 ID
    *
    * 最终 ID：
    * 时间戳左移 32 位，再与序列号进行按位或运算。
    *
    * long id = (timestamp << 32) | sequence;
    */

    public long nextId(String keyPreFix){
        //1.生成时间戳
        LocalDateTime time = LocalDateTime.now();
        long epochSecond = time.toEpochSecond(ZoneOffset.UTC);
        long timeSeconds = BEGIN_SECONDS - epochSecond;

        //2.生成序列号
        //2.1获取当前日期，精确到天
        String format = time.format(DateTimeFormatter.ofPattern("yyyy:MM:dd"));
        //2.2自增长
        long increment = redisTemplate.opsForValue().increment("icr:" + keyPreFix + ":" + format);
        //NOTE increment() 对应一条原子命令，这条命令要么完整执行、要么不执行，中途不能被其他操作
        // 插入
        return timeSeconds<<MOVE_BITS|increment;
    }
}
