package com.yj.domain.dto.link;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Package com.yj.domain.dto.link
 * @Author yJade
 * @Date 2023-03-16 0:18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LinkListDto {
    private Integer pageNum;
    private Integer pageSize;
    /**
     * 友链名称
     */
    private String name;

    /**
     * 友链状态
     */
    private String status;
}
