package com.dong.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dong.shop.domain.entity.SysRole;
import com.dong.shop.global.enums.YesNoEnum;
import com.dong.shop.mapper.SysRoleMapper;
import com.dong.shop.service.SysRoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统用户角色表 服务实现类
 * </p>
 *
 * @author caishaodong
 * @since 2020-11-12
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Override
    public SysRole selectOKById(Long roleId) {
        return this.baseMapper.selectOne(new LambdaQueryWrapper<SysRole>()
                .eq(SysRole::getId, roleId)
                .eq(SysRole::getIsDeleted, YesNoEnum.NO.getValue()));
    }
}
