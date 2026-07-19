package com.nanxinda.shop.controller;

import com.nanxinda.shop.entity.Shop;
import com.nanxinda.shop.service.ShopService;
import com.nanxinda.system.dto.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shop")
public class ShopController {
    //查询商铺信息
    @Qualifier("shopServiceImpl")
    @Autowired
    ShopService service;
    @GetMapping("/{id}")
    public Result queryShopById(@PathVariable Long id){
        return service.queryById(id);
    }

    //更新商铺信息
    @PutMapping
    public Result updateShop(@RequestBody Shop shop){
        return service.update(shop);
    }
}
