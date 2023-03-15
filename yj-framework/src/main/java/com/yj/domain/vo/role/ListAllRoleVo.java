package com.yj.domain.vo.role;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Description:
 * @Package com.yj.domain.vo.role
 * @Author yJade
 * @Date 2023-03-15 19:43
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListAllRoleVo {
    /**
     * 角色ID
     */
    @TableId
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
     * 删除标志（0代表存在 1代表删除）
     */
    @ApiModelProperty("删除标志（0代表存在 1代表删除）")
    private String delFlag;
    /**
     * 创建者
     */
    @ApiModelProperty("创建者")
    private Long createBy;
    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date createTime;
    /**
     * 更新者
     */
    @ApiModelProperty("更新者")
    private Long updateBy;
    /**
     * 更新时间
     */
    @ApiModelProperty("更新时间")
    private Date updateTime;
    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remark;

}
