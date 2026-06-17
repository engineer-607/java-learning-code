package com.nanxinda.redis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        //创建Template
        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
        //设置连接工厂
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        //设置序列化工具
        RedisSerializer<Object> jsonRedisSerializer = RedisSerializer.json();
        //NOTE RedisSerializer.json()会在json的value中记录对象的类，反序列化时
        // 不再使用而是默认类型LinkedHashSet，而是转化成原来的类型com.nanxinda.redis.pojo.User
        //key和hashKey采用string序列化
        redisTemplate.setKeySerializer(RedisSerializer.string());
        redisTemplate.setHashKeySerializer(RedisSerializer.string());
        //value和hashValue采用json序列化
        redisTemplate.setValueSerializer(jsonRedisSerializer);
        redisTemplate.setHashValueSerializer(jsonRedisSerializer);
        return redisTemplate;

        //NOTE RedisTemplate不进行配置默认采用JDK序列化，在把字符串对象变为二进制的时候
        // 最后的二进制数组前面会保留很多协议头（也就是\xac\xed\x00\x05所对应的二进制）
        // 而自定义RedisTemplate Bean采用RedisSerializer.string()
        // 就是String序列化，会把String对象当作普通文本内容，直接以UTF-8的编码方式存入redis服务器内
        // 即String内容 -> UTF-8 byte[]
        // 如果采用的是jsonRedisSerializer 就是json序列化，适合于自定义对象（User...)，转化
        // 方式：SON 序列化是：Java对象 -> JSON文本 -> UTF-8 byte[]
    }

}
