package com.yj.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 分类表(Category)表实体类
 *
 * @author makejava
 * @since 2023-02-10 16:22:48
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("yj_category")
public class Category  {
    @TableId
    private Long id;
    /**分类名*/
    private String name;
    /**父分类id，如果没有父分类为-1*/
    private Long pid;
    /**描述*/
    private String description;
    /**状态0:正常,1禁用*/
    private String status;
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
    /**删除标志（0代表未删除，1代表已删除）*/
    private Integer delFlag;

}

