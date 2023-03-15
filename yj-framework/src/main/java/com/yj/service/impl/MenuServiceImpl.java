package com.yj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yj.constants.MenuConstants;
import com.yj.constants.SystemConstants;
import com.yj.domain.entity.Menu;
import com.yj.mapper.MenuMapper;
import com.yj.service.MenuService;
import com.yj.utils.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 菜单权限表(Menu)表服务实现类
 *
 * @author makejava
 * @since 2023-02-24 21:48:33
 */
@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Override
    public List<String> selectPermsByUserId(Long userId) {
        // 如果是管理员, 直接返回所有的菜单
        if (userId == 1L) {
            LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.in(Menu::getMenuType, SystemConstants.MENU, SystemConstants.BUTTON);
            queryWrapper.in(Menu::getStatus, SystemConstants.STATUS_NORMAL);
            queryWrapper.select(Menu::getPerms);
            List<Menu> menus = list(queryWrapper);
            return menus.stream().map(Menu::getPerms).collect(Collectors.toList());
        }
        // 否则查询用户对应的role->role对应的menu
        return getBaseMapper().selectPermsByUserId(userId);
    }

    @Override
    public List<Menu> selectRouterMenuTreeByUserId(Long userId) {
        MenuMapper menuMapper = getBaseMapper();
        List<Menu> menus;
        // 判断是否是管理员
        if (SecurityUtils.isAdmin(userId)) {
            // 如果是, 返回所有符合要求的Menu
             menus = menuMapper.selectAllRouterMenu();
        } else {
            menus = menuMapper.selectRouterMenuByUserId(userId);
        }
        // 构建menuTree
        List<Menu> menuTree = buildMenuTree(menus);
        return menuTree;
    }

    @Override
    public List<Menu> selectRouterMenuTreeByRoleId(Long roleId) {
        MenuMapper menuMapper = getBaseMapper();
        List<Menu> menus;
        // 查询角色对应role
        if (MenuConstants.SUPER_ADMINISTRATOR_ROLE_ID.equals(roleId)) {
            // 返回所有正常的菜单
            menus = menuMapper.selectAllRouterMenu();
        } else {
            menus = menuMapper.selectRouterMenuByRoleId(roleId);
        }

        // 建menuTree
        List<Menu> menuTree = buildMenuTree(menus);
        return menuTree;
    }

    @Override
    public List<Menu> selectMenuList(Menu menu) {
        LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
        // 模糊查询
        queryWrapper.eq(StringUtils.hasText(menu.getMenuName()), Menu::getMenuName, menu.getMenuName());
        queryWrapper.eq(StringUtils.hasText(menu.getStatus()), Menu::getStatus, menu.getStatus());
        // 排序
        queryWrapper.orderByAsc(Menu::getParentId, Menu::getOrderNum);
        return list(queryWrapper);
    }

    @Override
    public boolean hasChild(Long menuId) {
        LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Menu::getParentId, menuId);
        // 直接使用count
        return count(queryWrapper) != 0;
    }

//    /**
//     * @description: 获取一个指定以指定id为parent的菜单树
//     * @param: menus
//     * @param: parentId
//     * @return: java.util.List<com.yj.domain.entity.Menu>
//     * @author: YJader
//     * @date: 2023/2/25 18:49
//     */
//    @Override
//    public List<Menu> buildMenuTree(List<Menu> menus, Long parentId) {
//        // menuId--映射到-->menus集合的位置index, 加速查找
//        HashMap<Long, Integer> idIndexMap = new HashMap<>(menus.size());
//        for (int i = 0; i <menus.size(); i++) {
//            idIndexMap.put(menus.get(i).getId(), i);
//        }
//
//        ArrayList<Menu> menuTree = new ArrayList<>();
//        // 更新子菜单
//        for (Menu menu : menus) {
//            // 第一层菜单
//            if (parentId.equals(menu.getParentId())) {
//                menuTree.add(menu);
//            } else {
//                Menu parentMenu = menus.get(idIndexMap.get(menu.getParentId()));
//                // 如果
//                if (Objects.isNull(parentMenu.getChildren())) {
//                    parentMenu.setChildren(new ArrayList<>());
//                }
//                parentMenu.getChildren().add(menu);
//            }
//        }
//
//        return menuTree;
//    }

    /**
     * @description: 重构:返回一个树型菜单
     * @param: menus
     * @return: java.util.List<com.yj.domain.entity.Menu>
     * @author: YJader
     * @date: 2023/3/14 20:01
     */
    @Override
    public List<Menu> buildMenuTree(List<Menu> menus) {
        // menuId--映射到-->menus集合的位置index, 加速查找
        HashMap<Long, Integer> idIndexMap = new HashMap<>(menus.size());
        for (int i = 0; i <menus.size(); i++) {
            idIndexMap.put(menus.get(i).getId(), i);
        }

        ArrayList<Menu> menuTree = new ArrayList<>();
        // 更新子菜单
        for (Menu menu : menus) {
            // 父菜单不存在, 为第一层菜单
            if (!idIndexMap.containsKey(menu.getParentId())) {
                menuTree.add(menu);
            } else {
                Menu parentMenu = menus.get(idIndexMap.get(menu.getParentId()));
                // 如果
                if (Objects.isNull(parentMenu.getChildren())) {
                    parentMenu.setChildren(new ArrayList<>());
                }
                parentMenu.getChildren().add(menu);
            }
        }

        return menuTree;
    }

    @Override
    public List<Long> selectMenuIdListByRoleId(Long roleId) {
        return getBaseMapper().selectMenuIdListByRoleId(roleId);
    }


}

