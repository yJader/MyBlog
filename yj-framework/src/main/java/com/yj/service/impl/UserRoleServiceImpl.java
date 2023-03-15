package com.yj.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yj.domain.entity.UserRole;
import com.yj.mapper.UserRoleMapper;
import com.yj.service.UserRoleService;
import org.springframework.stereotype.Service;

/**
 * 用户和角色关联表(UserRole)表服务实现类
 *
 * @author makejava
 * @since 2023-03-15 19:14:58
 */
@Service("userRoleService")
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}

