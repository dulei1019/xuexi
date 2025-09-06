package com.atguigu.spzx.manager.service.impl;

import com.atguigu.spzx.manager.mapper.SysRoleMapper;
import com.atguigu.spzx.manager.mapper.SysRoleUserMapper;
import com.atguigu.spzx.manager.service.SysRoleService;
import com.atguigu.spzx.model.dto.system.SysRoleDto;
import com.atguigu.spzx.model.entity.system.SysRole;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author
 * @Description:
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {


    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysRoleUserMapper sysRoleUserMapper;

    /**
     * 角色列表的方法
     * @param sysRoleDto
     * @param current
     * @param limit
     * @return
     */
    @Override
    public PageInfo<SysRole> findByPage(SysRoleDto sysRoleDto, Integer current, Integer limit) {
        //1.设置分页参数
        PageHelper.startPage(current,limit);
        //2.根据条件查询所以数据
        List<SysRole> list = sysRoleMapper.findByPage(sysRoleDto);
        //3.将查询到的数据封装到PageInfo对象中
        PageInfo<SysRole> pageInfo = new PageInfo<>(list);

        return pageInfo;
    }

    /**
     * 添加角色
     * @param sysRole
     */
    @Override
    public void saveSysRole(SysRole sysRole) {
        sysRoleMapper.saveSysRole(sysRole);

    }

    /**
     * 修改角色
     * @param sysRole
     */
    @Override
    public void updateSysRole(SysRole sysRole) {
        sysRoleMapper.updateSysRole(sysRole);
    }

    /**
     * 删除角色
     * @param roleId
     */
   @Override
    public void deleteById(Long roleId) {
        sysRoleMapper.deleteSysRole(roleId);
    }

    /**
     * 查询所有角色
     * @return
     */
    @Override
    @Transactional
    public Map<String, Object> findAll(Long userId) {
        //1.查询所有角色
        List<SysRole> roleList = sysRoleMapper.findAll();

        //2.分配过的角色列表
        //根据userId查询用户分配过的角色列表
       List<Long> roleIds = sysRoleUserMapper.selectRoleIdByUserId(userId);

        Map<String, Object> map = new HashMap<>();
        map.put("allRoleList",roleList);
        map.put("sysUserRoles",roleIds);
        return map;

    }
}













