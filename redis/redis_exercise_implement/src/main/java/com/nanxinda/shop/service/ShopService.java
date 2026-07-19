package com.nanxinda.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nanxinda.shop.entity.Shop;
import com.nanxinda.system.dto.Result;

import java.util.Random;

public interface ShopService extends IService<Shop> {
    public Result queryByIdThroughLock(Long id);
    public Result update(Shop shop);
    public Result queryByIdThroughExpireTime(Long id);
    public Result queryById(Long id);
}
