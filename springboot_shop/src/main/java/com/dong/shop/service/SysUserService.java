package com.dong.shop.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dong.shop.domain.entity.SysUser;
import com.dong.shop.domain.entity.dto.SysUserSearchDTO;

/**
 * <p>
 * 系统用户表 服务类
 * </p>
 *
 * @author caishaodong
 * @since 2020-11-12
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 获取用户列表（分页）
     *
     * @param dto
     * @return
     */
    IPage<SysUser> pageList(SysUserSearchDTO dto);
}
