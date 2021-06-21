package com.zjh.mall.dao;

import com.zjh.mall.entity.GoodsCategory;

public interface GoodsCategoryMapper {
    int deleteByPrimaryKey(Integer adminUserId);

    int insert(GoodsCategory record);

    int insertSelective(GoodsCategory record);

    GoodsCategory selectByPrimaryKey(Integer adminUserId);

    int updateByPrimaryKeySelective(GoodsCategory record);

    int updateByPrimaryKey(GoodsCategory record);
}