package com.yj.service.impl;

import com.yj.constants.RedisKeyConstants;
import com.yj.domain.ResponseResult;
import com.yj.domain.entity.LoginUser;
import com.yj.domain.entity.User;
import com.yj.domain.vo.BlogUserLoginVo;
import com.yj.domain.vo.UserInfoVo;
import com.yj.service.BlogLoginService;
import com.yj.utils.BeanCopyUtils;
import com.yj.utils.JwtUtil;
import com.yj.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @Author yJade
 * @Date 2023-02-13 23:17
 * @Package com.yj.service.impl
 * @Description:
 */
@Service
public class BlogLoginServiceImpl implements BlogLoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        //判断是否认证通过
        if(Objects.isNull(authenticate)) {
            throw new RuntimeException("用户名或密码错误");
        }
        //获取userId 生成token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String id = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(id);
        //将用户信息存入Redis
        redisCache.setCacheObject(RedisKeyConstants.ARTICLE_VIEW_COUNT +id, loginUser);
        //把token和userInfo封装 返回 (接口要求)

        BlogUserLoginVo blogUserLoginVo =
                new BlogUserLoginVo(jwt, BeanCopyUtils.copyBean(loginUser, UserInfoVo.class));
        return ResponseResult.okResult(blogUserLoginVo);
    }

    @Override
    public ResponseResult logout() {
        //获取token 解析获取userid
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        //获取userid
        Long id = loginUser.getUser().getId();
        //删除redis中的用户信息
        redisCache.deleteObject(RedisKeyConstants.ARTICLE_VIEW_COUNT+id);
        return ResponseResult.okResult();
    }
}
