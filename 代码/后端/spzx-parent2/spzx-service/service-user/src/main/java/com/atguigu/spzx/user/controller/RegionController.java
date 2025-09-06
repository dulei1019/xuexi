package com.atguigu.spzx.user.controller;

import com.atguigu.spzx.model.entity.base.Region;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.user.service.RegionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author
 * @Description:
 */
@Tag(name = "获取地区接口")
@RestController
@RequestMapping("/api/user/region")
public class RegionController {
    @Autowired
    private RegionService regionService;
    @Operation(summary = "获取地区")
    @GetMapping("/findByParentCode/{parentCode}")
    public Result findByParentCode(@PathVariable("parentCode") String parentCode){
        List<Region> list = regionService.findByParentCode(parentCode);

        return Result.build(list, ResultCodeEnum.SUCCESS);

    }



}
