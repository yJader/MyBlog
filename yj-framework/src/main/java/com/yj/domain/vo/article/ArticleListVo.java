package com.yj.domain.vo.article;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author yJade
 * @Date 2023-02-10 20:06
 * @Package com.yj.domain.vo
 * @Description: 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleListVo {

    private Long id;
    /** 标题 */
    private String title;
    /** 文章摘要 */
    private String summary;
    /**所属分类名 */
    private String categoryName;
    /**缩略图 */
    private String thumbnail;
    /** 访问量 */
    private Long viewCount;

    private Date createTime;
}