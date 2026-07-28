package com.nanxinda.coupon.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nanxinda.coupon.entity.Voucher;
import com.nanxinda.shop.entity.Shop;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface VoucherMapper extends BaseMapper<Voucher> {

    public List<Shop> queryVoucherOfShop(@Param("shopId")Long id);
}
