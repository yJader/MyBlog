package com.yj.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 标签(Tag)表实体类
 *
 * @author makejava
 * @since 2023-02-24 14:43:00
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("yj_tag")
@ApiModel("标签")
public class Tag  {
    
    @TableId
    @ApiModelProperty("")
    private Long id;
    /**
    * 标签名
    */    
    @ApiModelProperty("标签名")
    private String name;
    /**
     * 创建者
     */
    @ApiModelProperty("创建者")
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;
    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 更新者
     */
    @ApiModelProperty("更新者")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;
    /**
     * 更新时间
     */
    @ApiModelProperty("更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    /**
    * 删除标志（0代表未删除，1代表已删除）
    */    
    @ApiModelProperty("删除标志（0代表未删除，1代表已删除）")
    private Integer delFlag;
    /**
    * 备注
    */    
    @ApiModelProperty("备注")
    private String remark;

}

