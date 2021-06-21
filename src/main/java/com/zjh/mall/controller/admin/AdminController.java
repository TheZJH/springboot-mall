package com.zjh.mall.controller.admin;

import com.zjh.mall.entity.AdminUser;
import com.zjh.mall.service.AdminUserService;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * @author 20143
 */
@RequestMapping("/admin")
@Controller
public class AdminController {
    @Resource
    private AdminUserService adminUserService;

    /**
     * 来到页面
     *
     * @return
     */
    @GetMapping("/login")
    public String login() {
        return "admin/login";
    }

    /**
     * 规定主页面
     *
     * @param request
     * @return
     */
    @GetMapping({"", "/", "/index", "/index.html"})
    public String index(HttpServletRequest request) {
        request.setAttribute("path", "index");
        return "admin/index";
    }

    @PostMapping("/login")
    public String login(@RequestParam("userName") String username,
                        @RequestParam("password") String password,
                        @RequestParam("verifyCode") String verifyCode,
                        HttpSession session) {
        if (StringUtils.isEmpty(verifyCode)) {
            session.setAttribute("errorMsg", "验证码不能为空");
            return "admin/login";
        }
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            session.setAttribute("errorMsg", "用户名或密码不能为空");
            return "admin/login";
        }
        String kaptchaCode = session.getAttribute("verifyCode") + "";
        if (StringUtils.isEmpty(kaptchaCode) || !verifyCode.equals(kaptchaCode)) {
            session.setAttribute("errorMsg", "验证码错误");
            return "admin/login";
        }
        AdminUser adminUser = adminUserService.login(username, password);
        if (adminUser != null) {
            //把登陆成功的用户保存起来
            session.setAttribute("loginUser", adminUser.getNickName());
            session.setAttribute("loginUserId", adminUser.getAdminUserId());
            //session过期时间设置为7200秒 即两小时
            session.setMaxInactiveInterval(60 * 60 * 2);
            //重定向避免表单被重复刷新,springboot无法通过url直接访问templates,必需跳转
            return "redirect:/admin/index";
        } else {
            session.setAttribute("errorMsg", "登录失败");
            return "admin/login";
        }
    }

    @GetMapping("/profile")
    public String profile() {
        return "admin/profile";
    }

    @PostMapping("/profile")
    @ResponseBody
    public String passwordupdate(HttpServletRequest request,
                                 @RequestParam("orginalPassword") String orginalPassword,
                                 @RequestParam("newPassword") String newPassword) {
        if (StringUtils.isEmpty(orginalPassword) || StringUtils.isEmpty(newPassword)) {
            return "参数不能为空";
        }
        Integer loginUserId = (int) request.getSession().getAttribute("loginUserId");
        adminUserService.updateUserPassword(loginUserId, orginalPassword, newPassword);
        return "admin/login";
    }
}