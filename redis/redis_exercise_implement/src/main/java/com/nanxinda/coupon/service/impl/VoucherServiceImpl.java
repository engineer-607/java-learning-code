package com.nanxinda.coupon.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nanxinda.coupon.entity.SeckillVoucher;
import com.nanxinda.coupon.entity.Voucher;
import com.nanxinda.coupon.mapper.VoucherMapper;
import com.nanxinda.coupon.service.SeckillVoucherService;
import com.nanxinda.coupon.service.VoucherService;
import com.nanxinda.system.dto.Result;
import com.nanxinda.system.utils.RedisConstants;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class VoucherServiceImpl extends ServiceImpl<VoucherMapper,Voucher> implements VoucherService {
    @Resource
    SeckillVoucherService service;
    @Resource
    StringRedisTemplate stringRedisTemplate;
    @Resource
    VoucherMapper voucherMapper;
    @Override
    public void addSeckillVoucher(Voucher voucher) {
        //保存优惠券
        save(voucher);
        //保存秒杀信息
        SeckillVoucher seckillVoucher = new SeckillVoucher();
        seckillVoucher.setVoucherId(voucher.getId());
        seckillVoucher.setStock(voucher.getStock());
        seckillVoucher.setBeginTime(voucher.getBeginTime());
        seckillVoucher.setEndTime(voucher.getEndTime());
        log.debug("优惠券信息：{}",seckillVoucher);
        service.save(seckillVoucher);
        stringRedisTemplate.opsForValue().set(RedisConstants.SECKILL_STOCK_KEY + voucher.getId(),voucher.getStock().toString());
    }

    @Override
    public Result queryVoucherOfShop(Long id) {
        return Result.ok(voucherMapper.queryVoucherOfShop(id));
    }
}
