package com.dong.shop.controller;


import com.dong.shop.domain.entity.vo.SysMenuVo;
import com.dong.shop.global.ResponseResult;
import com.dong.shop.global.base.BaseController;
import com.dong.shop.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 系统菜单表 前端控制器
 * </p>
 *
 * @author caishaodong
 * @since 2020-11-12
 */
@RestController
@RequestMapping("/api/private/v1/menu")
public class SysMenuController extends BaseController {
    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 获取菜单列表
     *
     * @param type list:列表显示权限, tree:树状显示权限
     * @return
     */
    @GetMapping("/list/{type}")
    public ResponseResult<SysMenuVo> getList(@PathVariable("type") String type) {
        List<SysMenuVo> list = sysMenuService.getList(type);
        return success(list);
    }

}
