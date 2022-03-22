package com.xj.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 节假日
 *
 * @author sll
 * @since 2021/8/25
 */
@Data
public class Holiday {

    /**
     * 年份
     */
    @ApiModelProperty(value = "年份")
    private Integer year;

    /**
     * 日期
     */
    @ApiModelProperty(value = "日期")
    private Date date;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;

    /**
     * 补班日期列表
     */
    @ApiModelProperty(value = "补班日期列表")
    private String addWorkDateList;

    /**
     * 假期日期列表
     */
    @ApiModelProperty(value = "假期日期列表")
    private String holidayDateList;

}