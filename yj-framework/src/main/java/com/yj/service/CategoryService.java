package com.yj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yj.domain.ResponseResult;
import com.yj.domain.entity.Category;
import com.yj.domain.vo.category.CategoryListVo;
import com.yj.domain.vo.category.CategoryAllListVo;
import com.yj.domain.vo.PageVo;

import java.util.List;

/**
 * 分类表(Category)表服务接口
 *
 * @author makejava
 * @since 2023-02-10 16:22:48
 */
public interface CategoryService extends IService<Category> {
    ResponseResult getCategoryList();

    List<CategoryAllListVo> listAllCategory();

    PageVo<CategoryListVo> categoryList(Integer pageNum, Integer pageSize);
}
