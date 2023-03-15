package com.yj.domain.vo.menu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Description:
 * @Package com.yj.domain.vo.menu
 * @Author yJade
 * @Date 2023-03-14 13:20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleMenuTreeSelectVo {
    /**
     * 所有菜单的菜单树
     */
    private List<MenuTreeVo> menus;
    /**
     * 角色拥有的菜单权限id列表
     */
    private List<Long> checkedKeys;
}
