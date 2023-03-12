package com.yj.service.impl;

import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yj.constants.SystemConstants;
import com.yj.domain.ResponseResult;
import com.yj.domain.entity.Comment;
import com.yj.domain.vo.CommentVo;
import com.yj.domain.vo.PageVo;
import com.yj.enums.AppHttpCodeEnum;
import com.yj.exception.SystemException;
import com.yj.mapper.CommentMapper;
import com.yj.service.CommentService;
import com.yj.service.UserService;
import com.yj.utils.BeanCopyUtils;
import com.yj.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 评论表(Comment)表服务实现类
 *
 * @author makejava
 * @since 2023-02-15 00:30:17
 */
@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    private UserService userService;

    @Override
    public ResponseResult commentList(String commentType, Long articleId, Integer pageNum, Integer pageSize) {
        //查询文章对应的根评论
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        //对articleId进行判断 (只有是文章评论才进行判断)
        queryWrapper.eq(SystemConstants.ARTICLE_COMMENT.equals(commentType),Comment::getArticleId,articleId);
        //对根评论进行判断
        queryWrapper.eq(Comment::getRootId, -1L);
        //判断评论类型
        queryWrapper.eq(Comment::getType, commentType);
        //分页查询
        Page<Comment> commentPage = new Page<>(pageNum, pageSize);
        page(commentPage, queryWrapper);

        List<CommentVo> commentVos = toCommentVoList(commentPage.getRecords());

        //查询所有根评论对应的子评论集合, 并赋值给对应的属性
        for (CommentVo commentVo : commentVos) {
            //查询对应的子评论
            List<CommentVo> children= getChildren(commentVo.getId());
            //赋值
            commentVo.setChildren(children);
        }

        return ResponseResult.okResult(new PageVo(commentVos, commentPage.getTotal()));
    }

    @Override
    public ResponseResult addComment(Comment comment) {
        if (!StringUtils.hasText(comment.getContent())) {
            throw new SystemException(AppHttpCodeEnum.CONTENT_NOT_NULL);
        }
        //由mybatis plus自动填充完成
//        comment.setCreateBy(SecurityUtils.getUserId());

        save(comment);
        return null;
    }

    /**
     * @description: 根据根评论的id查询所对应的子评论的集合
     * @param: id 根评论的id
     * @return: java.util.List<com.yj.domain.vo.CommentVo>
     * @author: YJader
     * @date: 2023/2/15 18:07
     * TODO 为子评论添加分页功能
     */
    private List<CommentVo> getChildren(Long id) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getRootId, id);
        queryWrapper.orderByAsc(Comment::getCreateTime);
        List<Comment> list = list(queryWrapper);
        List<CommentVo> children = toCommentVoList(list);
        return children;
    }

    /**
     * @description: 将Comment集合转换为CommentVo集合
     * @param: list
     * @return: java.util.List<com.yj.domain.vo.CommentVo>
     * @author: YJader
     * @date: 2023/2/15 17:50
     */
    private List<CommentVo> toCommentVoList(List<Comment> list) {
        List<CommentVo> commentVos = BeanCopyUtils.copyBeanList(list, CommentVo.class);
        //遍历Vo集合
        for (CommentVo commentVo : commentVos) {
            //通过createBy属性查询用户的昵称并赋值
            String nickName = userService.getById(commentVo.getCreateBy()).getNickName();
            commentVo.setUsername(nickName);
            //通过toCommentUserId查询用户昵称并赋值

            //如果toCommentUserId不为-1(不是根评论)才进行查询
            if(commentVo.getToCommentUserId() != -1L) {
                commentVo.setToCommentUserName(userService.getById(commentVo.getToCommentUserId()).getNickName());
            }
        }
        return commentVos;
    }

}
