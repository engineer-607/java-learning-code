package com.nanxinda.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nanxinda.coupon.entity.Voucher;
import com.nanxinda.system.dto.Result;

public interface VoucherService extends IService<Voucher> {
    void addSeckillVoucher(Voucher voucher);

    Result queryVoucherOfShop(Long id);

}
