package com.atguigu.spzx.manager.controller;

import com.annotation.spzx.common.log.annotation.Log;
import com.atguigu.spzx.manager.service.SysRoleService;
import com.atguigu.spzx.model.dto.system.SysRoleDto;
import com.atguigu.spzx.model.entity.system.SysRole;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author
 * @Description:
 */
@RestController
@RequestMapping("/admin/system/sysRole")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 角色列表的方法
     * current 当前页
     * limit 每页显示条数
     * sysRoleDto 角色名称对象
     */
    @PostMapping("/findByPage/{current}/{limit}")
    public Result findByPage(@PathVariable("current") Integer current,
                             @PathVariable("limit") Integer limit,
                              SysRoleDto sysRoleDto) {
        //pageHelper插件实现分页
        PageInfo<SysRole> pageInfo = sysRoleService.findByPage(sysRoleDto, current, limit);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

    /**
     * 添加角色
     * @param sysRole
     * @return
     */
    @Log(title = "角色管理：添加角色", businessType = 1)
    @PostMapping(value = "/saveSysRole")
    public Result saveSysRole(@RequestBody SysRole sysRole) {
        sysRoleService.saveSysRole(sysRole);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
    /**
     * 修改角色
     */
    @PutMapping("/updateSysRole")
    public Result updateSysRole(@RequestBody SysRole sysRole) {
        sysRoleService.updateSysRole(sysRole);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    /**
     * 删除角色
     */
    @DeleteMapping("/deleteById/{RoleId}")
    public Result deleteSyRole(@PathVariable("RoleId") Long roleId) {
        sysRoleService.deleteById(roleId);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    /**
     * 查询所有的角色
     */
    @GetMapping("/findAllRoles/{userId}")
    public Result<Map<String , Object>> findAllRoles(@PathVariable("userId") Long userId) {
       Map<String,Object> map =  sysRoleService.findAll(userId);
       return Result.build(map, ResultCodeEnum.SUCCESS);
    }


}
