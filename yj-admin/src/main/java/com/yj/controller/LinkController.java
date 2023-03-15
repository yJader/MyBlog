package com.yj.controller;

import com.yj.domain.ResponseResult;
import com.yj.domain.dto.link.AddLinkDto;
import com.yj.domain.dto.link.LinkListDto;
import com.yj.domain.dto.link.UpdateLinkDto;
import com.yj.domain.entity.Link;
import com.yj.domain.vo.PageVo;
import com.yj.domain.vo.link.LinkDetailVo;
import com.yj.domain.vo.link.LinkListVo;
import com.yj.service.LinkService;
import com.yj.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: TODO 前端咋不加上传文件, 直接传地址啊 离谱
 * @Package com.yj.controller
 * @Author yJade
 * @Date 2023-03-16 0:16
 */
@RestController
@RequestMapping("/content/link")
public class LinkController {
    @Autowired
    private LinkService linkService;

    @GetMapping("/{id}")
    public ResponseResult<LinkDetailVo> detail(@PathVariable Long id) {
        Link link = linkService.getById(id);
        LinkDetailVo linkDetailVo = BeanCopyUtils.copyBean(link, LinkDetailVo.class);
        return ResponseResult.okResult(linkDetailVo);
    }

    @GetMapping("/list")
    public ResponseResult<PageVo<LinkListVo>> list(LinkListDto linkListDto) {
        Link link = BeanCopyUtils.copyBean(linkListDto, Link.class);
        return ResponseResult.okResult(linkService.selectLinkPage(link, linkListDto.getPageNum(), linkListDto.getPageSize()));
    }

    @PutMapping
    public ResponseResult update(@RequestBody UpdateLinkDto updateLinkDto) {
        Link link = BeanCopyUtils.copyBean(updateLinkDto, Link.class);
        linkService.updateById(link);
        return ResponseResult.okResult();
    }

    @PutMapping("/changeLinkStatus")
    public ResponseResult changeLinkStatus(@RequestBody UpdateLinkDto updateLinkDto) {
        Link link = BeanCopyUtils.copyBean(updateLinkDto, Link.class);
        linkService.updateById(link);
        return ResponseResult.okResult();
    }

    @PostMapping
    public ResponseResult add(@RequestBody AddLinkDto addLinkDto) {
        Link link = BeanCopyUtils.copyBean(addLinkDto, Link.class);
        linkService.save(link);
        return ResponseResult.okResult();
    }

    @DeleteMapping("/{id}")
    public ResponseResult delete(@PathVariable Long id) {
        linkService.removeById(id);
        return ResponseResult.okResult();
    }
}
