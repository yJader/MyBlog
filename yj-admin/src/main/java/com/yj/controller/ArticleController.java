package com.yj.controller;

import com.yj.domain.ResponseResult;
import com.yj.domain.dto.AddArticleDto;
import com.yj.domain.dto.EditArticleDto;
import com.yj.domain.entity.Article;
import com.yj.domain.vo.AdminArticleListVo;
import com.yj.domain.vo.ArticleVo;
import com.yj.domain.vo.PageVo;
import com.yj.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @Package com.yj.controller
 * @Author yJade
 * @Date 2023-03-06 22:32
 */
@RestController
@RequestMapping("/content/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @PostMapping
    public ResponseResult add(@RequestBody AddArticleDto addArticleDto) {
        return articleService.add(addArticleDto);
    }

    @GetMapping("/list")
    public ResponseResult<PageVo<AdminArticleListVo>> list(Article article, Integer pageNum, Integer pageSize) {
        PageVo<AdminArticleListVo> adminArticleListVoPageVo = articleService.selectArticlePage(article, pageNum, pageSize);
        return ResponseResult.okResult(adminArticleListVoPageVo);
    }

    @GetMapping("/{id}")
    public ResponseResult<ArticleVo> articleInfo(@PathVariable Long id) {
        ArticleVo articleVo = articleService.getInfo(id);
        return ResponseResult.okResult(articleVo);
    }

    @PutMapping
    public ResponseResult edit(@RequestBody EditArticleDto editArticleDto){
        articleService.edit(editArticleDto);
        return ResponseResult.okResult();
    }

    @DeleteMapping("/{id}")
    public ResponseResult delete(@PathVariable Long id) {
        articleService.removeById(id);
        return ResponseResult.okResult();
    }


}
