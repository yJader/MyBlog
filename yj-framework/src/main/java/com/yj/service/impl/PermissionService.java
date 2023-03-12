package com.yj.service.impl;

import com.yj.utils.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 自定义权限判断
 * @Package com.yj.service.impl
 * @Author yJade
 * @Date 2023-03-07 22:57
 */
@Service("ps")
public class PermissionService {
    /**
     * 判断当前用户是否具有permission
     * @param permission 要判断的权限
     * @return
     */
    public boolean hasPermission(String permission) {
        // 如果是超级管理员, 不需要判断 返回true
        if (SecurityUtils.isAdmin()) {
            return true;
        }

        // 否则, 需要获取用户的权限信息, 判断是否拥有权限
        List<String> permissions = SecurityUtils.getLoginUser().getPermissions();
        return permissions.contains(permission);
    }
}
