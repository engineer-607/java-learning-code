package com.nanxinda.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nanxinda.coupon.entity.VoucherOrder;
import com.nanxinda.system.dto.Result;

public interface VoucherOrderService extends IService<VoucherOrder> {
    Result seckillVoucher(Long voucherId);

    public Result createVoucherOrder(Long voucherId);
}
