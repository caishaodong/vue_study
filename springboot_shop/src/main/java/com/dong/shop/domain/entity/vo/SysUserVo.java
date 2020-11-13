package com.dong.shop.domain.entity.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author caishaodong
 * @Date 2020-11-13 13:31
 * @Description
 **/
@Data
public class SysUserVo {
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
     * 状态（1：正常，2：冻结，3：注销）
     */
    private Integer status;
    /**
     * 是否删除（0：未删除，1：删除）
     */
    private Boolean isDeleted;
    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;
    /**
     * 角色名称
     */
    private String roleName;
}
