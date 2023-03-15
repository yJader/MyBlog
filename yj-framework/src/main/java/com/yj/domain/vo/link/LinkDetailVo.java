package com.yj.domain.vo.link;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Package com.yj.domain.vo.link
 * @Author yJade
 * @Date 2023-03-16 0:33
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LinkDetailVo {
    private Long id;

    private String name;

    private String logo;

    private String description;
    /**网站地址*/
    private String address;
    /**审核状态 (0代表审核通过，1代表审核未通过，2代表未审核)*/
    private String status;
}
