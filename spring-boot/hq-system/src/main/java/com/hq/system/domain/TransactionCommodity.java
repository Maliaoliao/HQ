package com.hq.system.domain;

import com.hq.common.annotation.Excel;
import com.hq.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 【请填写功能名称】对象 transaction_commodity
 *
 * @author wxy
 * @date 2023-01-07
 */
public class TransactionCommodity extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 商品ID
     */
    private Long commodityId;

    /**
     * 名称
     */
    @Excel(name = "名称")
    private String name;

    /**
     * 价格(分)
     */
    @Excel(name = "价格(分)")
    private Long price;

    /**
     * 厨房ID
     */
    @Excel(name = "厨房ID")
    private Long kitchenId;

    /**
     * 餐箱编号
     */
    @Excel(name = "餐箱编号")
    private Long boxNum;

    /**
     * 是否失效
     */
    @Excel(name = "是否失效")
    private String commodityEnabled;

    /**
     * 主菜
     */
    @Excel(name = "主菜")
    private String mainCourse;

    /**
     * 次菜
     */
    @Excel(name = "次菜")
    private String minorDish;

    /**
     * 辅菜
     */
    @Excel(name = "辅菜")
    private String sideDish;

    /**
     * 主食
     */
    @Excel(name = "主食")
    private String stapleFood;

    /**
     * 描述
     */
    @Excel(name = "描述")
    private String description;

    /**
     * 商品海报
     */
    @Excel(name = "商品海报")
    private String picture;

    public void setCommodityId(Long commodityId) {
        this.commodityId = commodityId;
    }

    public Long getCommodityId() {
        return commodityId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getPrice() {
        return price;
    }

    public void setKitchenId(Long kitchenId) {
        this.kitchenId = kitchenId;
    }

    public Long getKitchenId() {
        return kitchenId;
    }

    public void setBoxNum(Long boxNum) {
        this.boxNum = boxNum;
    }

    public Long getBoxNum() {
        return boxNum;
    }

    public void setCommodityEnabled(String commodityEnabled) {
        this.commodityEnabled = commodityEnabled;
    }

    public String getCommodityEnabled() {
        return commodityEnabled;
    }

    public void setMainCourse(String mainCourse) {
        this.mainCourse = mainCourse;
    }

    public String getMainCourse() {
        return mainCourse;
    }

    public void setMinorDish(String minorDish) {
        this.minorDish = minorDish;
    }

    public String getMinorDish() {
        return minorDish;
    }

    public void setSideDish(String sideDish) {
        this.sideDish = sideDish;
    }

    public String getSideDish() {
        return sideDish;
    }

    public void setStapleFood(String stapleFood) {
        this.stapleFood = stapleFood;
    }

    public String getStapleFood() {
        return stapleFood;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPicture() {
        return picture;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("commodityId", getCommodityId())
                .append("name", getName())
                .append("price", getPrice())
                .append("kitchenId", getKitchenId())
                .append("boxNum", getBoxNum())
                .append("commodityEnabled", getCommodityEnabled())
                .append("remark", getRemark())
                .append("mainCourse", getMainCourse())
                .append("createBy", getCreateBy())
                .append("minorDish", getMinorDish())
                .append("createTime", getCreateTime())
                .append("sideDish", getSideDish())
                .append("updateBy", getUpdateBy())
                .append("stapleFood", getStapleFood())
                .append("updateTime", getUpdateTime())
                .append("description", getDescription())
                .append("picture", getPicture())
                .toString();
    }
}
