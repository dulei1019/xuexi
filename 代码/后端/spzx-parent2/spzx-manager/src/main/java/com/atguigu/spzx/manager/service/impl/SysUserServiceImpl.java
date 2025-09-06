package com.atguigu.spzx.manager.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.atguigu.spzx.common.exception.GuiguException;
import com.atguigu.spzx.manager.mapper.SysRoleUserMapper;
import com.atguigu.spzx.manager.mapper.SysUserMapper;
import com.atguigu.spzx.manager.service.SysUserService;
import com.atguigu.spzx.model.dto.system.AssginRoleDto;
import com.atguigu.spzx.model.dto.system.LoginDto;
import com.atguigu.spzx.model.dto.system.SysUserDto;
import com.atguigu.spzx.model.entity.system.SysUser;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.model.vo.system.LoginVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Author
 * @Description:
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    private SysRoleUserMapper sysRoleUserMapper;


    /**
     * 用户登录
     * @param loginDto
     * @return
     */
    @Override
    public LoginVo login(LoginDto loginDto) {

        //获取输入的验证码，和存储到redis的key名称
        String captcha = loginDto.getCaptcha();
        String key = loginDto.getCodeKey();
        //根据获取的redis里面的key，查询redis里面的验证码
        String redisCode = redisTemplate.opsForValue().get("user:validata" + key);
        //比较输入的验证码和redis里面的验证码是否一致
        if (StrUtil.isEmpty(captcha)||!StrUtil.equalsIgnoreCase(captcha,redisCode)) {
            //如果不一样提示用户，校验失败
            throw new GuiguException(ResultCodeEnum.VALIDATECODE_ERROR);
        }
        //如果一样，删除redis的验证码
        redisTemplate.delete("user:validata" + key);



        // 1.获取提交的用户名
        String userName = loginDto.getUserName();
        // 2.根据用户名查询数据库sys_user表
        SysUser sysUser = sysUserMapper.selectUserInfoByUserName(userName);
        //3.如果根据用户名查询不到数据，用户不存在，返回错误信息
        if (sysUser == null) {
           //throw new RuntimeException("用户不存在");
            throw new GuiguException(ResultCodeEnum.LOGIN_ERROR);
        }
        //4.如果查询到用户信息，用户存在
        //5.获取输入的密码，比较输入的密码和数据库中的密码是否一致
        String database_password = sysUser.getPassword();
        //获取用户提交的密码，并且进行加密
        String input_password = DigestUtils.md5DigestAsHex(loginDto.getPassword().getBytes());
        //比较用户提交的密码和数据库中的密码是否一致
        if (!database_password.equals(input_password)) {
            //throw new RuntimeException("密码不正确");
            throw new GuiguException(ResultCodeEnum.LOGIN_ERROR);
        }
        //6.如果密码一致，登录成功，如果不一致，登录失败
        //7.登录成功，生成用户唯一标识token
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        //8.将登录成功的用户信息存入redis
        //key:token  value:用户信息

        redisTemplate.opsForValue().
                set("user:login"+token,
                        JSON.toJSONString(sysUser),//将对象转换为json字符串，存入redis中
                        7,
                        TimeUnit.DAYS);

        //9.返回loginVo对象
        LoginVo loginVo = new LoginVo();
        loginVo.setToken(token);
        return loginVo;
    }

    /**
     * 获取用户信息
     * @param token
     * @return
     */
    @Override
    public SysUser getUserInfo(String token) {
        //根据token获取redis中的用户信息
        String userJson = redisTemplate.opsForValue().get("user:login" + token);
        //将json数据转换为SysUser对象
        SysUser sysUser = JSON.parseObject(userJson, SysUser.class);
        //将SysUser对象返回
        return sysUser;

    }

    /**
     * 用户退出登录
     * @param token
     */
    @Override
    public void logout(String token) {
        //根据token删除redis中的用户信息
        redisTemplate.delete("user:login" + token);

    }

    /**
     * 分页查询用户信息
     * @param pageNum
     * @param pageSize
     * @param sysUserDto
     * @return
     */
    @Override
    public PageInfo<SysUser> getUserByPage(Integer pageNum, Integer pageSize, SysUserDto sysUserDto) {
        //1.调用PageHelper插件，开启分页
        PageHelper.startPage(pageNum, pageSize);
        //2.调用mapper查询数据
        List<SysUser> list = sysUserMapper.findByPage(sysUserDto);
        //3.将查询到的数据封装到PageInfo对象中
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     * 新增用户信息
     * @param sysUser
     */
    @Override
    public void saveSysUser(SysUser sysUser) {
        //1.判断用户名是否存在
        String userName = sysUser.getUserName();
        SysUser dbSysUser = sysUserMapper.selectUserInfoByUserName(userName);
        if (dbSysUser != null) {
            throw new GuiguException(ResultCodeEnum.USER_NAME_IS_EXISTS);
        }

        //1.获取用户输入的密码
        String password = sysUser.getPassword();
        //2.将密码进行加密
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
        sysUser.setPassword(md5Password);
        //设置用户状态，1-正常，0-不可用
        sysUser.setStatus(1);


        sysUserMapper.save(sysUser);
    }

    /**
     * 修改用户信息
     * @param sysUser
     */
    @Override
    public void updateSysUser(SysUser sysUser) {
        //1.判断用户名是否存在
        String userName = sysUser.getUserName();
        SysUser dbSysUser = sysUserMapper.selectUserInfoByUserName(userName);
        if (dbSysUser != null) {
            throw new GuiguException(ResultCodeEnum.USER_NAME_IS_EXISTS);
        }
        sysUserMapper.update(sysUser);
    }

    /**
     * 删除用户信息
     * @param userId
     */
    @Override
    public void deleteById(Integer userId) {
        sysUserMapper.delete(userId);
    }

    /**
     * 分配角色
     * @param assginRoleDto
     */
    @Override
    public void doAssign(AssginRoleDto assginRoleDto) {
        //1.根据userId删除用户之前分配角色数据
        sysRoleUserMapper.deleteByUserId(assginRoleDto.getUserId()) ;
        //2.根据userId和roleId重新分配角色
        List<Long> roleIdList = assginRoleDto.getRoleIdList();
        for (Long roleId : roleIdList) {
            sysRoleUserMapper.doAssign(assginRoleDto.getUserId(), roleId);
        }
    }
}
















