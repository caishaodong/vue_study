package com.dong.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dong.shop.domain.entity.SysUser;
import com.dong.shop.domain.entity.dto.SysUserSearchDTO;
import com.dong.shop.domain.entity.vo.SysUserVo;
import com.dong.shop.global.util.page.PageUtil;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 系统用户表 Mapper 接口
 * </p>
 *
 * @author caishaodong
 * @since 2020-11-12
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 获取用户列表（分页）
     *
     * @param dto
     * @return
     */
    PageUtil<SysUserVo> pageList(SysUserSearchDTO dto);

    /**
     * 根据用户id获取用户信息
     *
     * @param sysUserid
     * @return
     */
    SysUserVo selectSysUserById(@Param("sysUserid") Long sysUserid);
}

