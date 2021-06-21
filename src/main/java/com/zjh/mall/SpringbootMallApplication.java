package com.zjh.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 20143
 */
@SpringBootApplication
@MapperScan("com.zjh.mall.dao")
public class SpringbootMallApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringbootMallApplication.class, args);
    }

}
