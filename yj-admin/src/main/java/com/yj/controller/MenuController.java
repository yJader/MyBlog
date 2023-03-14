package com.yj.controller;

import com.yj.domain.ResponseResult;
import com.yj.domain.dto.menu.AddMenuDto;
import com.yj.domain.dto.menu.MenuListDto;
import com.yj.domain.entity.Menu;
import com.yj.domain.vo.menu.MenuDetailVo;
import com.yj.domain.vo.menu.MenuTreeVo;
import com.yj.domain.vo.menu.MenuVo;
import com.yj.service.MenuService;
import com.yj.utils.BeanCopyUtils;
import com.yj.utils.SystemConvertor;
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
    public ResponseResult add(@RequestBody AddMenuDto addMenuDto) {
        Menu menu = BeanCopyUtils.copyBean(addMenuDto, Menu.class);
        menuService.save(menu);
        return ResponseResult.okResult();
    }

    @GetMapping("/{id}")
    public ResponseResult<MenuDetailVo> detail(@PathVariable Long id) {
        Menu menu = menuService.getById(id);
        MenuDetailVo menuDetailVo = BeanCopyUtils.copyBean(menu, MenuDetailVo.class);
        return ResponseResult.okResult(menuDetailVo);
    }

    @PutMapping
    public ResponseResult update(@RequestBody AddMenuDto addMenuDto) {
        // TODO 是否需要数据验证 (如parentId是否存在(虽然前端已经做过了))
        Menu menu = BeanCopyUtils.copyBean(addMenuDto, Menu.class);
        if (menu.getId().equals(menu.getParentId())) {
            return ResponseResult.errorResult(500, "修改菜单'" + menu.getMenuName() + "'失败，上级菜单不能选择自己");
        }
        menuService.updateById(menu);
        return ResponseResult.okResult();
    }

    @DeleteMapping("/{menuId}")
    public ResponseResult delete(@PathVariable Long menuId) {
        if (menuService.hasChild(menuId)) {
            return ResponseResult.errorResult(500, "该菜单存在子菜单,不允许删除");
        }
        menuService.removeById(menuId);
        return ResponseResult.okResult();
    }

    @GetMapping("/treeselect")
    public ResponseResult<List<MenuTreeVo>> selectTree() {
        List<Menu> menuList = menuService.list();
        List<Menu> menuTree = menuService.buildMenuTree(menuList, 0L);
        List<MenuTreeVo> menuTreeVos = SystemConvertor.converterToMenuTreeVo(menuTree);
        return ResponseResult.okResult(menuTreeVos);
    }
}
