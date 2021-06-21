package com.zjh.mall.dao;

import com.zjh.mall.entity.ShoppingCartItem;

public interface ShoppingCartItemMapper {
    int deleteByPrimaryKey(Integer adminUserId);

    int insert(ShoppingCartItem record);

    int insertSelective(ShoppingCartItem record);

    ShoppingCartItem selectByPrimaryKey(Integer adminUserId);

    int updateByPrimaryKeySelective(ShoppingCartItem record);

    int updateByPrimaryKey(ShoppingCartItem record);
}