package com.yj.domain.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 文章表(Article)实体类
 *
 * @author makejava
 * @since 2023-02-09 20:31:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("yj_article") //表名和实体类不一致 需要添加注解
@Accessors(chain = true)
public class Article  {

    @TableId
    private Long id;
    /**标题*/
    private String title;
    /**文章内容*/
    private String content;
    /**文章摘要*/
    private String summary;
    /**所属分类id*/
    private Long categoryId;
    /**所属分类名*/
    @TableField(exist = false)
    private String categoryName;
    /**缩略图*/
    private String thumbnail;
    /**是否置顶（0否，1是）*/
    private String isTop;
    /**状态（0已发布，1草稿）*/
    private String status;
    /**访问量*/
    private Long viewCount;
    /**是否允许评论 1是，0否*/
    private String isComment;
    /** mybatis plus 自动填充*/
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    /**删除标志（0代表未删除，1代表已删除）*/
    private Integer delFlag;

    public Article(Long id, Long viewCount) {
        this.id = id;
        this.viewCount = viewCount;
    }
}


