package com.yj.controller;

import com.yj.domain.ResponseResult;
import com.yj.domain.dto.tag.AddTagDto;
import com.yj.domain.dto.tag.TagInfoDto;
import com.yj.domain.dto.tag.TagListDto;
import com.yj.domain.entity.Tag;
import com.yj.domain.vo.PageVo;
import com.yj.domain.vo.tag.TagInfoVo;
import com.yj.domain.vo.tag.TagListVo;
import com.yj.service.TagService;
import com.yj.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description:
 * @Package com.yj.controller
 * @Author yJade
 * @Date 2023-02-24 14:46
 */
@RestController
@RequestMapping("/content/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/list")
    public ResponseResult<PageVo<TagListVo>> list(Integer pageNum, Integer pageSize, TagListDto tagListDto){
        return tagService.pageTagList(pageNum,pageSize,tagListDto);
    }


    @GetMapping("/listAllTag")
    public ResponseResult listAllTag(){
        List<TagListVo> list = tagService.listAllTag();
        return ResponseResult.okResult(list);
    }

    @PostMapping("/")
    public ResponseResult addTag(@RequestBody AddTagDto addTagDto) {
        tagService.save(BeanCopyUtils.copyBean(addTagDto, Tag.class));
        return ResponseResult.okResult();
    }

    @DeleteMapping("/")
    public ResponseResult deleteTag(@PathVariable Long id) {
        tagService.removeById(id);
        return ResponseResult.okResult();
    }

    @GetMapping("/{id}")
    public ResponseResult tagInfo(@PathVariable Long id) {
        Tag tag = tagService.getById(id);
        TagInfoVo tagInfoVo = BeanCopyUtils.copyBean(tag, TagInfoVo.class);
        return ResponseResult.okResult(tagInfoVo);
    }

    @PutMapping("")
    public ResponseResult updateTag(TagInfoDto tagInfoDto) {
        Tag tag = BeanCopyUtils.copyBean(tagInfoDto, Tag.class);
        tagService.updateById(tag);
        return ResponseResult.okResult();
    }


}
