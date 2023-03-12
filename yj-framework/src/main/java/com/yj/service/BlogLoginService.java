package com.yj.service;

import com.yj.domain.ResponseResult;
import com.yj.domain.entity.User;

/**
 * @Author yJade
 * @Date 2023-02-13 23:16
 * @Package com.yj.controller
 * @Description:
 */
public interface BlogLoginService {
    ResponseResult login(User user);

    ResponseResult logout();
}
