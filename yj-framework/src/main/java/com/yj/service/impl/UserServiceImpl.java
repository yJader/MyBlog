package com.yj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yj.domain.ResponseResult;
import com.yj.domain.dto.user.AddUserDto;
import com.yj.domain.dto.user.UpdateUserDto;
import com.yj.domain.entity.User;
import com.yj.domain.entity.UserRole;
import com.yj.domain.vo.PageVo;
import com.yj.domain.vo.UserInfoVo;
import com.yj.domain.vo.user.UserListVo;
import com.yj.enums.AppHttpCodeEnum;
import com.yj.exception.SystemException;
import com.yj.mapper.UserMapper;
import com.yj.service.UserRoleService;
import com.yj.service.UserService;
import com.yj.utils.BeanCopyUtils;
import com.yj.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户表(User)表服务实现类
 *
 * @author makejava
 * @since 2023-02-15 17:54:09
 */
@Service("UserService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRoleService userRoleService;

    @Override
    public ResponseResult userInfo() {
        //获取当前用户id
        Long userId = SecurityUtils.getUserId();
        //根据用户id查询用户信息
        User user = getById(userId);
        //封装成UserInfoVo
        UserInfoVo vo = BeanCopyUtils.copyBean(user, UserInfoVo.class);
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
        if (!StringUtils.hasText(user.getUserName())) {
            throw new SystemException(AppHttpCodeEnum.USERNAME_NOT_NULL);
        }
        if (!StringUtils.hasText(user.getPassword())) {
            throw new SystemException(AppHttpCodeEnum.PASSWORD_NOT_NULL);
        }
        if (!StringUtils.hasText(user.getEmail())) {
            throw new SystemException(AppHttpCodeEnum.EMAIL_NOT_NULL);
        }
        if (!StringUtils.hasText(user.getNickName())) {
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

    @Override
    public PageVo<UserListVo> selectUserPage(User user, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.hasText(user.getUserName()), User::getUserName, user.getUserName());
        queryWrapper.eq(StringUtils.hasText(user.getPhonenumber()), User::getPhonenumber, user.getPhonenumber());
        queryWrapper.eq(StringUtils.hasText(user.getStatus()), User::getStatus, user.getStatus());

        Page<User> userPage = new Page<>(pageNum, pageSize);
        page(userPage, queryWrapper);
        List<User> users = userPage.getRecords();
        List<UserListVo> userListVos = BeanCopyUtils.copyBeanList(users, UserListVo.class);

        return new PageVo<>(userListVos, userPage.getTotal());
    }

    @Override
    public void addUser(AddUserDto addUserDto) {
        // 数据验证
        if (isExist(addUserDto.getUserName(), User::getUserName)) {
            throw new SystemException(AppHttpCodeEnum.USERNAME_EXIST);
        }
        if (isExist(addUserDto.getPhonenumber(), User::getPhonenumber)) {
            throw new SystemException(AppHttpCodeEnum.PHONENUMBER_EXIST);
        }
        if (isExist(addUserDto.getEmail(), User::getEmail)) {
            throw new SystemException(AppHttpCodeEnum.EMAIL_EXIST);
        }
        // 插入用户
        User user = BeanCopyUtils.copyBean(addUserDto, User.class);
        //密码加密
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        save(user);

        // 保存对应角色
        List<UserRole> userRoleList = addUserDto.getRoleIds().stream()
                .map(roleId -> new UserRole(user.getId(), roleId))
                .collect(Collectors.toList());
        userRoleService.saveBatch(userRoleList);
    }

    @Override
    public void updateUser(UpdateUserDto updateUserDto) {
        User user = BeanCopyUtils.copyBean(updateUserDto, User.class);
        updateById(user);

        // 删除相关role
        LambdaQueryWrapper<UserRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserRole::getUserId, updateUserDto.getId());
        userRoleService.remove(queryWrapper);
        // 重新赋role
        List<UserRole> userRoles = updateUserDto.getRoleIds().stream()
                .map(roleId -> new UserRole(user.getId(), roleId))
                .collect(Collectors.toList());
        userRoleService.saveBatch(userRoles);
    }

    private boolean isExist(String variable, SFunction<User, ?> function) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(function, variable);

        return count(queryWrapper) > 0;
    }
}

