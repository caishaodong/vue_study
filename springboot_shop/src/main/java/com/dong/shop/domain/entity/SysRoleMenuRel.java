package com.dong.shop.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统角色和菜单关联表
 * </p>
 *
 * @author caishaodong
 * @since 2020-11-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("c_sys_role_menu_rel")
public class SysRoleMenuRel extends Model<SysRoleMenuRel> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 系统角色id
     */
    private Long sysRoleId;

    /**
     * 系统菜单id
     */
    private Long sysMenuId;

    /**
     * 是否删除（0：未删除，1：删除）
     */
    private Boolean isDeleted;

    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 修改时间
     */
    private LocalDateTime gmtModified;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
