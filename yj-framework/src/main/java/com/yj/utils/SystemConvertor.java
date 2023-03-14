package com.yj.utils;

import com.yj.domain.entity.Menu;
import com.yj.domain.vo.menu.MenuTreeVo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Description:
 * @Package com.yj.utils
 * @Author yJade
 * @Date 2023-03-13 22:10
 */
public class SystemConvertor {
    private SystemConvertor(){}

    public static List<MenuTreeVo> converterToMenuTreeVo(List<Menu> MenuTree) {
        ArrayList<MenuTreeVo> menuTreeVos = new ArrayList<>();
        for (Menu menu : MenuTree) {
            MenuTreeVo menuTreeVo = new MenuTreeVo(menu.getId(), menu.getMenuName(), menu.getParentId(), new ArrayList<>());
            // 递归调用
            if (Objects.nonNull(menu.getChildren())) {
                menuTreeVo.setChildren(converterToMenuTreeVo(menu.getChildren()));
            }
            menuTreeVos.add(menuTreeVo);
        }

        return menuTreeVos;
    }
}
