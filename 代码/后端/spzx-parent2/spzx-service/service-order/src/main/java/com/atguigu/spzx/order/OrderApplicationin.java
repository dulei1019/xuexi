package com.atguigu.spzx.order;

import com.atguigu.spzx.common.anno.EnableUserLoginAuthInterceptor;
import com.atguigu.spzx.common.anno.EnableUserTokenFeignInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author
 * @Description:
 */
@SpringBootApplication
@EnableUserTokenFeignInterceptor
@EnableUserLoginAuthInterceptor
@EnableTransactionManagement
@EnableFeignClients(basePackages = "com.atguigu.spzx.feign")
public class OrderApplicationin {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplicationin.class, args);
    }
}