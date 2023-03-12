package com.yj.controller;

import com.yj.domain.ResponseResult;
import com.yj.domain.dto.MenuDto;
import com.yj.domain.dto.MenuListDto;
import com.yj.domain.entity.Menu;
import com.yj.domain.vo.MenuVo;
import com.yj.service.MenuService;
import com.yj.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description:
 * @Package com.yj.controller
 * @Author yJade
 * @Date 2023-03-12 23:48
 */
@RestController
@RequestMapping("/system/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/list")
    public ResponseResult<List<MenuVo>> list(MenuListDto menuListDto) {
        Menu menu = BeanCopyUtils.copyBean(menuListDto, Menu.class);
        List<Menu> menus= menuService.selectMenuList(menu);
        List<MenuVo> menuVos = BeanCopyUtils.copyBeanList(menus, MenuVo.class);
        return ResponseResult.okResult(menuVos);
    }

    @PostMapping("")
    public ResponseResult add(@RequestBody MenuDto menuDto) {
        Menu menu = BeanCopyUtils.copyBean(menuDto, Menu.class);
        menuService.save(menu);
        return ResponseResult.okResult();
    }

    // TODO 修改菜单
}
