package com.yj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yj.domain.ResponseResult;
import com.yj.domain.entity.Link;
import com.yj.domain.vo.PageVo;
import com.yj.domain.vo.link.LinkListVo;

/**
 * 友链(Link)表服务接口
 *
 * @author makejava
 * @since 2023-02-11 14:44:01
 */
public interface LinkService extends IService<Link> {

    ResponseResult getAllLink();

    PageVo<LinkListVo> selectLinkPage(Link link, Integer pageNum, Integer pageSize);
}
