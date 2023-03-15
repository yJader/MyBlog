package com.yj.controller;

import com.yj.domain.ResponseResult;
import com.yj.domain.dto.user.AddUserDto;
import com.yj.domain.dto.user.UpdateUserDto;
import com.yj.domain.dto.user.UserListDto;
import com.yj.domain.entity.Role;
import com.yj.domain.entity.User;
import com.yj.domain.vo.PageVo;
import com.yj.domain.vo.user.GetUserDetailVo;
import com.yj.domain.vo.user.UserDetailVo;
import com.yj.domain.vo.user.UserListVo;
import com.yj.service.RoleService;
import com.yj.service.UserRoleService;
import com.yj.service.UserService;
import com.yj.utils.BeanCopyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description:
 * @Package com.yj.controller
 * @Author yJade
 * @Date 2023-03-15 16:38
 */
@Slf4j
@RestController
@RequestMapping("/system/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/list")
    public ResponseResult<PageVo<UserListVo>> list(UserListDto userListDto) {
        User user = BeanCopyUtils.copyBean(userListDto, User.class);
        return ResponseResult.okResult(userService.selectUserPage(user, userListDto.getPageNum(), userListDto.getPageSize()));
    }

    @PostMapping
    public ResponseResult add(@RequestBody AddUserDto addUserDto) {
//        log.info("addUserDto:{}", addUserDto);
        userService.addUser(addUserDto);
        return ResponseResult.okResult();
    }

    @DeleteMapping("/{id}")
    public ResponseResult delete(@PathVariable Long id) {
        userService.removeById(id);
        return ResponseResult.okResult();
    }

    @GetMapping("/{id}")
    public ResponseResult<GetUserDetailVo> getUserDetail(@PathVariable Long id) {
        // 所有角色的列表
        List<Role> roles = roleService.selectAllRole();
        // 用户关联的角色id
        List<Long> roleIds = roleService.selectRoleIdByUserId(id);
        // 用户信息
        User user = userService.getById(id);
        log.info("user:{}", user);
        UserDetailVo userDetailVo = BeanCopyUtils.copyBean(user, UserDetailVo.class);


        return ResponseResult.okResult(new GetUserDetailVo(roleIds, roles, userDetailVo));
    }

    @PutMapping
    public ResponseResult updateUser(@RequestBody UpdateUserDto updateUserDto) {
        userService.updateUser(updateUserDto);
        return ResponseResult.okResult();
    }
}
