package com.yj.service.impl;

import java.util.Date;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yj.domain.ResponseResult;
import com.yj.domain.entity.User;
import com.yj.domain.vo.UserInfoVo;
import com.yj.enums.AppHttpCodeEnum;
import com.yj.exception.SystemException;
import com.yj.mapper.UserMapper;
import com.yj.service.UserService;
import com.yj.utils.BeanCopyUtils;
import com.yj.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 用户表(User)表服务实现类
 *
 * @author makejava
 * @since 2023-02-15 17:54:09
 */
@Service("UserService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public ResponseResult userInfo() {
        //获取当前用户id
        Long userId = SecurityUtils.getUserId();
        //根据用户id查询用户信息
        User user = getById(userId);
        //封装成UserInfoVo
        UserInfoVo vo = BeanCopyUtils.copyBean(user,UserInfoVo.class);
        return ResponseResult.okResult(vo);
    }


    @Override
    public ResponseResult updateUserInfo(User user) {
        updateById(user);
        return null;
    }

    @Override
    public ResponseResult register(User user) {
        //对数据进行非空判断
        // TODO 使用validation进行数据判断
        if(!StringUtils.hasText(user.getUserName())){
            throw new SystemException(AppHttpCodeEnum.USERNAME_NOT_NULL);
        }
        if(!StringUtils.hasText(user.getPassword())){
            throw new SystemException(AppHttpCodeEnum.PASSWORD_NOT_NULL);
        }
        if(!StringUtils.hasText(user.getEmail())){
            throw new SystemException(AppHttpCodeEnum.EMAIL_NOT_NULL);
        }
        if(!StringUtils.hasText(user.getNickName())){
            throw new SystemException(AppHttpCodeEnum.NICKNAME_NOT_NULL);
        }
        //对数据进行重复判断
        if (isExist(user.getUserName(), User::getUserName)) {
            throw new SystemException(AppHttpCodeEnum.USERNAME_EXIST);
        }
        if (isExist(user.getNickName(), User::getNickName)) {
            throw new SystemException(AppHttpCodeEnum.NICKNAME_EXIST);
        }
        if (isExist(user.getPhonenumber(), User::getPhonenumber)) {
            throw new SystemException(AppHttpCodeEnum.PHONENUMBER_EXIST);
        }
        if (isExist(user.getEmail(), User::getEmail)) {
            throw new SystemException(AppHttpCodeEnum.EMAIL_EXIST);
        }
        //密码加密
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //存入数据库
        save(user);

        return ResponseResult.okResult();
    }

    private boolean isExist(String variable, SFunction<User, ?> function) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(function, variable);

        return count(queryWrapper) > 0;
    }
}

