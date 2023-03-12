package com.yj.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yj.domain.entity.ArticleTag;
import com.yj.mapper.ArticleTagMapper;
import com.yj.service.ArticleTagService;
import org.springframework.stereotype.Service;

/**
 * 文章标签关联表(ArticleTag)表服务实现类
 *
 * @author makejava
 * @since 2023-03-06 23:18:30
 */
@Service("articleTagService")
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag> implements ArticleTagService {

}

