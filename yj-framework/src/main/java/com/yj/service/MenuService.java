package com.yj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yj.domain.entity.Menu;

import java.util.List;

/**
 * 菜单权限表(Menu)表服务接口
 *
 * @author makejava
 * @since 2023-02-24 21:48:33
 */
public interface MenuService extends IService<Menu> {

    List<String> selectPermsByUserId(Long id);

    /**
     * @description: 通过用户id获取其菜单权限(以菜单树形式呈现)
     * @param: userId
     * @return: java.util.List<com.yj.domain.entity.Menu>
     * @author: YJader
     * @date: 2023/3/14 23:45
     */
    List<Menu> selectRouterMenuTreeByUserId(Long userId);

    /**
     * @description: 通过角色id获取其菜单权限(以菜单树形式呈现)
     * @description: 理解错误接口含义, 此方法弃用, 但是暂不删除
     * @param: roleId
     * @return: java.util.List<com.yj.domain.entity.Menu>
     * @author: YJader
     * @date: 2023/3/14 23:44
     */
    List<Menu> selectRouterMenuTreeByRoleId(Long roleId);

    List<Menu> selectMenuList(Menu menu);

    boolean hasChild(Long menuId);

//    List<Menu> buildMenuTree(List<Menu> menus, Long parentId);
    List<Menu> buildMenuTree(List<Menu> menus);


    List<Long> selectMenuIdListByRoleId(Long roleId);
}
