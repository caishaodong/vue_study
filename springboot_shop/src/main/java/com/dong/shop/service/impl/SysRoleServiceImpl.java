package com.dong.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dong.shop.domain.entity.SysMenu;
import com.dong.shop.domain.entity.SysRole;
import com.dong.shop.domain.entity.SysRoleMenuRel;
import com.dong.shop.domain.entity.vo.SysMenuVo;
import com.dong.shop.domain.entity.vo.SysRoleVo;
import com.dong.shop.global.enums.YesNoEnum;
import com.dong.shop.global.util.menu.MenuUtil;
import com.dong.shop.mapper.SysRoleMapper;
import com.dong.shop.service.SysMenuService;
import com.dong.shop.service.SysRoleMenuRelService;
import com.dong.shop.service.SysRoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
    @Autowired
    private SysRoleMenuRelService sysRoleMenuRelService;
    @Autowired
    private SysMenuService sysMenuService;

    @Override
    public SysRole selectOKById(Long roleId) {
        return this.baseMapper.selectOne(new LambdaQueryWrapper<SysRole>()
                .eq(SysRole::getId, roleId)
                .eq(SysRole::getIsDeleted, YesNoEnum.NO.getValue()));
    }

    /**
     * 获取角色列表，以及对应的权限
     *
     * @return
     */
    @Override
    public List<SysRoleVo> getList() {
        // 获取所有的角色信息
        List<SysRole> sysRoleList = this.baseMapper.selectList(new LambdaQueryWrapper<SysRole>()
                .eq(SysRole::getIsDeleted, YesNoEnum.NO.getValue()));

        List<SysRoleVo> sysRoleVoList = new ArrayList<>();

        copyList(sysRoleList, sysRoleVoList);

        sysRoleVoList.stream().forEach(sysRoleVo -> {
            // 获取所有角色对应的菜单id
            List<SysRoleMenuRel> sysRoleMenuRelList = sysRoleMenuRelService.list(new LambdaQueryWrapper<SysRoleMenuRel>()
                    .eq(SysRoleMenuRel::getSysRoleId, sysRoleVo.getId())
                    .eq(SysRoleMenuRel::getIsDeleted, YesNoEnum.NO.getValue()));
            if (!CollectionUtils.isEmpty(sysRoleMenuRelList)) {
                List<Long> menuIdList = sysRoleMenuRelList.stream().map(SysRoleMenuRel::getSysMenuId).collect(Collectors.toList());
                // 根据菜单id获取多有菜单，并转化为tree
                List<SysMenu> menuList = sysMenuService.list(new LambdaQueryWrapper<SysMenu>()
                        .in(SysMenu::getId, menuIdList)
                        .eq(SysMenu::getIsDeleted, YesNoEnum.NO.getValue()));
                List<SysMenuVo> menuVoList = new ArrayList<>();

                MenuUtil.copyList(menuList, menuVoList);
                sysRoleVo.setChildren(MenuUtil.getMenuTree(menuVoList));
            }

        });
        return sysRoleVoList;
    }

    /**
     * 复制list
     *
     * @param sysRoleList
     * @param sysRoleVoList
     */
    public static void copyList(List<SysRole> sysRoleList, List<SysRoleVo> sysRoleVoList) {
        sysRoleVoList = Objects.isNull(sysRoleVoList) ? new ArrayList<>() : sysRoleVoList;
        if (CollectionUtils.isEmpty(sysRoleList)) {
            return;
        }
        for (SysRole sysRole : sysRoleList) {
            SysRoleVo sysRoleVo = new SysRoleVo();
            BeanUtils.copyProperties(sysRole, sysRoleVo);
            sysRoleVo.setChildren(new ArrayList<>());
            sysRoleVoList.add(sysRoleVo);
        }
        return;
    }
}
