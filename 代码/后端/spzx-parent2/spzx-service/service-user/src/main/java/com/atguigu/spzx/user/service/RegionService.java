package com.atguigu.spzx.user.service;

import com.atguigu.spzx.model.entity.base.Region;

import java.util.List;

/**
 * @Author
 * @Description:
 */
public interface RegionService {
    List<Region> findByParentCode(String parentCode);
}
