package com.nanxinda;

import com.nanxinda.coupon.util.RedisIdWorker;
import com.nanxinda.shop.entity.Shop;
import com.nanxinda.shop.service.impl.ShopServiceImpl;
import com.nanxinda.system.utils.CacheClient;
import com.nanxinda.system.utils.RedisConstants;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.nanxinda.system.utils.RedisConstants.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class RedisExerciseImplementApplicationTests {
    @Autowired
    CacheClient cacheClient;
    @Autowired
    StringRedisTemplate redisTemplate;
    @Autowired
    ShopServiceImpl shopService;
    @Resource
    RedisIdWorker redisIdWorker;

    private ExecutorService es = Executors.newFixedThreadPool(300);


    @Test
    public void testHotIds(){
        Long[] hotIds = {1L, 2L, 3L, 4L, 5L, 6L};
        Long[] normalIds = {7L, 8L, 9L, 10L, 11L, 12L, 13L, 14L};

        redisTemplate.delete(CACHE_HOT_SHOP_IDS);
        redisTemplate.opsForSet().add(CACHE_HOT_SHOP_IDS, "1", "2", "3", "4", "5", "6");

        for (Long id : hotIds) {
            Shop shop = shopService.getById(id);
            assertNotNull(shop, "热门门店不存在，id=" + id);
            redisTemplate.delete(CACHE_SHOP_KEY + id);
            cacheClient.setWithLogicalExpire(CACHE_HOT_SHOP_KEY + id, shop, 30L, TimeUnit.MINUTES);
        }

        for (Long id : normalIds) {
            Shop shop = shopService.getById(id);
            assertNotNull(shop, "普通门店不存在，id=" + id);
            redisTemplate.delete(CACHE_HOT_SHOP_KEY + id);
            cacheClient.set(CACHE_SHOP_KEY + id, shop, RedisConstants.CACHE_SHOP_TTL, TimeUnit.MINUTES);
        }
    }
    @Test
    void testIdWorker() throws InterruptedException {
        //初始计数为300的计数器
        CountDownLatch latch = new CountDownLatch(300);

        Runnable task = () -> {
            for (int i = 0; i < 100; i++) {
                long id = redisIdWorker.nextId("order");
                System.out.println("id = " + id);
            }
            latch.countDown();
        };
        //Lambda表达式相当于：
        //Runnable task = new Runnable() {
        //    @Override
        //    public void run() {
        //        // 任务内容
        //    }
        //};

        long begin = System.currentTimeMillis();

        for (int i = 0; i < 300; i++) {
            es.submit(task);
        }

        latch.await();
        //作用：让测试主线程等待其他多的任务全部完成执行完成
        //避免任务还没有执行完就测量结束时间

        long end = System.currentTimeMillis();
        System.out.println("time = " + (end - begin));
    }
    @Test
    public void showShopData(){
        // 1.查询店铺信息
        List<Shop> list = shopService.list();
        // 2.把店铺分组，按照typeId分组，typeId一致的放到一个集合
        Map<Long, List<Shop>> map = list.stream().collect(Collectors.groupingBy(Shop::getTypeId));
        // 3.分批完成写入Redis
        for (Map.Entry<Long, List<Shop>> entry : map.entrySet()) {
            // 3.1.获取类型id
            Long typeId = entry.getKey();
            String key = SHOP_GEO_KEY + typeId;
            // 3.2.获取同类型的店铺的集合
            List<Shop> value = entry.getValue();
            List<RedisGeoCommands.GeoLocation<String>> locations = new ArrayList<>(value.size());
            // 3.3.写入redis GEOADD key 经度 纬度 member
            for (Shop shop : value) {
                // stringRedisTemplate.opsForGeo().add(key, new Point(shop.getX(), shop.getY()), shop.getId().toString());
                locations.add(new RedisGeoCommands.GeoLocation<>(
                        shop.getId().toString(),
                        new Point(shop.getX(), shop.getY())
                ));
            }
            redisTemplate.opsForGeo().add(key, locations);
        }


    }


}
