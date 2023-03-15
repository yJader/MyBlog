package com.yj.domain.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Description:
 * @Package com.yj.domain.dto.user
 * @Author yJade
 * @Date 2023-03-15 23:35
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserDto {
    /**主键*/
    // TODO 传参直接传id, 不安全吧
    private Long id;
    /**用户名*/
    private String userName;
    /**昵称*/
    private String nickName;
    /**账号状态（0正常 1停用）*/
    private String status;
    /**邮箱*/
    private String email;
    /**手机号*/
    private String phonenumber;
    /**用户性别（0男，1女，2未知）*/
    private String sex;

    private List<Long> roleIds;
}
