package com.hq.system.domain;

import com.hq.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 【请填写功能名称】对象 test
 *
 * @author wxy
 * @date 2021-10-28
 */
public class Test extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 商品ID
     */
    private Long test;

    public void setTest(Long test) {
        this.test = test;
    }

    public Long getTest() {
        return test;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("test", getTest())
                .toString();
    }
}
