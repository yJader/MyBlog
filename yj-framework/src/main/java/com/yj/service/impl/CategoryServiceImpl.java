package com.yj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yj.constants.SystemConstants;
import com.yj.domain.ResponseResult;
import com.yj.domain.entity.Article;
import com.yj.domain.entity.Category;
import com.yj.domain.vo.CategoryListVo;
import com.yj.domain.vo.CategoryVo;
import com.yj.domain.vo.PageVo;
import com.yj.mapper.CategoryMapper;
import com.yj.service.ArticleService;
import com.yj.service.CategoryService;
import com.yj.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 分类表(Category)表服务实现类
 *
 * @author makejava
 * @since 2023-02-10 16:22:48
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    ArticleService articleService;

    /**
     * @description: 返回分类列表
     * @param:
     * @return: com.yj.domain.ResponseResult
     * @author: YJader
     * @date: 2023/2/10 16:55
     */
    @Override
    public ResponseResult getCategoryList() {
        LambdaQueryWrapper<Article> articleQueryWrapper = new LambdaQueryWrapper<>();
        articleQueryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        List<Article> list = articleService.list(articleQueryWrapper);
        //获取文章的分类id, 并进行去重
        Set<Long> categories = list.stream().map(Article::getCategoryId)
                .collect(Collectors.toSet());

        //查询分类表, 过滤掉不正常的分类
        List<Category> categoryList = listByIds(categories).stream().
                filter(category -> SystemConstants.STATUS_NORMAL.equals(category.getStatus()))
                .collect(Collectors.toList());
        List<CategoryVo> categoryVos = BeanCopyUtils.copyBeanList(categoryList, CategoryVo.class);
        return ResponseResult.okResult(categoryVos);
    }

    @Override
    public List<CategoryVo> listAllCategory() {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Category::getStatus, SystemConstants.CATEGORY_STATUS_NORMAL);
        List<Category> list = list(queryWrapper);
        List<CategoryVo> categoryVos = BeanCopyUtils.copyBeanList(list, CategoryVo.class);

        return categoryVos;
    }

    @Override
    public PageVo<CategoryListVo> categoryList(Integer pageNum, Integer pageSize) {
        Page<Category> categoryPage = new Page<>();
        page(categoryPage);
        List<Category> categories = categoryPage.getRecords();
        List<CategoryListVo> categoryListVos = BeanCopyUtils.copyBeanList(categories, CategoryListVo.class);

        return new PageVo<>(categoryListVos, categoryPage.getTotal());
    }
}

