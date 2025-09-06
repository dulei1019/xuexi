package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.dto.system.AssginMenuDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author
 * @Description:
 */
@Mapper
public interface SysRoleMenuMapper {


     void updateSysRoleMenuIsHalf(Long id) ;


    List<Long> findSysRoleMenuByRoleId(Long roleId);

    void deleteByRoleId(Long roleId);

    void doAssign(AssginMenuDto assginMenuDto);
}
