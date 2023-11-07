package com.hq.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hq.common.annotation.Excel;
import com.hq.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 交易对象 transaction_order
 *
 * @author mall
 * @date 2022-12-27
 */
public class TransactionOrder extends BaseEntity {
    private static final long serialVersionUID=1L;

    /** 订单ID */
    /** 订单ID */
    private Long orderId;

    /** 订单关联设备编号 */
    @Excel(name = "订单关联设备编号")
    private String orderNum;

    /** 订单金额 */
    @Excel(name = "订单金额")
    private Integer orderAmount;

    /** 订单优惠金额 */
    @Excel(name = "订单优惠金额")
    private Long orderPreferentialAmount;

    /** 支付渠道 */
    @Excel(name = "支付渠道")
    private String paymenChannel;

    /** 支付渠道唯一编码 */
    @Excel(name = "支付渠道唯一编码")
    private String paymenChannelId;

    /** $column.columnComment */
    @Excel(name = "支付渠道唯一编码")
    private String payStatus;

    /** $column.columnComment */
    @Excel(name = "支付渠道唯一编码")
    private String payCode;

    /** $column.columnComment */
    @Excel(name = "支付渠道唯一编码")
    private Long productId;

    /** $column.columnComment */
    @Excel(name = "支付渠道唯一编码")
    private String outTradeNo;

    public void setOrderId(Long orderId)
    {
        this.orderId = orderId;
    }

    public Long getOrderId()
    {
        return orderId;
    }
    public void setOrderNum(String orderNum)
    {
        this.orderNum = orderNum;
    }

    public String getOrderNum()
    {
        return orderNum;
    }
    public void setOrderAmount(Integer orderAmount)
    {
        this.orderAmount = orderAmount;
    }

    public Integer getOrderAmount()
    {
        return orderAmount;
    }
    public void setOrderPreferentialAmount(Long orderPreferentialAmount)
    {
        this.orderPreferentialAmount = orderPreferentialAmount;
    }

    public Long getOrderPreferentialAmount()
    {
        return orderPreferentialAmount;
    }
    public void setPaymenChannel(String paymenChannel)
    {
        this.paymenChannel = paymenChannel;
    }

    public String getPaymenChannel()
    {
        return paymenChannel;
    }
    public void setPaymenChannelId(String paymenChannelId)
    {
        this.paymenChannelId = paymenChannelId;
    }

    public String getPaymenChannelId()
    {
        return paymenChannelId;
    }
    public void setPayStatus(String payStatus)
    {
        this.payStatus = payStatus;
    }

    public String getPayStatus()
    {
        return payStatus;
    }
    public void setPayCode(String payCode)
    {
        this.payCode = payCode;
    }

    public String getPayCode()
    {
        return payCode;
    }
    public void setProductId(Long productId)
    {
        this.productId = productId;
    }

    public Long getProductId()
    {
        return productId;
    }
    public void setOutTradeNo(String outTradeNo)
    {
        this.outTradeNo = outTradeNo;
    }

    public String getOutTradeNo()
    {
        return outTradeNo;
    }

    @Override
    public String toString(){
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("orderId",getOrderId())
                .append("orderNum",getOrderNum())
                .append("orderAmount",getOrderAmount())
                .append("orderPreferentialAmount",getOrderPreferentialAmount())
                .append("paymenChannel",getPaymenChannel())
                .append("paymenChannelId",getPaymenChannelId())
                .append("remark",getRemark())
                .append("createBy",getCreateBy())
                .append("createTime",getCreateTime())
                .append("updateBy",getUpdateBy())
                .append("updateTime",getUpdateTime())
                .append("payStatus",getPayStatus())
                .append("payCode",getPayCode())
                .append("productId",getProductId())
                .append("outTradeNo",getOutTradeNo())
                .toString();
    }
}
