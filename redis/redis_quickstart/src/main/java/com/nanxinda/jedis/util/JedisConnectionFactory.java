package com.nanxinda.jedis.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;


public class JedisConnectionFactory {
    //NOTE Jedis本身是线程不安全的，并且频繁地创建和销毁连接会有性能损耗
    // 解决方案：Jedis连接池
    private static final JedisPool jedisPool;

    static {

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        // 最大连接
        jedisPoolConfig.setMaxTotal(8);
        // 最大空闲连接
        jedisPoolConfig.setMaxIdle(8);
        // 最小空闲连接
        jedisPoolConfig.setMinIdle(0);
        // 设置最长等待时间，ms
        jedisPoolConfig.setMaxWait(Duration.ofMillis(2000));
        jedisPool = new JedisPool(jedisPoolConfig,"127.0.0.1",6379,1000,"123321");

    }

    public static Jedis getJedis(){
        return jedisPool.getResource();
    }
}
