package com.hq.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.hq.common.annotation.Excel;
import com.hq.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 transaction_order
 *
 * @author mall
 * @date 2021-10-26
 */
public class TransactionOrder extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 订单关联设备编号
     */
    @Excel(name = "订单关联设备编号")
    private String orderNum;

    /**
     * 订单金额
     */
    @Excel(name = "订单金额")
    private Long orderAmount;

    /**
     * 订单优惠金额
     */
    @Excel(name = "订单优惠金额")
    private Long orderPreferentialAmount;

    /**
     * 支付渠道
     */
    @Excel(name = "支付渠道")
    private String paymenChannel;

    /**
     * 支付渠道唯一编码
     */
    @Excel(name = "支付渠道唯一编码")
    private String paymenChannelId;

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderAmount(Long orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Long getOrderAmount() {
        return orderAmount;
    }

    public void setOrderPreferentialAmount(Long orderPreferentialAmount) {
        this.orderPreferentialAmount = orderPreferentialAmount;
    }

    public Long getOrderPreferentialAmount() {
        return orderPreferentialAmount;
    }

    public void setPaymenChannel(String paymenChannel) {
        this.paymenChannel = paymenChannel;
    }

    public String getPaymenChannel() {
        return paymenChannel;
    }

    public void setPaymenChannelId(String paymenChannelId) {
        this.paymenChannelId = paymenChannelId;
    }

    public String getPaymenChannelId() {
        return paymenChannelId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("orderId", getOrderId())
                .append("orderNum", getOrderNum())
                .append("orderAmount", getOrderAmount())
                .append("orderPreferentialAmount", getOrderPreferentialAmount())
                .append("paymenChannel", getPaymenChannel())
                .append("paymenChannelId", getPaymenChannelId())
                .append("remark", getRemark())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
