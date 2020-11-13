package com.dong.shop.domain.entity.vo;

import com.dong.shop.domain.entity.SysRole;
import lombok.Data;

import java.util.List;

/**
 * @Author caishaodong
 * @Date 2020-11-13 16:16
 * @Description
 **/
@Data
public class SysRoleVo extends SysRole {
    /**
     * 角色拥有的菜单信息
     */
    private List<SysMenuVo> children;
}
