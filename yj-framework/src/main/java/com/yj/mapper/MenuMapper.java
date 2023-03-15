package com.yj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yj.domain.entity.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 菜单权限表(Menu)表数据库访问层
 *
 * @author makejava
 * @since 2023-02-24 21:48:33
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<String> selectPermsByUserId(@Param("userId") Long id);

    List<Menu> selectAllRouterMenu();

    List<Menu> selectRouterMenuByUserId(Long userId);

    // sql没学好暴露了, 明天再整
    // 错误的, 改改就能用, 继续整!
    List<Menu> selectRouterMenuByRoleId(Long roleId);

    /**
     * @description:
     * @param: roleId 角色id
     * @return: java.util.List<java.lang.Long> 菜单idList
     * @author: YJader
     * @date: 2023/3/15 16:19
     */
    List<Long> selectMenuIdListByRoleId(Long roleId);
}

