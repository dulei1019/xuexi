package com.atguigu.spzx.user.service.impl;

import com.alibaba.fastjson2.JSON;
import com.atguigu.spzx.common.exception.GuiguException;
import com.atguigu.spzx.model.dto.h5.UserLoginDto;
import com.atguigu.spzx.model.dto.h5.UserRegisterDto;
import com.atguigu.spzx.model.entity.user.UserInfo;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.model.vo.h5.UserInfoVo;
import com.atguigu.spzx.user.mapper.UserInfoMapper;
import com.atguigu.spzx.user.service.UserInfoService;
import com.atguigu.spzx.utils.AuthContextUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Author
 * @Description:
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private RedisTemplate<String , String> redisTemplate;

    /**
     * 用户注册
     * @param userRegisterDto
     */
    @Override
    public void register(UserRegisterDto userRegisterDto) {
        //1.从dto中获取数据
        String username = userRegisterDto.getUsername();
        String password = userRegisterDto.getPassword();
        String nickName = userRegisterDto.getNickName();
        String code = userRegisterDto.getCode();

        //2.验证码校验
        //2.1从redis中获取验证码
        String redisCode = redisTemplate.opsForValue().get("phone:code:"+username);
        //2.2判断验证码是否正确
        if (redisCode == null||!redisCode.equals(code)){
           throw new GuiguException(ResultCodeEnum.VALIDATECODE_ERROR);
        }
        //3.校验用户名不能重复
        UserInfo userInfo = userInfoMapper.getByUsername(username);
        if (userInfo != null){
            throw new GuiguException(ResultCodeEnum.USER_NAME_IS_EXISTS);
        }
        //4.封装添加的方法，调用方法添加数据库
        userInfo = new UserInfo();
        userInfo.setUsername(username);
        userInfo.setNickName(nickName);
        userInfo.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
        userInfo.setPhone(username);
        userInfo.setStatus(1);
        userInfo.setSex(0);
        userInfo.setAvatar("http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132");

        userInfoMapper.save(userInfo);

        //5.从redis中删除验证码
        redisTemplate.delete(username);

    }

    /**
     * 用户登录
     * @param userLoginDto
     * @return
     */
    @Override
    public String login(UserLoginDto userLoginDto) {
        //1.从dto中获取数据
        String username = userLoginDto.getUsername();
        String password = userLoginDto.getPassword();

        //2.调用mapper查询用户信息
        UserInfo userInfo = userInfoMapper.getByUsername(username);
        //3.判断用户是否存在
        if (userInfo == null){
            throw new GuiguException(ResultCodeEnum.LOGIN_ERROR);
        }
        //4.判断密码是否正确
        if (!DigestUtils.md5DigestAsHex(password.getBytes()).equals(userInfo.getPassword())){
            throw new GuiguException(ResultCodeEnum.LOGIN_ERROR);
        }
        //5.判断用户是否被禁用
        if (userInfo.getStatus() == 0){
            throw new GuiguException(ResultCodeEnum.ACCOUNT_STOP);
        }
        //6.生成token
        String token = UUID.randomUUID().toString().replaceAll("-","");
        //7.将token存入redis
        redisTemplate.opsForValue().set("user:spzx:"+token,
                                        JSON.toJSONString(userInfo),
                                        30, TimeUnit.DAYS);
        //8.返回token
        return token;
    }

    /**
     * 获取当前登录用户信息
     * @param token
     * @return
     */
    @Override
    public UserInfoVo getCurrentUserInfo(String token) {
        //1.从redis中获取用户信息
        /*String userInfoJSON = redisTemplate.opsForValue().get("user:spzx:" + token);
        if(!StringUtils.hasText(userInfoJSON)) {
            throw new GuiguException(ResultCodeEnum.LOGIN_AUTH) ;
        }
        //2.将json字符串转换为对象
        UserInfo userInfo = JSON.parseObject(userInfoJSON , UserInfo.class) ;*/
        //从theadLocal中获取用户信息

        UserInfo userInfo = AuthContextUtil.getUserInfo();
        UserInfoVo userInfoVo = new UserInfoVo();
        BeanUtils.copyProperties(userInfo, userInfoVo);
        return userInfoVo ;
    }
}















