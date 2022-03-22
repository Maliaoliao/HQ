package com.hq.system.domain;

import com.hq.common.annotation.Excel;
import com.hq.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 【请填写功能名称】对象 transaction_commodities
 *
 * @author wxy
 * @date 2021-10-28
 */
public class TransactionCommodities extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 商品ID
     */
    private Long commodityId;

    /**
     * 商品编码
     */
    @Excel(name = "商品编码")
    private String commodityCode;

    /**
     * 商品名称
     */
    @Excel(name = "商品名称")
    private String commodityName;

    /**
     * 商品价格
     */
    @Excel(name = "商品价格")
    private Long commodityPrice;

    public void setCommodityId(Long commodityId) {
        this.commodityId = commodityId;
    }

    public Long getCommodityId() {
        return commodityId;
    }

    public void setCommodityCode(String commodityCode) {
        this.commodityCode = commodityCode;
    }

    public String getCommodityCode() {
        return commodityCode;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityPrice(Long commodityPrice) {
        this.commodityPrice = commodityPrice;
    }

    public Long getCommodityPrice() {
        return commodityPrice;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("commodityId", getCommodityId())
                .append("commodityCode", getCommodityCode())
                .append("commodityName", getCommodityName())
                .append("commodityPrice", getCommodityPrice())
                .append("remark", getRemark())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
