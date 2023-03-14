package com.yj.domain.dto.article;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Package com.yj.domain.dto
 * @Author yJade
 * @Date 2023-03-06 22:34
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddArticleDto {

    private Long id;
    /**标题*/
    private String title;
    /**文章内容*/
    private String content;
    /**文章摘要*/
    private String summary;
    /**所属分类id*/
    private Long categoryId;

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
    /** 文章标签*/
    private List<Long> tags;
}
