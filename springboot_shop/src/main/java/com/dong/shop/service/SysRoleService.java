package com.dong.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dong.shop.domain.entity.SysRole;
import com.dong.shop.domain.entity.vo.SysRoleVo;

import java.util.List;

/**
 * <p>
 * 系统用户角色表 服务类
 * </p>
 *
 * @author caishaodong
 * @since 2020-11-12
 */
public interface SysRoleService extends IService<SysRole> {

    SysRole selectOKById(Long roleId);

    /**
     * 获取角色列表，以及对应的权限
     *
     * @return
     */
    List<SysRoleVo> getList();
}
