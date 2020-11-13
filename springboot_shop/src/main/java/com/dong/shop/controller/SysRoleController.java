package com.dong.shop.controller;


import com.dong.shop.domain.entity.vo.SysRoleVo;
import com.dong.shop.global.ResponseResult;
import com.dong.shop.global.base.BaseController;
import com.dong.shop.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 系统用户角色表 前端控制器
 * </p>
 *
 * @author caishaodong
 * @since 2020-11-12
 */
@RestController
@RequestMapping("/api/private/v1/role")
public class SysRoleController extends BaseController {

    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 获取角色列表，以及对应的权限
     *
     * @return
     */
    @GetMapping("/list")
    public ResponseResult<SysRoleVo> getList() {
        List<SysRoleVo> list = sysRoleService.getList();
        return success(list);
    }

}
