package com.yj.domain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author yJade
 * @Date 2023-02-11 14:00
 * @Package com.yj.domain.vo
 * @Description: TODO
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDetailVo {

    private Long id;
    /**标题*/
    private String title;
    /**文章内容*/
    private String content;
    /**所属分类id*/
    private Long categoryId;
    /**所属分类名*/
    private String categoryName;
    /**是否允许评论 1是，0否*/
    private String isComment;
    /**访问量*/
    private Long viewCount;
    private Date createTime;
}
