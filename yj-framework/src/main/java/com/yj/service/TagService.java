package com.yj.service;

import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yj.domain.ResponseResult;
import com.yj.domain.dto.TagListDto;
import com.yj.domain.entity.Tag;
import com.yj.domain.vo.PageVo;
import com.yj.domain.vo.TagVo;

/**
 * 标签(Tag)表服务接口
 *
 * @author makejava
 * @since 2023-02-24 14:43:00
 */
public interface TagService extends IService<Tag> {

    /**
     * @description: 按照给定条件(tagListDto)查询tag
     * @param: pageNum
     * @param: pageSize
     * @param: tagListDto
     * @return: com.yj.domain.ResponseResult<com.yj.domain.vo.PageVo>
     * @author: YJader
     * @date: 2023/2/27 22:11
     */
    ResponseResult<PageVo<TagVo>> pageTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto);

    List<TagVo> listAllTag();
}
