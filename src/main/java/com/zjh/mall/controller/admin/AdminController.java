package com.zjh.mall.controller.admin;

import com.zjh.mall.entity.AdminUser;
import com.zjh.mall.service.AdminUserService;
import jdk.internal.org.objectweb.asm.tree.analysis.Value;
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
            session.setAttribute("errorMsg", "验证码或用户名密码错误");
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
    public String profile(HttpServletRequest request) {
        //得到之前存在session中的id
        Integer loginUserId = (int) request.getSession().getAttribute("loginUserId");
        //根据id查询到id值所对应的用户信息
        AdminUser userDetailById = adminUserService.getUserDetailById(loginUserId);
        //保存session中的值
        //request.setAttribute("path", "profile");
        request.setAttribute("loginUserName", userDetailById.getLoginUserName());
        request.setAttribute("nickName", userDetailById.getNickName());
        return "admin/profile";
    }

    @PostMapping("/profile/password")
    @ResponseBody
    public String passwordupdate(HttpServletRequest request,
                                 @RequestParam("oldPassword") String oldPassword,
                                 @RequestParam("newPassword") String newPassword) {
        if (StringUtils.isEmpty(oldPassword) || StringUtils.isEmpty(newPassword)) {
            return "参数不能为空";
        }
        Integer loginUserId = (int) request.getSession().getAttribute("loginUserId");
        if (adminUserService.updateUserPassword(loginUserId, oldPassword, newPassword)) {
            //修改成功后删除session中的数据
            request.getSession().removeAttribute("loginUserId");
            request.getSession().removeAttribute("loginUser");
            //前端控制跳转
            return "success";
        } else {
            return "修改失败";
        }

    }

    @RequestMapping(value = "/profile/name", method = RequestMethod.POST)
    @ResponseBody
    public String updateName(HttpServletRequest request,
                             @RequestParam("loginUserName") String oldName,
                             @RequestParam("nickName") String newName) {
        if (StringUtils.isEmpty(oldName) || StringUtils.isEmpty(newName)) {
            return "用户名为空";
        }
        Integer userId = (int) request.getSession().getAttribute("loginUserId");
        //adminuserService方法可以用if进行判断, 因为他返回的是一个boolean类型的值
        if (adminUserService.updateUserName(userId, oldName, newName)) {
            //前端控制跳转
            return "success";
        } else {
            return "修改失败";
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("loginUserId");
        request.getSession().removeAttribute("loginUser");
        request.getSession().removeAttribute("errorMsg");
        return "admin/login";
    }
}