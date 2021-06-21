package com.zjh.mall.dao;

import com.zjh.mall.entity.GoodsInfo;

public interface GoodsInfoMapper {
    int deleteByPrimaryKey(Integer adminUserId);

    int insert(GoodsInfo record);

    int insertSelective(GoodsInfo record);

    GoodsInfo selectByPrimaryKey(Integer adminUserId);

    int updateByPrimaryKeySelective(GoodsInfo record);

    int updateByPrimaryKey(GoodsInfo record);
}