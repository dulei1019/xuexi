package com.atguigu.spzx.user.service.impl;

import com.atguigu.spzx.model.entity.base.Region;
import com.atguigu.spzx.user.mapper.RegionMapper;
import com.atguigu.spzx.user.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author
 * @Description:
 */
@Service
public class RegionServiceImpl implements RegionService {
    @Autowired
    private RegionMapper regionMapper;

    @Override
    public List<Region> findByParentCode(String parentCode) {
        List<Region> list = regionMapper.findByParentCode(parentCode);
        return list;
    }
}
