package com.yj.service;

import com.yj.domain.ResponseResult;
import com.yj.domain.entity.User;

public interface LoginService {
    ResponseResult login(User user);

    ResponseResult logout();
}
