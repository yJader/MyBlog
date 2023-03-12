package com.yj.service;

import com.yj.domain.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Description:
 * @Package com.yj.service
 * @Author yJade
 * @Date 2023-02-16 14:33
 */
public interface UploadService {
    ResponseResult uploadImg(MultipartFile img);
}
