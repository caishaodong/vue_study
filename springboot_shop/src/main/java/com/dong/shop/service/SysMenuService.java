package com.dong.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dong.shop.domain.entity.SysMenu;
import com.dong.shop.domain.entity.vo.SysMenuVo;

import java.util.List;

/**
 * <p>
 * 系统菜单表 服务类
 * </p>
 *
 * @author caishaodong
 * @since 2020-11-12
 */
public interface SysMenuService extends IService<SysMenu> {

    /**
     * 获取菜单列表
     *
     * @param type list:列表显示权限, tree:树状显示权限
     * @return
     */
    List<SysMenuVo> getList(String type);
}
