package com.atguigu.spzx.cart;

import com.atguigu.spzx.common.anno.EnableUserLoginAuthInterceptor;
import com.atguigu.spzx.common.anno.EnableUserTokenFeignInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author
 * @Description:
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)  // 排除数据库的自动化配置，Cart微服务不需要访问数据库
@EnableFeignClients(basePackages = {"com.atguigu.spzx"})  // 开启Feign远程调用
@EnableUserLoginAuthInterceptor
@EnableUserTokenFeignInterceptor
public class CartApplication {

    public static void main(String[] args) {
        SpringApplication.run(CartApplication.class , args) ;
    }

}