package com.atguigu.spzx.pay;

import com.atguigu.spzx.common.anno.EnableUserLoginAuthInterceptor;
import com.atguigu.spzx.pay.utils.AlipayProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author
 * @Description:
 */
@SpringBootApplication
@EnableUserLoginAuthInterceptor //开启用户拦截器
@EnableTransactionManagement //开启事务管理
@EnableConfigurationProperties(value = {AlipayProperties.class})
@EnableFeignClients(basePackages ={"com.atguigu.spzx.fegin", "com.atguigu.spzx.feign"}) //开启feign远程调用
public class PayApplication {

    public static void main(String[] args) {
        SpringApplication.run(PayApplication.class , args) ;
    }

}
