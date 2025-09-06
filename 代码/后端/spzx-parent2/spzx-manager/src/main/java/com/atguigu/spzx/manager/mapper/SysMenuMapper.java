package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.entity.system.SysMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author
 * @Description:
 */
@Mapper
public interface SysMenuMapper {
    /**
     * 查询所有菜单信息,返回list集合
     * @return
     */
    List<SysMenu> findAll();

    /**
     * 添加菜单信息
     * @param sysMenu
     */
    void save(SysMenu sysMenu);

    /**
     * 修改菜单信息
     * @param
     * @return
     */
    void update(SysMenu sysMenu);

    /**
     * 根据id删除菜单信息
     * @param id
     */
    void delete(Long id);

    /**
     * 根据id查询是否有子菜单
     * @param id
     * @return
     */
    int selectCountById(Long id);

    /**
     * 根据用户id查询菜单信息
     * @param userId
     * @return
     */
    List<SysMenu> selectListByUserId(Long userId);

    /**
     * 根据子菜单id查询父菜单信息
     * @param parentId
     * @return
     */
    SysMenu selectParentMenu(Long parentId);
}
