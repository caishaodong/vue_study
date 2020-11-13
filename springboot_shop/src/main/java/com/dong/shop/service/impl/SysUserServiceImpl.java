package com.dong.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dong.shop.domain.entity.SysUser;
import com.dong.shop.domain.entity.dto.SysUserSearchDTO;
import com.dong.shop.domain.entity.vo.SysUserVo;
import com.dong.shop.global.enums.YesNoEnum;
import com.dong.shop.global.util.page.PageUtil;
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

    @Override
    public SysUser selectOKById(Long sysUserId) {
        return this.baseMapper.selectOne(new LambdaQueryWrapper<SysUser>()
                .eq(SysUser::getId, sysUserId)
                .eq(SysUser::getIsDeleted, YesNoEnum.NO.getValue()));
    }

    /**
     * 获取用户列表（分页）
     *
     * @param dto
     * @return
     */
    @Override
    public IPage<SysUserVo> pageList(SysUserSearchDTO dto) {
        PageUtil<SysUserVo> pageUtil = this.baseMapper.pageList(dto);
        return pageUtil;
    }

    /**
     * 根据用户id获取用户信息
     *
     * @param sysUserid
     * @return
     */
    @Override
    public SysUserVo selectSysUserById(Long sysUserid) {
        return this.baseMapper.selectSysUserById(sysUserid);
    }
}
