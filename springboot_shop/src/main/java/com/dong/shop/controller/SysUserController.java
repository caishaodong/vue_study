package com.dong.shop.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dong.shop.domain.entity.SysRole;
import com.dong.shop.domain.entity.SysUser;
import com.dong.shop.domain.entity.SysUserRoleRel;
import com.dong.shop.domain.entity.dto.SysUserSearchDTO;
import com.dong.shop.domain.entity.vo.SysUserVo;
import com.dong.shop.global.ResponseResult;
import com.dong.shop.global.base.BaseController;
import com.dong.shop.global.enums.BusinessEnum;
import com.dong.shop.global.enums.SysUserStatusEnum;
import com.dong.shop.global.enums.YesNoEnum;
import com.dong.shop.global.util.page.PageUtil;
import com.dong.shop.global.util.reflect.ReflectUtil;
import com.dong.shop.global.util.string.StringUtil;
import com.dong.shop.service.SysRoleService;
import com.dong.shop.service.SysUserRoleRelService;
import com.dong.shop.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 系统用户表 前端控制器
 * </p>
 *
 * @author caishaodong
 * @since 2020-11-12
 */
@RestController
@RequestMapping("/api/private/v1/user")
public class SysUserController extends BaseController {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysUserRoleRelService sysUserRoleRelService;

    /**
     * 获取用户列表（分页）
     *
     * @param dto
     * @return
     */
    @GetMapping("/pageList")
    public ResponseResult<PageUtil<SysUserVo>> pageList(@RequestBody SysUserSearchDTO dto) {
        IPage<SysUserVo> page = sysUserService.pageList(dto);
        return success(page);
    }

    /**
     * 保存/修改用户信息
     *
     * @param sysUser
     * @return
     */
    @PostMapping("/save")
    public ResponseResult save(@RequestBody SysUser sysUser) {
        if (StringUtil.isBlank(sysUser.getUsername()) || StringUtil.isBlank(sysUser.getPassword())) {
            return error(BusinessEnum.PARAM_ERROR);
        }
        // 校验用户名是否重复
        List<SysUser> list = this.sysUserService.list(new LambdaQueryWrapper<SysUser>()
                .and(wrapper -> wrapper.eq(SysUser::getUsername, sysUser.getUsername())
                        .or().eq(StringUtil.isNotBlank(sysUser.getMobile()), SysUser::getMobile, sysUser.getMobile())
                        .or().eq(StringUtil.isNotBlank(sysUser.getEmail()), SysUser::getEmail, sysUser.getEmail()))
                .ne(Objects.nonNull(sysUser.getId()), SysUser::getId, sysUser.getId()));
        if (!CollectionUtils.isEmpty(list)) {
            return error(BusinessEnum.USER_EXIST);
        }

        if (Objects.isNull(sysUser.getId())) {
            // 保存用户
            SysUser newSysUser = new SysUser();
            newSysUser.setUsername(sysUser.getUsername());
            newSysUser.setPassword(sysUser.getPassword());
            newSysUser.setMobile(sysUser.getMobile());
            newSysUser.setEmail(sysUser.getEmail());
            newSysUser.setStatus(SysUserStatusEnum.NORMAL.getStatus());
            ReflectUtil.setCreateInfo(newSysUser, SysUser.class);
            this.sysUserService.save(newSysUser);
        } else {
            this.sysUserService.updateById(sysUser);
        }

        return success();
    }

    /**
     * 修改用户状态
     *
     * @param sysUserid 用户id
     * @param status    状态（1：正常，2：冻结，3：注销）
     * @return
     */
    @PutMapping("/status/{sysUserId}/{status}")
    public ResponseResult pageList(@PathVariable("sysUserId") Long sysUserid, @PathVariable("status") Integer status) {
        // 校验用户是否存在
        SysUser sysUser = this.sysUserService.selectOKById(sysUserid);
        if (Objects.isNull(sysUser)) {
            return error(BusinessEnum.USER_NOT_EXIST);
        }
        sysUser.setStatus(status);
        this.sysUserService.updateById(sysUser);
        return success();
    }

    /**
     * 根据用户id获取用户信息
     *
     * @param sysUserid 用户id
     * @return
     */
    @GetMapping("/getById/{sysUserId}")
    public ResponseResult<SysUserVo> getSysUserById(@PathVariable("sysUserId") Long sysUserid) {
        SysUserVo sysUserVo = this.sysUserService.selectSysUserById(sysUserid);
        if (Objects.isNull(sysUserVo)) {
            return error(BusinessEnum.USER_NOT_EXIST);
        }
        return success(sysUserVo);
    }

    /**
     * 根据id删除用户
     *
     * @param sysUserid 用户id
     * @return
     */
    @DeleteMapping("/delete/{sysUserId}")
    public ResponseResult delete(@PathVariable("sysUserId") Long sysUserid) {
        // 校验用户是否存在
        SysUser sysUser = this.sysUserService.selectOKById(sysUserid);
        if (Objects.isNull(sysUser)) {
            return error(BusinessEnum.USER_NOT_EXIST);
        }
        sysUser.setIsDeleted(YesNoEnum.YES.getValue());
        this.sysUserService.updateById(sysUser);
        return success();
    }

    /**
     * 给用户设置角色
     *
     * @param sysUserId 用户id
     * @param roleId    角色id
     * @return
     */
        @PutMapping("/setRole/{sysUserId}/{roleId}")
    public ResponseResult setRole(@PathVariable("sysUserId") Long sysUserId, @PathVariable("roleId") Long roleId) {
        // 校验用户是否存在
        SysUser sysUser = this.sysUserService.selectOKById(sysUserId);
        if (Objects.isNull(sysUser)) {
            return error(BusinessEnum.USER_NOT_EXIST);
        }
        // 校验角色是否存在
        SysRole sysRole = this.sysRoleService.selectOKById(roleId);
        if (Objects.isNull(sysRole)) {
            return error(BusinessEnum.ROLE_NOT_EXIST);
        }

        SysUserRoleRel sysUserRoleRel = sysUserRoleRelService.getOne(new LambdaQueryWrapper<SysUserRoleRel>()
                .eq(SysUserRoleRel::getSysUserId, sysUserId));
        if (Objects.isNull(sysUserRoleRel)) {
            sysUserRoleRel = new SysUserRoleRel();
            sysUserRoleRel.setSysUserId(sysUserId);
            sysUserRoleRel.setSysRoleId(roleId);
            ReflectUtil.setCreateInfo(sysUserRoleRel, SysUserRoleRel.class);
            sysUserRoleRelService.save(sysUserRoleRel);
        } else {
            sysUserRoleRel.setSysRoleId(roleId);
            sysUserRoleRelService.updateById(sysUserRoleRel);
        }
        return success();
    }


}
