package com.hq.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.hq.common.annotation.Excel;
import com.hq.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 kitchen_material
 *
 * @author wxy
 * @date 2021-11-26
 */
public class KitchenMaterial extends BaseEntity
        {
private static final long serialVersionUID=1L;

        /** 物料ID */
            private Long materialId;

            /** 物料名称 */
                                                        @Excel(name = "物料名称")
                        private String materialName;

            /** 厨房ID */
                                                        @Excel(name = "厨房ID")
                        private Long kitchenId;

            /** 物料复核状态0未复核 1已复核 */
                                                        @Excel(name = "物料复核状态0未复核 1已复核")
                        private String materialCheckStatus;

            /** 物料复核人员ID */
                                                        @Excel(name = "物料复核人员ID")
                        private Long materialCheckUserId;

            /** 复核日期 */
                                                        @JsonFormat(pattern = "yyyy-MM-dd")
            @Excel(name = "复核日期", width = 30, dateFormat = "yyyy-MM-dd")
                        private Date materialCheckDate;

            /** 采购数量 */
                                                        @Excel(name = "采购数量")
                        private Long materialQuantity;

            /** 单位 */
                                                        @Excel(name = "单位")
                        private String materialUnit;

            /** 采购金额 */
                                                        @Excel(name = "采购金额")
                        private Long paymenAmount;

            /** 采购渠道 */
                                                        @Excel(name = "采购渠道")
                        private String paymenCanal;

            /** 支付方式 */
                                                        @Excel(name = "支付方式")
                        private String paymenWay;

            /** 支付ID */
                                                        @Excel(name = "支付ID")
                        private String paymenOrderId;

                                                public void setMaterialId(Long materialId)
            {
            this.materialId = materialId;
            }

    public Long getMaterialId()
            {
            return materialId;
            }
                            public void setMaterialName(String materialName)
            {
            this.materialName = materialName;
            }

    public String getMaterialName()
            {
            return materialName;
            }
                            public void setKitchenId(Long kitchenId)
            {
            this.kitchenId = kitchenId;
            }

    public Long getKitchenId()
            {
            return kitchenId;
            }
                            public void setMaterialCheckStatus(String materialCheckStatus)
            {
            this.materialCheckStatus = materialCheckStatus;
            }

    public String getMaterialCheckStatus()
            {
            return materialCheckStatus;
            }
                            public void setMaterialCheckUserId(Long materialCheckUserId)
            {
            this.materialCheckUserId = materialCheckUserId;
            }

    public Long getMaterialCheckUserId()
            {
            return materialCheckUserId;
            }
                            public void setMaterialCheckDate(Date materialCheckDate)
            {
            this.materialCheckDate = materialCheckDate;
            }

    public Date getMaterialCheckDate()
            {
            return materialCheckDate;
            }
                            public void setMaterialQuantity(Long materialQuantity)
            {
            this.materialQuantity = materialQuantity;
            }

    public Long getMaterialQuantity()
            {
            return materialQuantity;
            }
                            public void setMaterialUnit(String materialUnit)
            {
            this.materialUnit = materialUnit;
            }

    public String getMaterialUnit()
            {
            return materialUnit;
            }
                            public void setPaymenAmount(Long paymenAmount)
            {
            this.paymenAmount = paymenAmount;
            }

    public Long getPaymenAmount()
            {
            return paymenAmount;
            }
                            public void setPaymenCanal(String paymenCanal)
            {
            this.paymenCanal = paymenCanal;
            }

    public String getPaymenCanal()
            {
            return paymenCanal;
            }
                            public void setPaymenWay(String paymenWay)
            {
            this.paymenWay = paymenWay;
            }

    public String getPaymenWay()
            {
            return paymenWay;
            }
                            public void setPaymenOrderId(String paymenOrderId)
            {
            this.paymenOrderId = paymenOrderId;
            }

    public String getPaymenOrderId()
            {
            return paymenOrderId;
            }
                        
@Override
public String toString(){
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                                .append("materialId",getMaterialId())
                                .append("materialName",getMaterialName())
                                .append("kitchenId",getKitchenId())
                                .append("materialCheckStatus",getMaterialCheckStatus())
                                .append("materialCheckUserId",getMaterialCheckUserId())
                                .append("materialCheckDate",getMaterialCheckDate())
                                .append("materialQuantity",getMaterialQuantity())
                                .append("materialUnit",getMaterialUnit())
                                .append("paymenAmount",getPaymenAmount())
                                .append("paymenCanal",getPaymenCanal())
                                .append("paymenWay",getPaymenWay())
                                .append("paymenOrderId",getPaymenOrderId())
                                .append("remark",getRemark())
                                .append("createBy",getCreateBy())
                                .append("createTime",getCreateTime())
                                .append("updateBy",getUpdateBy())
                                .append("updateTime",getUpdateTime())
                .toString();
        }
        }
