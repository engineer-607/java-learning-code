package com.nanxinda.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nanxinda.shop.entity.ShopType;
import com.nanxinda.shop.mapper.ShopTypeMapper;
import com.nanxinda.shop.service.ShopTypeService;
import org.springframework.stereotype.Service;

@Service
public class ShopTypeServiceImpl extends ServiceImpl<ShopTypeMapper, ShopType> implements ShopTypeService {
}
