package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.entity.product.Brand;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author
 * @Description:
 */
@Mapper
public interface BrandMapper {
    List<Brand> findByPage();

    void save(Brand brand);

    void update(Brand brand);

    void remove(Long id);


}
