package com.dong.shop.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.dong.shop.domain.entity.SysUser;
import com.dong.shop.domain.entity.SysUserRoleRel;
import com.dong.shop.domain.entity.vo.LoginSysUserVO;
import com.dong.shop.global.ResponseResult;
import com.dong.shop.global.base.BaseController;
import com.dong.shop.global.enums.BusinessEnum;
import com.dong.shop.global.enums.YesNoEnum;
import com.dong.shop.global.util.jwt.JwtUtil;
import com.dong.shop.global.util.string.StringUtil;
import com.dong.shop.service.SysUserRoleRelService;
import com.dong.shop.service.SysUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

/**
 * @Author caishaodong
 * @Date 2020-11-12 17:01
 * @Description
 **/
@RestController
@RequestMapping("/api/private/v1/login")
public class LoginController extends BaseController {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserRoleRelService sysUserRoleRelService;

    /**
     * 登录
     *
     * @param sysUser
     * @return
     */
    @PostMapping("")
    public ResponseResult login(@RequestBody SysUser sysUser) {
        String username = sysUser.getUsername();
        String password = sysUser.getPassword();
        // 参数校验
        if (StringUtil.isBlank(username) || StringUtil.isBlank(password)) {
            return error(BusinessEnum.PARAM_ERROR);
        }
        // 获取用户信息
        SysUser existSysUser = sysUserService.getOne(new LambdaQueryWrapper<SysUser>()
                .eq(SysUser::getUsername, username)
                .eq(SysUser::getIsDeleted, YesNoEnum.NO.getValue()));
        if (Objects.isNull(existSysUser)) {
            return error(BusinessEnum.LOGIN_NAME_OR_PASSWORD_ERROR);
        }
        // 密码校验
        if (!Objects.equals(password, existSysUser.getPassword())) {
            return error(BusinessEnum.LOGIN_NAME_OR_PASSWORD_ERROR);
        }

        // 封装响应数据
        LoginSysUserVO loginSysUserVO = new LoginSysUserVO();
        BeanUtils.copyProperties(existSysUser, loginSysUserVO);

        // 获取用户角色id
        List<SysUserRoleRel> list = sysUserRoleRelService.list(new LambdaQueryWrapper<SysUserRoleRel>()
                .eq(SysUserRoleRel::getSysUserId, existSysUser.getId())
                .eq(SysUserRoleRel::getIsDeleted, YesNoEnum.NO.getValue()));
        if (CollectionUtils.isNotEmpty(list)) {
            loginSysUserVO.setSysRoleId(list.get(0).getSysRoleId());
        }

        // 生成token
        String token = JwtUtil.createToken(existSysUser);
        loginSysUserVO.setToken(token);

        return success(loginSysUserVO);
    }
}
