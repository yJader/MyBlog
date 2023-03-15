package com.yj.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.yj.domain.ResponseResult;
import com.yj.domain.dto.category.AddCategoryDto;
import com.yj.domain.dto.category.UpdateCategoryDto;
import com.yj.domain.entity.Category;
import com.yj.domain.vo.PageVo;
import com.yj.domain.vo.category.CategoryAllListVo;
import com.yj.domain.vo.category.CategoryDetailVo;
import com.yj.domain.vo.category.CategoryListVo;
import com.yj.domain.vo.category.ExcelCategoryVo;
import com.yj.enums.AppHttpCodeEnum;
import com.yj.service.CategoryService;
import com.yj.utils.BeanCopyUtils;
import com.yj.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Description:
 * @Package com.yj.controller
 * @Author yJade
 * @Date 2023-03-01 23:24
 */
@RestController
@RequestMapping("/content/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/{id}")
    public ResponseResult detail(@PathVariable String id) {
        Category category = categoryService.getById(id);
        CategoryDetailVo categoryDetailVo = BeanCopyUtils.copyBean(category, CategoryDetailVo.class);
        return ResponseResult.okResult(categoryDetailVo);
    }

    @GetMapping("/listAllCategory")
    public ResponseResult<List<CategoryAllListVo>> listAllCategory() {
        List<CategoryAllListVo> list = categoryService.listAllCategory();
        return ResponseResult.okResult(list);
    }

    @GetMapping("/list")
    public ResponseResult<PageVo<CategoryListVo>> categoryList(Integer pageNum, Integer pageSize) {
        PageVo<CategoryListVo> categoryListVoPageVo = categoryService.categoryList(pageNum, pageSize);
        return ResponseResult.okResult(categoryListVoPageVo);
    }

    @PreAuthorize("@ps.hasPermission('content:category:export')")
    @GetMapping("/export")
    public void export(HttpServletResponse response) {
        try {
            // 设置下载文件的请求头
            WebUtils.setDownLoadHeader("分类.xlsx", response);
            // 获取需要导出的数据
            List<Category> categoryList = categoryService.list();
            List<ExcelCategoryVo> excelCategoryVos = BeanCopyUtils.copyBeanList(categoryList, ExcelCategoryVo.class);

            // 将数据写入excel
            EasyExcel.write(response.getOutputStream(), ExcelCategoryVo.class)
                    .autoCloseStream(Boolean.FALSE)
                    .sheet("分类导出")
                    .doWrite(excelCategoryVos);
        } catch (Exception e) {
            // 出现异常, 响应json
            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
            WebUtils.renderString(response, JSON.toJSONString(result));
        }
    }

    @PostMapping
    public ResponseResult add(@RequestBody AddCategoryDto addCategoryDto) {
        Category category = BeanCopyUtils.copyBean(addCategoryDto, Category.class);
        categoryService.save(category);
        return ResponseResult.okResult();
    }

    @PutMapping
    public ResponseResult update(@RequestBody UpdateCategoryDto updateCategoryDto) {
        Category category = BeanCopyUtils.copyBean(updateCategoryDto, Category.class);
        categoryService.updateById(category);
        return ResponseResult.okResult();
    }
}
