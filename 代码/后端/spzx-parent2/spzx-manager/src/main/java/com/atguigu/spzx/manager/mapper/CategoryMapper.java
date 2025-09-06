package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.entity.product.Category;
import com.atguigu.spzx.model.vo.product.CategoryExcelVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author
 * @Description:
 */
@Mapper
public interface CategoryMapper {

    List<Category> seleteCategoryByParentId(Long id);

    int selectCountByParentId(Long id);

    /**
     * 查询所有分类
     * @return
     */
    List<Category> findAll();

    /**
     * 批量插入
     * @param categoryList
     * @param <T>
     */
    <T> void batchInsert(List<CategoryExcelVo> categoryList);
}
