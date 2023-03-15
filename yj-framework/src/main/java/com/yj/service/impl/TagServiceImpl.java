package com.yj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yj.domain.ResponseResult;
import com.yj.domain.dto.tag.TagListDto;
import com.yj.domain.entity.Tag;
import com.yj.domain.vo.PageVo;
import com.yj.domain.vo.tag.TagListVo;
import com.yj.mapper.TagMapper;
import com.yj.service.TagService;
import com.yj.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 标签(Tag)表服务实现类
 *
 * @author makejava
 * @since 2023-02-24 14:43:00
 */
@Service("tagService")
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Override
    public ResponseResult<PageVo<TagListVo>> pageTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto) {
        // 条件构造
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.eq(StringUtils.hasText(tagListDto.getName()), Tag::getName, tagListDto.getName());
        queryWrapper.eq(StringUtils.hasText(tagListDto.getRemark()), Tag::getRemark, tagListDto.getRemark());

        // 分页查询
        Page<Tag> tagPage = new Page<>(pageNum, pageSize);
        page(tagPage, queryWrapper);
        // 封装数据返回
        PageVo<TagListVo> pageVo = new PageVo(tagPage.getRecords(), tagPage.getTotal());

        return ResponseResult.okResult(pageVo);
    }

    @Override
    public List<TagListVo> listAllTag() {
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(Tag::getId, Tag::getName);
        List<Tag> list = list(queryWrapper);
        List<TagListVo> tagListVos = BeanCopyUtils.copyBeanList(list, TagListVo.class);

        return tagListVos;
    }


}
