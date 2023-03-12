package com.yj.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Package com.yj.domain.dto
 * @Author yJade
 * @Date 2023-03-12 23:50
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuListDto {
    /**
     * 菜单状态（0正常 1停用）
     */
    @ApiModelProperty("菜单状态（0正常 1停用）")
    private String status;
    /**
     * 菜单名称
     */
    @ApiModelProperty("菜单名称")
    private String menuName;
}
