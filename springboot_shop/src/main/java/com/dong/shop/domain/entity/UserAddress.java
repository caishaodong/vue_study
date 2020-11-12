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
 * 用户收货地址表
 * </p>
 *
 * @author caishaodong
 * @since 2020-11-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("c_user_address")
public class UserAddress extends Model<UserAddress> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 收货人姓名
     */
    private String receiveName;

    /**
     * 收货人手机号
     */
    private String receiveMobile;

    /**
     * 收货地址
     */
    private String receiveAddress;

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
