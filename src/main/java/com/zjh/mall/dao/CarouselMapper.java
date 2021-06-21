package com.zjh.mall.dao;

import com.zjh.mall.entity.Carousel;

public interface CarouselMapper {
    int deleteByPrimaryKey(Integer adminUserId);

    int insert(Carousel record);

    int insertSelective(Carousel record);

    Carousel selectByPrimaryKey(Integer adminUserId);

    int updateByPrimaryKeySelective(Carousel record);

    int updateByPrimaryKey(Carousel record);
}