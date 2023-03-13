package com.yj.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author yJade
 * @Date 2023-02-11 14:53
 * @Package com.yj.domain.vo
 * @Description: 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LinkVo {
    private Long id;

    private String name;

    private String logo;

    private String description;
    /**网站地址*/
    private String address;
}
