package com.dong.shop.domain.entity.vo;

import com.dong.shop.domain.entity.SysMenu;
import lombok.Data;

import java.util.List;

/**
 * @Author caishaodong
 * @Date 2020-11-13 15:38
 * @Description
 **/
@Data
public class SysMenuVo extends SysMenu {
    /**
     * 子菜单
     */
    private List<SysMenuVo> children;
}
