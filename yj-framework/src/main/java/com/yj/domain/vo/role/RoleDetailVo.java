package com.yj.domain.vo.role;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Package com.yj.domain.vo.role
 * @Author yJade
 * @Date 2023-03-14 13:11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDetailVo {
    /**
     * 角色ID
     */
    @ApiModelProperty("角色ID")
    private Long id;
    /**
     * 角色名称
     */
    @ApiModelProperty("角色名称")
    private String roleName;
    /**
     * 角色权限字符串
     */
    @ApiModelProperty("角色权限字符串")
    private String roleKey;
    /**
     * 显示顺序
     */
    @ApiModelProperty("显示顺序")
    private Integer roleSort;
    /**
     * 角色状态（0正常 1停用）
     */
    @ApiModelProperty("角色状态（0正常 1停用）")
    private String status;
    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remark;
}
