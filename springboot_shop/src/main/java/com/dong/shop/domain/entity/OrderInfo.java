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
 * 订单表
 * </p>
 *
 * @author caishaodong
 * @since 2020-11-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("c_order_info")
public class OrderInfo extends Model<OrderInfo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 收货人姓名
     */
    private String receiveName;

    /**
     * 用户收货手机号
     */
    private String receiveMobile;

    /**
     * 收货地址
     */
    private String receiveAddress;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 订单价格
     */
    private BigDecimal orderPrice;

    /**
     * 支付方式( 0：未支付，1：支付宝，2：微信，3：银行卡）
     */
    private Boolean orderPay;

    /**
     * 支付状态（0：未支付，1：已支付）
     */
    private Boolean payStatus;

    /**
     * 是否发货（0：未发货，1：已发货）
     */
    private Boolean isSend;

    /**
     * 交易编号
     */
    private String tradeNo;

    /**
     * 发票抬头（1：个人，2：公司）
     */
    private String fapiaoTitle;

    /**
     * 发票公司名称
     */
    private String fapiaoCompany;

    /**
     * 发票内容
     */
    private String fapiaoContent;

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
