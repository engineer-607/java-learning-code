package com.nanxinda.system.utils;


public final class RedisConstants {
    private RedisConstants(){}
    public static final String LOGIN_CODE_KEY = "login:code:";
    public static final Long LOGIN_CODE_TTL = 2L;

    public static final String LOGIN_USER_KEY = "login:token:";
    public static final Long LOGIN_USER_TTL = 30L;

    public static final String CACHE_SHOP_KEY = "cache:shop";
    public static final String CACHE_HOT_SHOP_KEY = "cache:hot:shop";
    public static final Long LOGIN_SHOP_TTL = 30L;
    public static final String LOCK_SHOP_KEY = "lock:shop";
    public static final Long CACHE_NULL_TTL = 15L;
    public static final Long CACHE_SHOP_TTL = 30L;

    public static final String CACHE_HOT_SHOP_IDS = "shop:hot:ids";

    public static final String SECKILL_STOCK_KEY = "seckill:stock:";

    public static final String SHOP_GEO_KEY = "shop:geo:";

}
