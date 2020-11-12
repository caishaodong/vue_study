package com.dong.shop.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dong.shop.domain.entity.SysUser;
import com.dong.shop.domain.entity.dto.SysUserSearchDTO;
import com.dong.shop.global.ResponseResult;
import com.dong.shop.global.base.BaseController;
import com.dong.shop.global.util.page.PageUtil;
import com.dong.shop.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 * 系统用户表 前端控制器
 * </p>
 *
 * @author caishaodong
 * @since 2020-11-12
 */
@Controller
@RequestMapping("/api/private/v1/user")
public class SysUserController extends BaseController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 获取用户列表（分页）
     *
     * @param dto
     * @return
     */
//    @GetMapping("/pageList")
//    public ResponseResult<PageUtil<SysUser>> pageList(@RequestBody SysUserSearchDTO dto) {
//        IPage<SysUser> page = sysUserService.pageList(dto);
//        return success(page);
//    }

}
