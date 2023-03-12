package com.yj.runner;

import com.yj.constants.RedisKeyConstants;
import com.yj.domain.entity.Article;
import com.yj.mapper.ArticleMapper;
import com.yj.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Package com.yj.runner
 * @Author yJade
 * @Date 2023-02-16 22:44
 */
@Component
public class ViewCountRunner implements CommandLineRunner {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private RedisCache redisCache;

    @Override
    public void run(String... args) throws Exception {
        //查询博客信息 id viewCount
        List<Article> articles = articleMapper.selectList(null);
        Map<String, Integer> viewCountMap = new HashMap<>();
        for (Article article : articles) {
            viewCountMap.put(article.getId().toString(), article.getViewCount().intValue());
        }
        //存入redis
        redisCache.setCacheMap(RedisKeyConstants.ARTICLE_VIEW_COUNT, viewCountMap);
    }
}
