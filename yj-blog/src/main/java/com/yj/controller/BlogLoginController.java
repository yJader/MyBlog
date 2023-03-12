package com.yj.controller;

import com.yj.domain.ResponseResult;
import com.yj.domain.dto.LoginUserDTO;
import com.yj.domain.entity.User;
import com.yj.enums.AppHttpCodeEnum;
import com.yj.exception.SystemException;
import com.yj.service.BlogLoginService;
import com.yj.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author yJade
 * @Date 2023-02-13 23:04
 * @Package com.yj.controller
 * @Description:
 */
@RestController
public class BlogLoginController {

    @Autowired
    private BlogLoginService blogLoginService;

    @PostMapping("/login")
    public ResponseResult login(@RequestBody LoginUserDTO loginUserDTO) {
        User user = BeanCopyUtils.copyBean(loginUserDTO, User.class);
        return blogLoginService.login(user);
    }

    @PostMapping("/logout")
    public ResponseResult logout(HttpServletRequest request){
        return blogLoginService.logout();
    }
}
