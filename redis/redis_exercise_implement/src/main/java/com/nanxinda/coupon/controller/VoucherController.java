package com.nanxinda.coupon.controller;

import com.nanxinda.coupon.entity.Voucher;
import com.nanxinda.coupon.service.SeckillVoucherService;
import com.nanxinda.coupon.service.VoucherService;
import com.nanxinda.system.dto.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/voucher")
public class VoucherController {

    @Resource
    private VoucherService service;


    //添加优惠券
    @PostMapping("/seckill")
    public Result addSeckillVoucher(@RequestBody Voucher voucher){
        service.addSeckillVoucher(voucher);
        return Result.ok(voucher.getId());
    }

    //查询优惠券
    @GetMapping("/list/{shopId}")
    public Result queryVoucherOfShop(@PathVariable("shopId")Long id){
        return service.queryVoucherOfShop(id);
    }




}
