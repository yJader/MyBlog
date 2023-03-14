package com.yj.domain.vo.article;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author yJade
 * @Date 2023-02-09 23:48
 * @Package com.yj.domain.vo
 * @Description: 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotArticleVo {
    private Long id;
    /**标题*/
    private String title;
    /**访问量*/
    private Long viewCount;
}
