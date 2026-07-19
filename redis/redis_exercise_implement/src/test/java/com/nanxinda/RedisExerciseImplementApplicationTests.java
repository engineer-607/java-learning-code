package com.nanxinda;

import com.nanxinda.shop.entity.Shop;
import com.nanxinda.shop.service.impl.ShopServiceImpl;
import com.nanxinda.system.utils.CacheClient;
import com.nanxinda.system.utils.RedisConstants;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

import static com.nanxinda.system.utils.RedisConstants.CACHE_HOT_SHOP_IDS;
import static com.nanxinda.system.utils.RedisConstants.CACHE_HOT_SHOP_KEY;
import static com.nanxinda.system.utils.RedisConstants.CACHE_SHOP_KEY;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class RedisExerciseImplementApplicationTests {
    @Autowired
    CacheClient cacheClient;
    @Autowired
    StringRedisTemplate redisTemplate;
    @Autowired
    ShopServiceImpl shopService;

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


}
