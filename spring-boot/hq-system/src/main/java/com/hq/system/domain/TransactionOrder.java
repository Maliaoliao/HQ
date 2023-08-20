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
    private static final long serialVersionUID = 1L;

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 用户标识
     */
    @Excel(name = "用户标识")
    private String openid;

    /**
     * 是否关注公众账号
     */
    @Excel(name = "是否关注公众账号")
    private String isSubscribe;

    /**
     * 交易类型
     */
    @Excel(name = "交易类型")
    private String tradeType;

    /**
     * 付款银行
     */
    @Excel(name = "付款银行")
    private String bankType;

    /**
     * 货币类型
     */
    @Excel(name = "货币类型")
    private String feeType;

    /**
     * 订单金额
     */
    @Excel(name = "订单金额")
    private Long totalFee;

    /**
     * 应结订单金额
     */
    @Excel(name = "应结订单金额")
    private Long settlementTotalFee;

    /**
     * 代金券金额
     */
    @Excel(name = "代金券金额")
    private Long couponFee;

    /**
     * 现金支付货币类型
     */
    @Excel(name = "现金支付货币类型")
    private String cashFeeType;

    /**
     * 现金支付金额
     */
    @Excel(name = "现金支付金额")
    private Long cashFee;

    /**
     * 微信支付订单号
     */
    @Excel(name = "微信支付订单号")
    private String transactionId;

    /**
     * 商户订单号
     */
    private String outTradeNo;

    /**
     * 商家数据包
     */
    @Excel(name = "商家数据包")
    private String attach;

    /**
     * 支付完成时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "支付完成时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date timeEnd;

    /**
     * 营销详情
     */
    @Excel(name = "营销详情")
    private String promotionDetail;

    /**
     * 商品ID
     */
    @Excel(name = "商品ID")
    private Long commodityId;

    /**
     * 终端ID
     */
    @Excel(name = "终端ID")
    private Long spbillCreateIp;

    /**
     * 订单是否开发票
     */
    @Excel(name = "订单是否开发票")
    private String isReceipt;

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getOpenid() {
        return openid;
    }

    public void setIsSubscribe(String isSubscribe) {
        this.isSubscribe = isSubscribe;
    }

    public String getIsSubscribe() {
        return isSubscribe;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    public String getBankType() {
        return bankType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setTotalFee(Long totalFee) {
        this.totalFee = totalFee;
    }

    public Long getTotalFee() {
        return totalFee;
    }

    public void setSettlementTotalFee(Long settlementTotalFee) {
        this.settlementTotalFee = settlementTotalFee;
    }

    public Long getSettlementTotalFee() {
        return settlementTotalFee;
    }

    public void setCouponFee(Long couponFee) {
        this.couponFee = couponFee;
    }

    public Long getCouponFee() {
        return couponFee;
    }

    public void setCashFeeType(String cashFeeType) {
        this.cashFeeType = cashFeeType;
    }

    public String getCashFeeType() {
        return cashFeeType;
    }

    public void setCashFee(Long cashFee) {
        this.cashFee = cashFee;
    }

    public Long getCashFee() {
        return cashFee;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getAttach() {
        return attach;
    }

    public void setTimeEnd(Date timeEnd) {
        this.timeEnd = timeEnd;
    }

    public Date getTimeEnd() {
        return timeEnd;
    }

    public void setPromotionDetail(String promotionDetail) {
        this.promotionDetail = promotionDetail;
    }

    public String getPromotionDetail() {
        return promotionDetail;
    }

    public void setCommodityId(Long commodityId) {
        this.commodityId = commodityId;
    }

    public Long getCommodityId() {
        return commodityId;
    }

    public void setSpbillCreateIp(Long spbillCreateIp) {
        this.spbillCreateIp = spbillCreateIp;
    }

    public Long getSpbillCreateIp() {
        return spbillCreateIp;
    }

    public void setIsReceipt(String isReceipt) {
        this.isReceipt = isReceipt;
    }

    public String getIsReceipt() {
        return isReceipt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("orderId", getOrderId())
                .append("openid", getOpenid())
                .append("isSubscribe", getIsSubscribe())
                .append("tradeType", getTradeType())
                .append("bankType", getBankType())
                .append("feeType", getFeeType())
                .append("totalFee", getTotalFee())
                .append("remark", getRemark())
                .append("settlementTotalFee", getSettlementTotalFee())
                .append("createBy", getCreateBy())
                .append("couponFee", getCouponFee())
                .append("createTime", getCreateTime())
                .append("cashFeeType", getCashFeeType())
                .append("updateBy", getUpdateBy())
                .append("cashFee", getCashFee())
                .append("updateTime", getUpdateTime())
                .append("transactionId", getTransactionId())
                .append("outTradeNo", getOutTradeNo())
                .append("attach", getAttach())
                .append("timeEnd", getTimeEnd())
                .append("promotionDetail", getPromotionDetail())
                .append("commodityId", getCommodityId())
                .append("spbillCreateIp", getSpbillCreateIp())
                .append("isReceipt", getIsReceipt())
                .toString();
    }
}
