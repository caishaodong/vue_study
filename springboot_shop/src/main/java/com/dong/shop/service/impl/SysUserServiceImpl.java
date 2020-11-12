package com.dong.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dong.shop.domain.entity.SysUser;
import com.dong.shop.domain.entity.dto.SysUserSearchDTO;
import com.dong.shop.global.enums.YesNoEnum;
import com.dong.shop.mapper.SysUserMapper;
import com.dong.shop.service.SysUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统用户表 服务实现类
 * </p>
 *
 * @author caishaodong
 * @since 2020-11-12
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    /**
     * 获取用户列表（分页）
     *
     * @param dto
     * @return
     */
    @Override
    public IPage<SysUser> pageList(SysUserSearchDTO dto) {
        IPage<SysUser> page = this.page(dto, new LambdaQueryWrapper<SysUser>()
                .eq(SysUser::getIsDeleted, YesNoEnum.NO.getValue())
                .orderByDesc(SysUser::getGmtCreate));
        return page;
    }
}
