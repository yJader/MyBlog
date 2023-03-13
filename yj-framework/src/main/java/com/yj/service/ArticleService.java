package com.yj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yj.domain.ResponseResult;
import com.yj.domain.dto.AddArticleDto;
import com.yj.domain.dto.EditArticleDto;
import com.yj.domain.entity.Article;
import com.yj.domain.vo.AdminArticleListVo;
import com.yj.domain.vo.ArticleListVo;
import com.yj.domain.vo.ArticleVo;
import com.yj.domain.vo.PageVo;

/**
 * @Author yJade
 * @Date 2023-02-09 20:44
 * @Package com.yj.service
 * @Description: 
 */
public interface ArticleService extends IService<Article> {

    ResponseResult hotArticleList();

    ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId);

    ResponseResult getArticleDetail(Long id);

    ResponseResult updateViewCount(Long id);

    ResponseResult add(AddArticleDto articleDto);

    PageVo<AdminArticleListVo> selectArticlePage(Article article, Integer pageNum, Integer pageSize);

    ArticleVo getInfo(Long id);

    void edit(EditArticleDto editArticleDto);
}
