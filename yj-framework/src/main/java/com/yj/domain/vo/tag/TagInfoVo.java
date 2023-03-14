package com.yj.domain.vo.tag;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Package com.yj.domain.vo
 * @Author yJade
 * @Date 2023-03-01 21:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagInfoVo {
    private Long id;
    private String name;
    private String remark;
}
