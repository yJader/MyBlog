package com.yj.domain.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 角色和菜单关联表(RoleMenu)表实体类
 *
 * @author makejava
 * @since 2023-03-14 11:47:19
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_role_menu")
@ApiModel("角色和菜单关联表")
public class RoleMenu  {
    /**
    * 角色ID
    */
    @TableId
    @ApiModelProperty("角色ID")
    private Long roleId;
    /**
    * 菜单ID
    */
    @ApiModelProperty("菜单ID")
    private Long menuId;

}

