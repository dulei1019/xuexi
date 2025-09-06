package com.atguigu.spzx.manager.utils;

import com.atguigu.spzx.model.entity.system.SysMenu;

import java.util.ArrayList;
import java.util.List;

/**
 *封装树形菜单数据
 */
public class MenuHelper {


    //递归实现封装过程
    public static List<SysMenu> buildTree(List<SysMenu> sysMenuList) {
        //TODO 完成封装过程  sysMenuList所有菜单集合
        //创建list集合，用于封装最终的数据
        List<SysMenu> trees = new ArrayList<>();
        //遍历所有菜单
        for(SysMenu sysMenu : sysMenuList){
            //找到第一层的入口，第一层菜单
            //条件：parent——id == 0
            if (sysMenu.getParentId().longValue() == 0){
                //根据第一层找下层数据，使用递归
                //写方法实现找下层过程
                //方法里面传递2个实参，第一个：当前菜单，第二个：所有菜单
                trees.add(findChildren(sysMenu,sysMenuList));
            }
        }


        return trees;
    }
    //递归去查找下层的菜单信息
    private static SysMenu findChildren(SysMenu sysMenu, List<SysMenu> sysMenuList) {
        //SysMenu有属性，private List<SysMenu> children; 封装下一层数据
        sysMenu.setChildren(new ArrayList<>());
        //递归查询
        //sysMenu的id值和sysMenuList的parent_id值相同
        for (SysMenu menu : sysMenuList) {
           //判断当前菜单的id值和sysMenuList的parent_id值相同
            if (sysMenu.getId().longValue()==menu.getParentId().longValue()){
                //menu是下一层菜单,进行封装
                sysMenu.getChildren().add(findChildren(menu,sysMenuList));
            }
        }
        return sysMenu;
    }
}
