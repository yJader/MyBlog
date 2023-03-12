package com.yj.controller;

import com.yj.annotation.SystemLog;
import com.yj.domain.ResponseResult;
import com.yj.domain.entity.User;
import com.yj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static net.sf.jsqlparser.util.validation.metadata.NamedObject.user;

/**
 * @description: TODO 将接口改为需要token验证
 * @author: YJader
 * @date: 2023/2/16 17:23
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/userInfo")
    public ResponseResult userInfo(){
        return userService.userInfo();
    }

    @PutMapping("/userInfo")
    @SystemLog(businessName = "更新用户信息")
    public ResponseResult updateUserInfo(@RequestBody User user) {
        return userService.updateUserInfo(user);
    }

    @PostMapping("/register")
    public ResponseResult register(@RequestBody User user) {
        return userService.register(user);
    }
}
