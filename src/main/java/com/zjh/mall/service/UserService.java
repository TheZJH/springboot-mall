package com.zjh.mall.service;

import com.zjh.mall.entity.User;

public interface UserService {
    User selectByPrimaryKey(Integer adminUserId);
}
