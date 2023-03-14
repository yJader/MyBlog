package com.yj.domain.vo.menu;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Package com.yj.domain.vo
 * @Author yJade
 * @Date 2023-03-13 11:49
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuDetailVo {
    /**
     * 菜单ID
     */
    @ApiModelProperty("菜单ID")
    private Long id;
    /**
     * 菜单名称
     */
    @ApiModelProperty("菜单名称")
    private String menuName;
    /**
     * 父菜单ID
     */
    @ApiModelProperty("父菜单ID")
    private Long parentId;
    /**
     * 显示顺序
     */
    @ApiModelProperty("显示顺序")
    private Integer orderNum;
    /**
     * 路由地址
     */
    @ApiModelProperty("路由地址")
    private String path;
    /**
     * 菜单类型（M目录 C菜单 F按钮）
     */
    @ApiModelProperty("菜单类型（M目录 C菜单 F按钮）")
    private String menuType;
    /**
     * 菜单状态（0显示 1隐藏）
     */
    @ApiModelProperty("菜单状态（0显示 1隐藏）")
    private String visible;
    /**
     * 菜单状态（0正常 1停用）
     */
    @ApiModelProperty("菜单状态（0正常 1停用）")
    private String status;
    /**
     * 菜单图标
     */
    @ApiModelProperty("菜单图标")
    private String icon;
    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remark;

}
