package com.yj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yj.constants.SystemConstants;
import com.yj.domain.ResponseResult;
import com.yj.domain.entity.Link;
import com.yj.domain.vo.PageVo;
import com.yj.domain.vo.link.LinkListVo;
import com.yj.domain.vo.link.LinkVo;
import com.yj.mapper.LinkMapper;
import com.yj.service.LinkService;
import com.yj.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 友链(Link)表服务实现类
 *
 * @author makejava
 * @since 2023-02-11 14:44:02
 */
@Service("linkService")
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {

    @Override
    public ResponseResult getAllLink() {
        //查询所有审核通过的link
        LambdaQueryWrapper<Link> linkLambdaQueryWrapper = new LambdaQueryWrapper<>();
        linkLambdaQueryWrapper.eq(Link::getStatus, SystemConstants.LINK_STATUS_NORMAL);
        List<Link> links = list(linkLambdaQueryWrapper);
        //转换Vo
        List<LinkVo> linkVos = BeanCopyUtils.copyBeanList(links, LinkVo.class);

        return ResponseResult.okResult(linkVos);
    }

    @Override
    public PageVo<LinkListVo> selectLinkPage(Link link, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<Link> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.hasText(link.getName()), Link::getName, link.getName());
        queryWrapper.eq(StringUtils.hasText(link.getStatus()), Link::getStatus, link.getStatus());

        Page<Link> linkPage = new Page<>();
        page(linkPage, queryWrapper);
        List<Link> links = linkPage.getRecords();
        List<LinkListVo> linkListVos = BeanCopyUtils.copyBeanList(links, LinkListVo.class);

        return new PageVo<>(linkListVos, linkPage.getTotal());
    }
}

