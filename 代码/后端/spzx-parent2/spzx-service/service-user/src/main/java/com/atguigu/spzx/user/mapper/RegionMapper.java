package com.atguigu.spzx.user.mapper;

import com.atguigu.spzx.model.entity.base.Region;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author
 * @Description:
 */
@Mapper
public interface RegionMapper {
    List<Region> findByParentCode(String parentCode);
}
