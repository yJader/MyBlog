package com.yj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yj.domain.dto.role.AddRoleDto;
import com.yj.domain.dto.role.UpdateRoleDto;
import com.yj.domain.entity.Role;
import com.yj.domain.entity.RoleMenu;
import com.yj.domain.vo.PageVo;
import com.yj.domain.vo.role.RoleListVo;
import com.yj.mapper.RoleMapper;
import com.yj.service.RoleMenuService;
import com.yj.service.RoleService;
import com.yj.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色信息表(Role)表服务实现类
 *
 * @author makejava
 * @since 2023-02-24 21:51:13
 */
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RoleMenuService roleMenuService;

    @Override
    public List<String> selectRoleKeyByUserId(Long id) {
        return null;
    }

    @Override
    public void insertRole(AddRoleDto addRoleDto) {
        // 添加角色
        Role role = BeanCopyUtils.copyBean(addRoleDto, Role.class);
        save(role);

        // 添加角色对应菜单
        // mybatis plus的save会自动为role赋插入后的id
        insertRoleMenu(role.getId(), addRoleDto.getMenuIds());
    }

    @Override
    public void updateRole(UpdateRoleDto updateRoleDto) {
        Role role = BeanCopyUtils.copyBean(updateRoleDto, Role.class);
        updateById(role);
        roleMenuService.deleteRoleMenuByRoleId(updateRoleDto.getId());
        insertRoleMenu(updateRoleDto.getId(), updateRoleDto.getMenuIds());
    }

    @Override
    public PageVo<RoleListVo> roleList(Integer pageNum, Integer pageSize, String roleName, String status) {
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        // 针对角色名称进行模糊查询
        queryWrapper.like(StringUtils.hasText(roleName), Role::getRoleName, roleName);
        // 对状态进行查询
        queryWrapper.eq(StringUtils.hasText(status), Role::getStatus, status);
        // 按照role_sort升序排序
        queryWrapper.orderByAsc(Role::getRoleSort);

        Page<Role> rolePage = new Page<Role>(pageNum, pageSize);
        page(rolePage, queryWrapper);
        List<Role> records = rolePage.getRecords();
        List<RoleListVo> roleListVos = BeanCopyUtils.copyBeanList(records, RoleListVo.class);

        return new PageVo<>(roleListVos, rolePage.getTotal());
    }

    private void insertRoleMenu(Long roleId, List<Long> menuIds) {
        List<RoleMenu> roleMenuList = menuIds.stream()
                .map(menuId -> new RoleMenu(roleId, menuId))
                .collect(Collectors.toList());
        roleMenuService.saveBatch(roleMenuList);
    }
}

