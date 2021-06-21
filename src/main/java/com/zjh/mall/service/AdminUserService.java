package com.zjh.mall.service;

import com.zjh.mall.entity.AdminUser;

import java.util.List;

/**
 * @author 20143
 */
public interface AdminUserService {
    AdminUser login(String username, String password);

    AdminUser getUserDetailById(Integer loginUserId);

    /**
     * 更新密码
     * @param loginUserId
     * @param oldPassword
     * @param newPassword
     * @return
     */
    boolean updateUserPassword(Integer loginUserId,String oldPassword,String newPassword);

    /**
     * 更新用户名
     * @param loginUserId
     * @param oldName
     * @param newName
     * @return
     */
    boolean updateUserName(Integer loginUserId,String oldName,String newName);

}
