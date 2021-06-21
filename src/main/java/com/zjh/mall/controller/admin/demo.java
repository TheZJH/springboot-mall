package com.zjh.mall.controller.admin;

import com.zjh.mall.dao.UserMapper;
import com.zjh.mall.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/demo")
public class demo {
    @Resource
    UserMapper userMapper;

    @RequestMapping("/hello")
    public String hello() {
        return "/demo/index";
    }

    @GetMapping("/user")
    public User query() {
        return userMapper.selectByPrimaryKey(2);
    }
}
