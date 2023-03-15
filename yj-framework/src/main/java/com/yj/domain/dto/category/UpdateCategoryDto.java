package com.yj.domain.dto.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Package com.yj.domain.dto.category
 * @Author yJade
 * @Date 2023-03-16 0:09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCategoryDto {
    private Long id;
    /**分类名*/
    private String name;
    /**描述*/
    private String description;
    /**状态0:正常,1禁用*/
    private String status;
}
