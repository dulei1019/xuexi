package com.atguigu.spzx.manager.service;

import com.atguigu.spzx.model.entity.product.Brand;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Author
 * @Description:
 */
public interface BrandService {
    /**
     *
     */
    PageInfo<Brand> findByPage(Integer page, Integer limit);

    /**
     *新增品牌
     */
    void save(Brand brand);

    /**
     * 修改品牌
     */
    void update(Brand brand);

    /**
     * 删除品牌
     * @param id
     */
    void remove(Long id);

    /**
     * 查询所有品牌
     */
    List<Brand> findAll();
}
