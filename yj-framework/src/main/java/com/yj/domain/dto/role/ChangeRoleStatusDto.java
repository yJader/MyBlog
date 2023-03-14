package com.yj.domain.dto.role;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Package com.yj.domain.dto
 * @Author yJade
 * @Date 2023-03-13 20:02
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeRoleStatusDto {
    /**
     * 角色ID
     */
    @TableId
    @ApiModelProperty("角色ID")
    private Long id;
    /**
     * 角色状态（0正常 1停用）
     */
    @ApiModelProperty("角色状态（0正常 1停用）")
    private String status;
}
