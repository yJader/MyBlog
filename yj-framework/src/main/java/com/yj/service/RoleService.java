package com.yj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yj.domain.dto.role.AddRoleDto;
import com.yj.domain.dto.role.UpdateRoleDto;
import com.yj.domain.entity.Role;
import com.yj.domain.vo.PageVo;
import com.yj.domain.vo.role.RoleListVo;

import java.util.List;

/**
 * 角色信息表(Role)表服务接口
 *
 * @author makejava
 * @since 2023-02-24 21:51:13
 */
public interface RoleService extends IService<Role> {

    PageVo<RoleListVo> roleList(Integer pageNum, Integer pageSize, String roleName, String status);

    List<String> selectRoleKeyByUserId(Long id);

    void insertRole(AddRoleDto addRoleDto);

    void updateRole(UpdateRoleDto roleDto);

    List<Role> listAllRole();

    /**
     * @description: 获取所有状态正常的role
     * @param: null
     * @return: java.util.List<com.yj.domain.entity.Role>
     * @author: YJader
     * @date: 2023/3/15 23:07
     */
    List<Role> selectAllRole();

    List<Long> selectRoleIdByUserId(Long userId);
}
