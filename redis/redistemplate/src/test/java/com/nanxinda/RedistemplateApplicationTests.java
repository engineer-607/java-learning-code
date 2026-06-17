package com.nanxinda;

import com.nanxinda.redis.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import tools.jackson.databind.ObjectMapper;

import java.util.Map;

@SpringBootTest
class RedistemplateApplicationTests {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Test
    void testString(){
        //NOTE SpringDataRedis 中 RedisTemplate 常用 API
        // redisTemplate.opsForValue() -> ValueOperations：操作 String 类型数据
        // redisTemplate.opsForHash()  -> HashOperations ：操作 Hash 类型数据
        // redisTemplate.opsForList()  -> ListOperations ：操作 List 类型数据
        // redisTemplate.opsForSet()   -> SetOperations  ：操作 Set 类型数据
        // redisTemplate.opsForZSet()  -> ZSetOperations ：操作 SortedSet 类型数据
        // redisTemplate               -> 通用 Redis 命令

        //插入一条string类型
        redisTemplate.opsForValue().set("name","李四");
        //NOTE 会将name这个字符串jdk序列化为String对象（ObjectOutputStream）
        //public class DefaultSerializer implements Serializer<Object> {
        //	@Override
        //	public void serialize(Object object, OutputStream outputStream) throws IOException {
        //		if (!(object instanceof Serializable)) {
        //			throw new IllegalArgumentException(getClass().getSimpleName() + " requires a Serializable payload " +
        //					"but received an object of type [" + object.getClass().getName() + "]");
        //		}
        //		ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        //		objectOutputStream.writeObject(object);
        //		objectOutputStream.flush();
        //	}
        //}

        //读取一条string类型数据
        Object name = redisTemplate.opsForValue().get("name");
        System.out.println("name="+name);
    }
    @Test
    void testSaveUser(){
        redisTemplate.opsForValue().set("nanxinda:user:2",new User("jack",19));
        User user = (User)redisTemplate.opsForValue().get("nanxinda:user:2");
        System.out.println("user="+user);
    }
    //NOTE 序列化和反序列化包含类的信息，会带来额外的内存开销
    // 解决思路：
    // 统一使用String序列化器，要求只能存储String类型的key和value
    // 当需要存储java对象时，手动完成对象的序列化和反序列化
    // 具体方案：
    // 1.Spring提供了StringRedisTemplate类，其key和value序列化方式默认是
    // String方式，省去自定义RedisTemplate过程
    // 2.使用ObjectMapper JSON序列化轮子，序列化时可以提取对象的属性转成字符串型json
    // 反序列化时可以将字符串型json转为对象
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    // JSON工具
    private static final ObjectMapper mapper = new ObjectMapper();
    @Test
    void testStringTemplate() {
        User user = new User("李四",18);
        //手动序列化
        String json = mapper.writeValueAsString(user);
        //写入一条数据到redis
        stringRedisTemplate.opsForValue().set("nanxinda:user:3",json);
        //读取数据
        String val = stringRedisTemplate.opsForValue().get("nanxinda:user:3");
        //反序列化
        User user1 = mapper.readValue(val,User.class);
        System.out.println("user = "+user1);
    }
    @Test
    void testHash(){
        stringRedisTemplate.opsForHash().put("nanxinda:user:3","name","李四");
        stringRedisTemplate.opsForHash().put("nanxinda:user:3","age","18");
        Map<Object,Object> entries =redisTemplate.opsForHash().entries("nanxinda:user:2");
        System.out.println(entries);


    }





}
