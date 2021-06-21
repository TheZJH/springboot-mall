package com.zjh.mall.service.impl;

import com.zjh.mall.dao.AdminUserMapper;
import com.zjh.mall.entity.AdminUser;
import com.zjh.mall.service.AdminUserService;
import com.zjh.mall.util.MD5Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @author 20143
 */
@Service
public class AdminUserServiceImpl implements AdminUserService {
    @Resource
    private AdminUserMapper adminUserMapper;

    @Override
    public AdminUser login(String username, String password) {
        //md5加密
        String passwordMd5 = MD5Util.MD5Encode(password, "UTF-8");
        return adminUserMapper.login(username, passwordMd5);
    }

    @Override
    public AdminUser getUserDetailById(Integer loginId) {
        return null;
    }

    @Override
    public boolean updateUserPassword(Integer loginUserId, String oldPassword, String newPassword) {
        AdminUser adminUser = adminUserMapper.selectByPrimaryKey(loginUserId);
        //当前用户非空可以修改
        if (adminUser != null) {
            String oldPasswordMd5 = MD5Util.MD5Encode(oldPassword, "UTF-8");
            String newPasswordMd5 = MD5Util.MD5Encode(newPassword, "UTF-8");
            //如果数据库密码和输入密码相等
            if (oldPasswordMd5.equals(adminUser.getLoginPassword())) {
                adminUser.setLoginPassword(newPasswordMd5);
                if (adminUserMapper.updateByPrimaryKeySelective(adminUser) > 0) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean updateUserName(Integer loginUserId, String oldName, String newName) {
        AdminUser adminUser = adminUserMapper.selectByPrimaryKey(loginUserId);
        if (adminUser != null) {
            adminUser.setLoginUserName(oldName);
            adminUser.setNickName(newName);
        }
        if (adminUserMapper.updateByPrimaryKeySelective(adminUser) > 0) {
            return true;
        }
        return false;
    }
}
