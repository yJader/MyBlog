package com.yj.domain.vo.user;

import com.yj.domain.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Description:
 * @Package com.yj.domain.vo.user
 * @Author yJade
 * @Date 2023-03-15 22:56
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetUserDetailVo {
    private List<Long> roleIds;
    private List<Role> roles;
    private UserDetailVo user;
}
