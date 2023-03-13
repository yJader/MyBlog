package com.yj.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author yJade
 * @Date 2023-02-10 19:33
 * @Package com.yj.domain.vo
 * @Description: 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryVo {
    private Long id;
    private String name;
    /**
     * 描述
     */
    private String description;
}
