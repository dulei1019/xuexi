package com.atguigu.spzx.user.service;

import com.atguigu.spzx.model.dto.h5.UserLoginDto;
import com.atguigu.spzx.model.dto.h5.UserRegisterDto;
import com.atguigu.spzx.model.vo.h5.UserInfoVo;

/**
 * @Author
 * @Description:
 */
public interface UserInfoService {
    /**
     * 用户注册
     * @param userRegisterDto
     */
    void register(UserRegisterDto userRegisterDto);

    /**
     * 用户登录
     * @param userLoginDto
     * @return
     */
    String login(UserLoginDto userLoginDto);

    /**
     * 获取当前登录用户信息
     * @param token
     * @return
     */
    UserInfoVo getCurrentUserInfo(String token);
}
