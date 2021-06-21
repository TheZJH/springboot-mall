package com.zjh.mall.dao;

import com.zjh.mall.entity.IndexConfig;

public interface IndexConfigMapper {
    int deleteByPrimaryKey(Integer adminUserId);

    int insert(IndexConfig record);

    int insertSelective(IndexConfig record);

    IndexConfig selectByPrimaryKey(Integer adminUserId);

    int updateByPrimaryKeySelective(IndexConfig record);

    int updateByPrimaryKey(IndexConfig record);
}