package com.dong.shop.domain.entity.vo;

import lombok.Data;

/**
 * @Author caishaodong
 * @Date 2020-11-12 17:22
 * @Description
 **/
@Data
public class LoginSysUserVO {
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 手机号
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;
    /**
     * 角色id
     */
    private Long sysRoleId;
    /**
     * 用户登录token
     */
    private String token;
}
