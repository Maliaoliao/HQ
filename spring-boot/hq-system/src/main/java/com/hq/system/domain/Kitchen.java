package com.hq.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.hq.common.annotation.Excel;
import com.hq.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 kitchen
 *
 * @author wxy
 * @date 2021-11-25
 */
public class Kitchen extends BaseEntity
        {
private static final long serialVersionUID=1L;

        /** 厨房ID */
            private Long kitchenId;

            /** 厨房名称 */
                                                        @Excel(name = "厨房名称")
                        private String kitchenCode;

            /** 厨房地址 */
                                                        @Excel(name = "厨房地址")
                        private String kitchenPosition;

            /** 厨房经理 */
                                                        @Excel(name = "厨房经理")
                        private Long kitchenDirectorUserId;

            /** 厨房合同编码 */
                                                        @Excel(name = "厨房合同编码")
                        private String kitchenContractCode;

            /** 厨房开始租赁日期 */
                                                        @JsonFormat(pattern = "yyyy-MM-dd")
            @Excel(name = "厨房开始租赁日期", width = 30, dateFormat = "yyyy-MM-dd")
                        private Date kitchenBeginTime;

            /** 厨房结束租赁日期 */
                                                        @JsonFormat(pattern = "yyyy-MM-dd")
            @Excel(name = "厨房结束租赁日期", width = 30, dateFormat = "yyyy-MM-dd")
                        private Date kitchenEndTime;

            /** 厨房租金 */
                                                        @Excel(name = "厨房租金")
                        private Long kitchenRental;

            /** 厨房押金 */
                                                        @Excel(name = "厨房押金")
                        private Long kitchenForegift;

            /** 房租到期是否提醒 0不提醒 1提醒 */
                                                        @Excel(name = "房租到期是否提醒 0不提醒 1提醒")
                        private String kitchenAttermRemind;

            /** 厨房状态 0 停用 1启用 */
                                                        @Excel(name = "厨房状态 0 停用 1启用")
                        private String kitchenStatus;

                                                public void setKitchenId(Long kitchenId)
            {
            this.kitchenId = kitchenId;
            }

    public Long getKitchenId()
            {
            return kitchenId;
            }
                            public void setKitchenCode(String kitchenCode)
            {
            this.kitchenCode = kitchenCode;
            }

    public String getKitchenCode()
            {
            return kitchenCode;
            }
                            public void setKitchenPosition(String kitchenPosition)
            {
            this.kitchenPosition = kitchenPosition;
            }

    public String getKitchenPosition()
            {
            return kitchenPosition;
            }
                            public void setKitchenDirectorUserId(Long kitchenDirectorUserId)
            {
            this.kitchenDirectorUserId = kitchenDirectorUserId;
            }

    public Long getKitchenDirectorUserId()
            {
            return kitchenDirectorUserId;
            }
                            public void setKitchenContractCode(String kitchenContractCode)
            {
            this.kitchenContractCode = kitchenContractCode;
            }

    public String getKitchenContractCode()
            {
            return kitchenContractCode;
            }
                            public void setKitchenBeginTime(Date kitchenBeginTime)
            {
            this.kitchenBeginTime = kitchenBeginTime;
            }

    public Date getKitchenBeginTime()
            {
            return kitchenBeginTime;
            }
                            public void setKitchenEndTime(Date kitchenEndTime)
            {
            this.kitchenEndTime = kitchenEndTime;
            }

    public Date getKitchenEndTime()
            {
            return kitchenEndTime;
            }
                            public void setKitchenRental(Long kitchenRental)
            {
            this.kitchenRental = kitchenRental;
            }

    public Long getKitchenRental()
            {
            return kitchenRental;
            }
                            public void setKitchenForegift(Long kitchenForegift)
            {
            this.kitchenForegift = kitchenForegift;
            }

    public Long getKitchenForegift()
            {
            return kitchenForegift;
            }
                            public void setKitchenAttermRemind(String kitchenAttermRemind)
            {
            this.kitchenAttermRemind = kitchenAttermRemind;
            }

    public String getKitchenAttermRemind()
            {
            return kitchenAttermRemind;
            }
                            public void setKitchenStatus(String kitchenStatus)
            {
            this.kitchenStatus = kitchenStatus;
            }

    public String getKitchenStatus()
            {
            return kitchenStatus;
            }
                        
@Override
public String toString(){
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                                .append("kitchenId",getKitchenId())
                                .append("kitchenCode",getKitchenCode())
                                .append("kitchenPosition",getKitchenPosition())
                                .append("kitchenDirectorUserId",getKitchenDirectorUserId())
                                .append("kitchenContractCode",getKitchenContractCode())
                                .append("kitchenBeginTime",getKitchenBeginTime())
                                .append("kitchenEndTime",getKitchenEndTime())
                                .append("kitchenRental",getKitchenRental())
                                .append("kitchenForegift",getKitchenForegift())
                                .append("kitchenAttermRemind",getKitchenAttermRemind())
                                .append("kitchenStatus",getKitchenStatus())
                                .append("remark",getRemark())
                                .append("createBy",getCreateBy())
                                .append("createTime",getCreateTime())
                                .append("updateBy",getUpdateBy())
                                .append("updateTime",getUpdateTime())
                .toString();
        }
        }
