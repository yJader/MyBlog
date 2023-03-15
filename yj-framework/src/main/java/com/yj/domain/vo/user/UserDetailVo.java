package com.yj.domain.vo.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Package com.yj.domain.vo.user
 * @Author yJade
 * @Date 2023-03-15 22:58
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailVo {
    /**主键*/
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
}
