package com.zjh.mall.dao;

import com.zjh.mall.entity.OrderItem;

public interface OrderItemMapper {
    int deleteByPrimaryKey(Integer adminUserId);

    int insert(OrderItem record);

    int insertSelective(OrderItem record);

    OrderItem selectByPrimaryKey(Integer adminUserId);

    int updateByPrimaryKeySelective(OrderItem record);

    int updateByPrimaryKey(OrderItem record);
}