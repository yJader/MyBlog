package com.yj.service;

import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yj.domain.entity.Role;

/**
 * 角色信息表(Role)表服务接口
 *
 * @author makejava
 * @since 2023-02-24 21:51:13
 */
public interface RoleService extends IService<Role> {

    List<String> selectRoleKeyByUserId(Long id);
}
