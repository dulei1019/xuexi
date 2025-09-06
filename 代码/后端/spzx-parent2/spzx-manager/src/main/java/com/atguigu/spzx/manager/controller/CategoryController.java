package com.atguigu.spzx.manager.controller;

import com.atguigu.spzx.manager.service.CategoryService;
import com.atguigu.spzx.model.entity.product.Category;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author
 * @Description:
 */
@RestController
@RequestMapping(value="/admin/product/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 导入数据
     */
    @PostMapping("/importData")
    public Result importData(MultipartFile file){
        //获取上传文件
        categoryService.importData(file);
        return Result.build(null, ResultCodeEnum.SUCCESS);

    }

    /**
     * 导出数据
     * HttpServletResponse  需要使用里面的方法，将数据写到response中
     */
    @GetMapping("/exportData")
    public void exportData(HttpServletResponse response ) {
        categoryService.exportData(response);

    }



    //分类列表，每次只查询一层数据
    @GetMapping("/findCategoryList/{id}")
    public Result findCategoryList(@PathVariable("id") Long id){
        List<Category> list = categoryService.findCategoryList(id);
        return Result.build(list, ResultCodeEnum.SUCCESS);
    }



}
