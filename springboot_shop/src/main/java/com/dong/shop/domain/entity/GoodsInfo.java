package com.dong.shop.domain.entity;

import java.math.BigDecimal;
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
 * 商品表
 * </p>
 *
 * @author caishaodong
 * @since 2020-11-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("c_goods_info")
public class GoodsInfo extends Model<GoodsInfo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品价格
     */
    private BigDecimal goodsPrice;

    /**
     * 商品数量
     */
    private Integer goodsNumber;

    /**
     * 商品单位
     */
    private String goodsUnit;

    /**
     * 商品重量
     */
    private BigDecimal goodsWeight;

    /**
     * 商品状态（0：未通过，1：审核中，2：上架，3：下架）
     */
    private Boolean goodsState;

    /**
     * 是否是热销品（0：否，1：是）
     */
    private Boolean isPromote;

    /**
     * 上传的图片临时路径
     */
    private String pics;

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
