package com.atguigu.spzx.manager.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author
 * @Description:
 */
@Mapper
public interface SysRoleUserMapper {
    void deleteByUserId(Long userId);

    void doAssign(Long userId, Long roleId);

    List<Long> selectRoleIdByUserId(Long userId);
}
