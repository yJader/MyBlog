package com.yj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yj.domain.ResponseResult;
import com.yj.domain.dto.user.AddUserDto;
import com.yj.domain.dto.user.UpdateUserDto;
import com.yj.domain.entity.User;
import com.yj.domain.vo.PageVo;
import com.yj.domain.vo.user.UserListVo;

/**
 * 用户表(User)表服务接口
 *
 * @author makejava
 * @since 2023-02-15 17:54:09
 */
public interface UserService extends IService<User> {

    ResponseResult userInfo();

    ResponseResult updateUserInfo(User user);

    ResponseResult register(User user);

    PageVo<UserListVo> selectUserPage(User user, Integer pageNum, Integer pageSize);

    /**
     * @description: 添加用户及其对应角色
     * @param: addUserDto
     * @return: void
     * @author: YJader
     * @date: 2023/3/15 18:01
     */
    void addUser(AddUserDto addUserDto);

    void updateUser(UpdateUserDto updateUserDto);
}
