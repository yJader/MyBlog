package com.yj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yj.constants.SystemConstants;
import com.yj.domain.entity.LoginUser;
import com.yj.domain.entity.User;
import com.yj.mapper.MenuMapper;
import com.yj.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @Author yJade
 * @Date 2023-02-13 23:54
 * @Package com.yj.service.impl
 * @Description:
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查询用户信息
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<User>();
        queryWrapper.eq(User::getUserName, username);
        User user = userMapper.selectOne(queryWrapper);
        //判断是否查到用户, 如果没有查到 抛出异常
        if(Objects.isNull(user)) {
            throw new RuntimeException("用户不存在");
        }

        // 查询用户权限信息并封装
        // 如果是后台用户才进行权限添加
        if (SystemConstants.ADMIN.equals(user.getType())) {
            List<String> perms = menuMapper.selectPermsByUserId(user.getId());
            return new LoginUser(user, perms);
        }

        //封装为LoginUser
        return new LoginUser(user, null);
    }
}
