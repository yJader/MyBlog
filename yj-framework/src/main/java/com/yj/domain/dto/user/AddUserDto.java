package com.yj.domain.dto.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @Description:
 * @Package com.yj.domain.dto.user
 * @Author yJade
 * @Date 2023-03-15 17:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddUserDto {
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名, 长度为3~20个字符")
    @NotBlank(message = "用户名不能为空")
    @Length(min = 3, max = 20, message = "用户名长度错误, 应在3~20个字符之间")
    private String userName;
    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称, 长度为1~10个字符")
    @NotBlank(message = "昵称不能为空")
    @Length(min = 1, max = 10, message = "昵称长度错误, 应在1~10个字符之间")
    private String nickName;
    /**
     * 密码
     */
    @ApiModelProperty(value = "密码, 长度为3~20个字符")
    @NotBlank(message = "密码不能为空")
    @Length(min = 3, max = 20, message = "密码长度错误, 应在3~20个字符之间")
    private String password;
    /**
     * 邮箱
     */
    @ApiModelProperty("邮箱")
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式错误")
    private String email;
    /**
     * 手机号
     */
    @ApiModelProperty("手机号")
    @NotBlank(message = "手机号不能为空")
    @Length(min = 11, max = 11, message = "手机号格式错误")
    private String phonenumber;
    /**账号状态（0正常 1停用）*/
    private String status = "0";
    /**用户性别（0男，1女，2未知）*/
    private String sex = "2";
    /**
     * 用户对应角色id
     */
    private List<Long> roleIds;

}
