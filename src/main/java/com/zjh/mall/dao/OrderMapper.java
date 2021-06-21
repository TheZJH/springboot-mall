package com.zjh.mall.dao;

import com.zjh.mall.entity.Order;

public interface OrderMapper {
    int deleteByPrimaryKey(Integer adminUserId);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer adminUserId);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}