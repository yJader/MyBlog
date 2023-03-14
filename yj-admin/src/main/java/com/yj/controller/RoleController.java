package com.yj.controller;

import com.yj.domain.ResponseResult;
import com.yj.domain.dto.role.AddRoleDto;
import com.yj.domain.dto.role.ChangeRoleStatusDto;
import com.yj.domain.entity.Role;
import com.yj.domain.vo.PageVo;
import com.yj.domain.vo.RoleVo;
import com.yj.service.RoleService;
import com.yj.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @Package com.yj.controller
 * @Author yJade
 * @Date 2023-03-13 19:26
 */
@RestController
@RequestMapping("/system/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/list")
    public ResponseResult<PageVo<RoleVo>> roleList(Integer pageNum,Integer pageSize, String roleName, String status) {
        return ResponseResult.okResult(roleService.roleList(pageNum, pageSize, roleName, status));
    }

    @PutMapping("/changeStatus")
    public ResponseResult changeStatus(@RequestBody ChangeRoleStatusDto changeRoleStatusDto) {
        // 有一说一 复制的话会慢一些些, 不如直接新建一个
        Role role = BeanCopyUtils.copyBean(changeRoleStatusDto, Role.class);
        roleService.updateById(role);
        return ResponseResult.okResult();
    }

    @PostMapping
    public ResponseResult add(@RequestBody AddRoleDto addRoleDto) {
        // TODO 新增角色
        roleService.add(addRoleDto);
        return ResponseResult.okResult();
    }
}
