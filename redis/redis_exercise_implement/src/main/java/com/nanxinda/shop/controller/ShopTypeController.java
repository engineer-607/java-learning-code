package com.nanxinda.shop.controller;

import com.nanxinda.shop.service.ShopTypeService;
import com.nanxinda.system.dto.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("shop-type")
public class ShopTypeController {
    @Autowired
    private ShopTypeService shopTypeService;

    @GetMapping("/list")
    public Result queryTypeList() {
        return Result.ok(shopTypeService.query().orderByAsc("sort").list());
    }
}
