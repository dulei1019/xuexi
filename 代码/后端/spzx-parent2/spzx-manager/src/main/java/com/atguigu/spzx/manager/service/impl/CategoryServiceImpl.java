package com.atguigu.spzx.manager.service.impl;

import com.alibaba.excel.EasyExcel;
import com.atguigu.spzx.common.exception.GuiguException;
import com.atguigu.spzx.manager.listener.ExcelListener;
import com.atguigu.spzx.manager.mapper.CategoryMapper;
import com.atguigu.spzx.manager.service.CategoryService;
import com.atguigu.spzx.model.entity.product.Category;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.model.vo.product.CategoryExcelVo;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author
 * @Description:
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    /**
     *分类列表，每次查询一层数据
     */
    @Override
    @Transactional
    public List<Category> findCategoryList(Long id) {
        //1,根据id条件值进行查询
        List<Category> categoryList = categoryMapper.seleteCategoryByParentId(id);
        //2.遍历返回的list集合，判断每个分类是否有下一次分类。如果有设置hsaChildren=true
        if (categoryList != null && categoryList.size() > 0) {
            categoryList.forEach(category -> {
                int count = categoryMapper.selectCountByParentId(category.getId());
                if (count > 0) {
                    category.setHasChildren(true);
                }else {
                    category.setHasChildren(false);
                }
            });
        }

        return categoryList;
    }
    /**
     * 导出数据
     */
    @Override
    @Transactional
    public void exportData(HttpServletResponse response) {
        try{
            //1.设置响应体信息和其他信息
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");

            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode("分类数据", "UTF-8");

            response.setHeader("Content-Disposition","attachment;filename="+fileName+".xlsx");
            //2.调用mapper方法查询所有分类，返回list集合
            List<Category> categoryList = categoryMapper.findAll();

            //最终list集合
            ArrayList<CategoryExcelVo> categoryExcelVoList = new ArrayList<>();

            //List<Category> ---->>> List<CategoryExcelVo>
            for (Category category : categoryList) {
                CategoryExcelVo categoryExcelVo = new CategoryExcelVo();
                //使用BeanUtils工具类进行属性拷贝
                BeanUtils.copyProperties(category,categoryExcelVo);
                categoryExcelVoList.add(categoryExcelVo);

            }
            //3 调用EasyExcel的
            EasyExcel.write(response.getOutputStream(), CategoryExcelVo.class)
                    .sheet("分类数据").doWrite(categoryExcelVoList);

        }catch (Exception e){
            e.printStackTrace();
            throw new GuiguException(ResultCodeEnum.DATA_ERROR);
        }


    }

    /**
     * 导入数据
     */
    @Override
    public void importData(MultipartFile file) {
        //TODO 监听器

        ExcelListener<CategoryExcelVo> excelListener = new ExcelListener(categoryMapper);
        try {
            EasyExcel.read(file.getInputStream(), CategoryExcelVo.class, excelListener)
                    .sheet().doRead();
            //调用mapper方法批量插入数据
        } catch (IOException e) {
            e.printStackTrace();
            throw new GuiguException(ResultCodeEnum.DATA_ERROR);
        }

    }
}
















