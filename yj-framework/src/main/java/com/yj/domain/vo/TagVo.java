package com.yj.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Package com.yj.domain.vo
 * @Author yJade
 * @Date 2023-03-01 23:39
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagVo {
    private Long id;

    /**
     * 标签名
     */
    private String name;
}
