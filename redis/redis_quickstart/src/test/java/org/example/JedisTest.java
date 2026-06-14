package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import redis.clients.jedis.Jedis;

import java.util.Map;

class JedisTest {
    private Jedis jedis;
    @BeforeEach
    void setUp(){
        //NOTE 2.连接本地6380，但是实际上会通过SSH隧道连接到服务器Redis
        jedis = new Jedis("127.0.0.1",6380);
        jedis.auth("123321");
        jedis.select(0);
    }
    @Test
    void testString(){
        //插入数据，方法名称就是redis命令名称
        String result = jedis.set("name","张三");
        System.out.println("result="+result);
        //获取数据
        String name = jedis.get("name");
        System.out.println("name="+name);
    }
    @Test
    void testHash(){
        //插入hash数据
        jedis.hset("nanxinda:user;1","name","jack");
        jedis.hset("nanxinda:user;1","age","19");
        //获取数据
        Map<String, String> stringStringMap = jedis.hgetAll("nanxinda:user;1");
        System.out.println(stringStringMap);
    }
    @AfterEach
    void tearDown(){
        if(jedis!=null){
            jedis.close();
        }
    }
}
