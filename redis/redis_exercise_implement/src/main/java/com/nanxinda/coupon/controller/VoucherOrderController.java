package com.nanxinda.coupon.controller;

import com.nanxinda.coupon.service.VoucherOrderService;
import com.nanxinda.system.dto.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/voucher-order")
public class VoucherOrderController {

    @Resource
    VoucherOrderService voucherOrderService;

    @PostMapping("/seckill/{id}")
    public Result seckillVoucher(@PathVariable Long id){
        return voucherOrderService.seckillVoucher(id);
    }
}
