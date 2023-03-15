package com.yj.domain.dto.user;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Package com.yj.domain.dto.user
 * @Author yJade
 * @Date 2023-03-15 16:51
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("用户列表Dto")
public class UserListDto {
    private Integer pageNum;
    private Integer pageSize;
    /**用户名*/
    private String userName;
    /**手机号*/
    private String phonenumber;
    /**账号状态（0正常 1停用）*/
    private String status;
}
