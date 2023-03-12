package com.yj.domain.entity;

import java.util.Date;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.*;

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
        
    @ApiModelProperty("")
    private Long createBy;
        
    @ApiModelProperty("")
    private Date createTime;
        
    @ApiModelProperty("")
    private Long updateBy;
        
    @ApiModelProperty("")
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

