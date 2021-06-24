package com.zjh.mall.config;

import com.zjh.mall.interceptor.AdminLoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置拦截器使其生效
 *
 * @author 20143
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Autowired
    AdminLoginInterceptor adminLoginInterceptor;

    /**
     * 帮助配置映射截取程序列表
     */

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加拦截/admin为前缀的url路径()后台登录拦截
        registry.addInterceptor(adminLoginInterceptor)
                .addPathPatterns("/admin/**")
                //排除登录页面,防止连登录页面也访问不到
                .excludePathPatterns("/admin/login")
                //排除静态资源页面
                .excludePathPatterns("/admin/dist/**")
                .excludePathPatterns("/admin/plugins/**");
    }

}
