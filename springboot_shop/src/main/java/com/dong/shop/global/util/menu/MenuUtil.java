package com.dong.shop.global.util.menu;

import com.dong.shop.domain.entity.SysMenu;
import com.dong.shop.domain.entity.vo.SysMenuVo;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Author caishaodong
 * @Date 2020-09-10 15:09
 * @Description
 **/
public class MenuUtil {

    public static Integer FIRST_LEVEL = 1;

    /**
     * 复制list
     *
     * @param sysMenuList
     * @param sysMenuVoList
     */
    public static void copyList(List<SysMenu> sysMenuList, List<SysMenuVo> sysMenuVoList) {
        sysMenuVoList = Objects.isNull(sysMenuVoList) ? new ArrayList<>() : sysMenuVoList;
        if (CollectionUtils.isEmpty(sysMenuList)) {
            return;
        }
        for (SysMenu sysMenu : sysMenuList) {
            SysMenuVo SysMenuVo = new SysMenuVo();
            BeanUtils.copyProperties(sysMenu, SysMenuVo);
            SysMenuVo.setChildren(new ArrayList<SysMenuVo>());
            sysMenuVoList.add(SysMenuVo);
        }
        return;
    }

    /**
     * 获取菜单树
     *
     * @param SysMenuVoList
     */
    public static List<SysMenuVo> getMenuTree(List<SysMenuVo> SysMenuVoList) {
        // 获取所有的一级菜单
        List<SysMenuVo> firstLevelMenuList = SysMenuVoList.stream().filter(SysMenuVo -> SysMenuVo.getLevel().intValue() == FIRST_LEVEL.intValue())
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(firstLevelMenuList)) {
            return null;
        }
        firstLevelMenuList.stream().forEach(firstLevelMenu -> {
            setChildren(SysMenuVoList, firstLevelMenu);
        });
        return firstLevelMenuList;
    }

    /**
     * 获取子菜单
     *
     * @param SysMenuVoList
     * @param parent
     */
    public static void setChildren(List<SysMenuVo> SysMenuVoList, SysMenuVo parent) {
        List<SysMenuVo> children = SysMenuVoList.stream().filter(SysMenuVo -> Objects.equals(SysMenuVo.getPid(), (parent.getId()))).collect(Collectors.toList());
        parent.setChildren(children);
        if (!CollectionUtils.isEmpty(children)) {
            for (SysMenuVo child : children) {
                setChildren(SysMenuVoList, child);
            }
        }

    }
}
