package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.dto.system.SysUserDto;
import com.atguigu.spzx.model.entity.system.SysUser;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author
 * @Description:
 */
@Mapper
public interface SysUserMapper {


    /**
     * 根据用户名查询用户信息
     * @param userName
     * @return
     */
    SysUser selectUserInfoByUserName(String userName);


    List<SysUser> findByPage(SysUserDto sysUserDto);

    void save(SysUser sysUser);

    void update(SysUser sysUser);

    void delete(Integer userId);
}
