package com.atguigu.spzx.manager.controller;


import com.atguigu.spzx.manager.service.SysMenuService;
import com.atguigu.spzx.manager.service.SysUserService;
import com.atguigu.spzx.manager.service.ValidateCodeService;
import com.atguigu.spzx.model.dto.system.LoginDto;
import com.atguigu.spzx.model.entity.system.SysMenu;
import com.atguigu.spzx.model.entity.system.SysUser;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.model.vo.system.LoginVo;
import com.atguigu.spzx.model.vo.system.SysMenuVo;
import com.atguigu.spzx.model.vo.system.ValidateCodeVo;
import com.atguigu.spzx.utils.AuthContextUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author
 * @Description:
 */
@Tag(name = "用户接口")
@RestController
@RequestMapping("/admin/system/index")
public class IndexController {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private ValidateCodeService validateCodeService;
    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 查询用户可操作的菜单
     */
    @GetMapping("/menus")
    public Result menus() {
        List<SysMenuVo> sysMenuVoList =  sysMenuService.findUserMenuList() ;
        return Result.build(sysMenuVoList , ResultCodeEnum.SUCCESS) ;
    }


    //生成图片验证码
    @GetMapping("/generateValidateCode")
    public Result<ValidateCodeVo> generateValidateCode() {
        ValidateCodeVo validateCodeVo = validateCodeService.generateValidateCode();
       return Result.build(validateCodeVo, ResultCodeEnum.SUCCESS);
    }

    //用户登录
    @Operation(summary = "登录的方法")
    @PostMapping("/login")
    public Result login(@RequestBody LoginDto loginDto) {
        LoginVo loginVo = sysUserService.login(loginDto);
        return Result.build(loginVo, ResultCodeEnum.SUCCESS);
    }
    /**
     * 获取当前登录的用户信息
     */
    /*
    @GetMapping("/getUserInfo")
    public Result getUserInfo(@RequestHeader(name = "token") String token) {
        //1.从请求头获取token
        //2.根据token查询redis获取用户信息
        SysUser sysUser = sysUserService.getUserInfo(token);
        //3.用户信息返回
        return Result.build(sysUser, ResultCodeEnum.SUCCESS);
    }*/
    @GetMapping("/getUserInfo")
    public Result getUserInfo(@RequestHeader(name = "token") String token) {
        return Result.build(AuthContextUtil.get(), ResultCodeEnum.SUCCESS);
    }

    /**
     * 用户退出登录
     */
    @GetMapping("/logout")
    public Result logout(@RequestHeader(name = "token") String token) {
        //1.根据token删除redis中的用户信息
        sysUserService.logout(token);
        //2.返回结果
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }




}
