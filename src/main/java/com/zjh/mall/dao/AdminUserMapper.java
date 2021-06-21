package com.zjh.mall.dao;

import com.zjh.mall.entity.AdminUser;

public interface AdminUserMapper {
    int deleteByPrimaryKey(Integer adminUserId);

    int insert(AdminUser record);

    int insertSelective(AdminUser record);

    AdminUser login(String username,String password);

    AdminUser selectByPrimaryKey(Integer adminUserId);

    int updateByPrimaryKeySelective(AdminUser record);

    int updateByPrimaryKey(AdminUser record);
}