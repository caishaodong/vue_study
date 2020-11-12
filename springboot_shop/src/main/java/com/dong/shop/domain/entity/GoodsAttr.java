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
 * 商品类目参数表
 * </p>
 *
 * @author caishaodong
 * @since 2020-11-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("c_goods_attr")
public class GoodsAttr extends Model<GoodsAttr> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 类目参数所属类目id
     */
    private Long cateId;

    /**
     * 类目参数名称
     */
    private String attrName;

    /**
     * only：输入框(唯一)，many：后台下拉列表/前台单选框
     */
    private String attrSel;

    /**
     * manual:手工录入，list:从列表选择
     */
    private String attrWrite;

    /**
     * 如果 attr_write为list,那么有值，该值以逗号分隔
     */
    private String attrVals;

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
