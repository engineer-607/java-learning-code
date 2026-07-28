package com.nanxinda.coupon.util;

public interface Lock {
    /**
     * 尝试获取锁
     * @param timeouts 锁持有的超时时间，过期后自动释放
     * @return 返回true代表锁获取成功，false代表锁获取失败
     */
    boolean tryLock(Long timeouts);

    /**
     * 释放锁
     */
    void unlock();
}
