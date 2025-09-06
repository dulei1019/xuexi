package com.atguigu.spzx.user.controller;

import com.atguigu.spzx.model.entity.user.UserAddress;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.user.service.UserAddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author
 * @Description:
 */
@Tag(name = "用户地址接口")
@RestController
@RequestMapping(value="/api/user/userAddress")
public class UserAddressController {

    @Autowired
    private UserAddressService userAddressService;

    /**
     * 获取用户地址列表
     * @return
     */
    @Operation(summary = "获取用户地址列表")
    @GetMapping("auth/findUserAddressList")
    public Result<List<UserAddress>> findUserAddressList() {
        List<UserAddress> list = userAddressService.findUserAddressList();
        return Result.build(list , ResultCodeEnum.SUCCESS) ;
    }

    /**
     * 新增地址
     * @param userAddress
     * @return
     */
    @Operation(summary = "新增地址")
    @PostMapping("/auth/save")
    public Result save(@RequestBody UserAddress userAddress) {
        userAddressService.save(userAddress);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }


    /**
     * * 删除地址
     */
    @Operation(summary = "删除地址")
    @DeleteMapping("/auth/removeById/{id}")
    public Result delete(@PathVariable Long id) {
        userAddressService.delete(id);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }





    /**
     * 根据id获取地址信息
     * @param id
     * @return
     */
    @Operation(summary = "获取地址信息")
    @GetMapping("getUserAddress/{id}")
    public UserAddress getUserAddress(@PathVariable Long id) {
        return userAddressService.getById(id);
    }

}
