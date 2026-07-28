package com.nanxinda.shop.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nanxinda.shop.entity.Shop;
import com.nanxinda.shop.service.ShopService;
import com.nanxinda.system.dto.RedisData;
import com.nanxinda.system.dto.Result;
import com.nanxinda.system.utils.RedisConstants;
import com.nanxinda.system.utils.SystemConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shop")
public class ShopController {
    //查询商铺信息
    @Autowired
    ShopService service;
    @GetMapping("/{id}")
    public Result queryShopById(@PathVariable Long id){
        return service.queryById(id);
    }
    //根据商铺类型分页查询商铺信息
    @GetMapping("/of/type")
    public Result queryByType(
            @RequestParam("typeId") Integer typeId,
            @RequestParam(value = "current",defaultValue = "1") Integer current,
            @RequestParam(value = "x",required = false) Double x,
            @RequestParam(value = "y",required = false) Double y){
        return service.queryByType(typeId,current,x,y);
    }
    //根据商铺名称关键字分页查询商铺信息
    @GetMapping("/of/name")
    public Result queryShopByName(
            @RequestParam(value = "name",required = false) String name,
            @RequestParam(value = "current",defaultValue = "1")Integer current
    ){
        Page<Shop> page = service.lambdaQuery()
                .like(StrUtil.isNotBlank(name), Shop::getName, name)
                .page(new Page<>(current, SystemConstants.DEFAULT_PAGE_SIZE));
        return Result.ok(page.getRecords());
    }

    //更新商铺信息
    @PutMapping
    public Result updateShop(@RequestBody Shop shop){
        return service.update(shop);
    }
}
