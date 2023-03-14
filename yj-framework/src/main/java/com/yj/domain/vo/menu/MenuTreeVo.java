package com.yj.domain.vo.menu;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Description:
 * @Package com.yj.domain.vo.menu
 * @Author yJade
 * @Date 2023-03-13 20:26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuTreeVo {
    /**
     * 菜单ID
     */
    @ApiModelProperty("菜单ID")
    private Long id;

    /**
     * 菜单名称(标签)
     */
    @ApiModelProperty("菜单名称(标签)")
    private String label;

    /**
     * 父菜单ID
     */
    @ApiModelProperty("父菜单ID")
    private Long parentId;

    @ApiModelProperty("子菜单")
    private List<MenuTreeVo> children;
}
