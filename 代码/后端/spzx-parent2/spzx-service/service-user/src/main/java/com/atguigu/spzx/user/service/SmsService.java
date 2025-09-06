package com.atguigu.spzx.user.service;

/**
 * @Author
 * @Description:
 */
public interface SmsService {
    /**
     * 发生短信验证码
     * @param phone
     */
    void sendCode(String phone);
}
