package com.atguigu.spzx.manager.controller;

import com.annotation.spzx.common.log.annotation.Log;
import com.annotation.spzx.common.log.enums.OperatorType;
import com.atguigu.spzx.manager.service.BrandService;
import com.atguigu.spzx.model.entity.product.Brand;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author
 * @Description:
 */
@RestController
@RequestMapping(value="/admin/product/brand")
public class BrandController {
    @Autowired
    private  BrandService brandService;


    /**
     * 分页查询品牌信息
     */
    @Log(title = "品牌管理:列表", businessType = 0, operatorType=OperatorType.OTHER)
    @GetMapping("/{page}/{limit}")
    public Result list(@PathVariable("page") Integer page, @PathVariable("limit") Integer limit) {

       PageInfo<Brand> pageInfo = brandService.findByPage(page, limit);
       return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

    /**
     * 新增品牌信息
     */
    @PostMapping("save")
    public Result save(@RequestBody Brand brand) {
        brandService.save(brand);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }

    /**
     * 修改品牌信息
     * @param brand
     * @return
     */
    @PutMapping("update")
    public Result update(@RequestBody Brand brand) {
        brandService.update(brand);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }

    /**
     * 删除品牌信息
     * @param id
     * @return
     */
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable("id") Long id) {
        brandService.remove(id);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }

    /**
     * 查询所有品牌
     */
    @GetMapping("findAll")
    public Result findAll() {
        List<Brand> list = brandService.findAll();
        return Result.build(list , ResultCodeEnum.SUCCESS) ;
    }


}
