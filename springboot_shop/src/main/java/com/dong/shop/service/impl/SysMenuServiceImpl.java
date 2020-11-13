package com.dong.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dong.shop.domain.entity.SysMenu;
import com.dong.shop.domain.entity.vo.SysMenuVo;
import com.dong.shop.global.enums.YesNoEnum;
import com.dong.shop.global.util.menu.MenuUtil;
import com.dong.shop.mapper.SysMenuMapper;
import com.dong.shop.service.SysMenuService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 系统菜单表 服务实现类
 * </p>
 *
 * @author caishaodong
 * @since 2020-11-12
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    /**
     * 获取菜单列表
     *
     * @param type list:列表显示权限, tree:树状显示权限
     * @return
     */
    @Override
    public List<SysMenuVo> getList(String type) {
        String menuType = "tree";
        List<SysMenuVo> list = new ArrayList<>();

        List<SysMenu> sysMenus = this.baseMapper.selectList(new LambdaQueryWrapper<SysMenu>()
                .eq(SysMenu::getIsDeleted, YesNoEnum.NO.getValue()).orderByAsc(SysMenu::getSort));

        // 复制
        MenuUtil.copyList(sysMenus, list);
        if (Objects.equals(type, menuType)) {
            // 展示树状权限
            list = MenuUtil.getMenuTree(list);
        }
        return list;
    }
}
