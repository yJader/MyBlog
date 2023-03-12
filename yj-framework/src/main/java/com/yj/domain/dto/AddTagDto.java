package com.yj.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Package com.yj.domain.dto
 * @Author yJade
 * @Date 2023-02-27 22:04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("查询标签")
public class AddTagDto {
    @ApiModelProperty(notes = "标签名")
    private String name;

    @ApiModelProperty(notes = "备注")
    private String remark;
}
