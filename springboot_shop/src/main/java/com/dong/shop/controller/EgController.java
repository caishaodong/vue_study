package com.dong.shop.controller;

import com.dong.shop.global.ResponseResult;
import com.dong.shop.global.base.BaseController;
import com.dong.shop.global.config.aop.permission.Permission;
import com.dong.shop.global.enums.RoleEnum;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author caishaodong
 * @Date 2020-09-07 13:09
 * @Description
 **/
@RestController
@RequestMapping("/index")
public class EgController extends BaseController {

    @Permission(role = {RoleEnum.SUPER_ADMIN, RoleEnum.ADMIN})
    @GetMapping("/permission")
    public ResponseResult permission() {
        return success();
    }

    @GetMapping("/returnValue")
    public Long returnValue() {
        return 3L;
    }
}
