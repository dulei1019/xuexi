package com.atguigu.spzx.manager.service.impl;

import com.atguigu.spzx.manager.mapper.BrandMapper;
import com.atguigu.spzx.manager.service.BrandService;
import com.atguigu.spzx.model.entity.product.Brand;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author
 * @Description:
 */
@Service
public  class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandMapper brandMapper;

    @Override
    public PageInfo<Brand> findByPage(Integer page, Integer limit) {
        //分页查询品牌信息
        PageHelper.startPage(page, limit);
        List<Brand> brandList = brandMapper.findByPage() ;
        return new PageInfo(brandList);
    }

    /**
     * 新增品牌信息
     * @param brand
     */
    @Override
    public void save(Brand brand) {
        brandMapper.save(brand);
    }

    /**
     * 修改品牌信息
     * @param brand
     */
    @Override
    public void update(Brand brand) {
        brandMapper.update(brand);
    }

    /**
     * 删除品牌信息
     * @param id
     */
    @Override
    public void remove(Long id) {
        brandMapper.remove(id);
    }

    /**
     * 查询所有品牌信息
     * @return
     */
    @Override
    public List<Brand> findAll() {

        return brandMapper.findByPage();
    }
}
