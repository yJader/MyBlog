package com.yj.service;

import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yj.domain.entity.Menu;

/**
 * 菜单权限表(Menu)表服务接口
 *
 * @author makejava
 * @since 2023-02-24 21:48:33
 */
public interface MenuService extends IService<Menu> {

    List<String> selectPermsByUserId(Long id);

    List<Menu> selectRouterMenuTreeByUserId(Long userId);

    List<Menu> selectMenuList(Menu menu);
}
