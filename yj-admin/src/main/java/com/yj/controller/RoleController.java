package com.yj.controller;

import com.yj.domain.ResponseResult;
import com.yj.domain.dto.role.AddRoleDto;
import com.yj.domain.dto.role.ChangeRoleStatusDto;
import com.yj.domain.dto.role.UpdateRoleDto;
import com.yj.domain.entity.Role;
import com.yj.domain.vo.PageVo;
import com.yj.domain.vo.role.RoleDetailVo;
import com.yj.domain.vo.role.RoleListVo;
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
    public ResponseResult<PageVo<RoleListVo>> roleList(Integer pageNum, Integer pageSize, String roleName, String status) {
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
        roleService.insertRole(addRoleDto);
        return ResponseResult.okResult();
    }

    @GetMapping("/{id}")
    public ResponseResult<RoleDetailVo> detail(@PathVariable Long id){
        Role role = roleService.getById(id);
        RoleDetailVo roleDetailVo = BeanCopyUtils.copyBean(role, RoleDetailVo.class);
        return ResponseResult.okResult(roleDetailVo);
    }

    @PutMapping
    public ResponseResult update(@RequestBody UpdateRoleDto updateRoleDto) {
        roleService.updateRole(updateRoleDto);
        return ResponseResult.okResult();
    }

    /**
     * @description: 逻辑删除角色,不删除角色对应权限
     * @param: id
     * @return: com.yj.domain.ResponseResult
     * @author: YJader
     * @date: 2023/3/15 16:32
     */
    @DeleteMapping("/{id}")
    public ResponseResult delete(@PathVariable Long id) {
        roleService.removeById(id);
        return ResponseResult.okResult();
    }
}
