package com.liuzemin.server.framework.model.model;


import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 租赁谁被其他费用对象 crec_settle_other
 *
 * @author Scott_xin
 * @date 2023-10-25
 */

@Data
public class SettleOther {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private Long id;

    /**
     * 租赁设备id
     */
    private Long settleDeviceId;

    /**
     * 其它费用不含税金额
     */
    private BigDecimal otherAmountExcludingTax;

    /**
     * 其它费用不含税单价
     */
    private BigDecimal otherPriceExcludingTax;

    /**
     * 其它费用内容
     */
    private String otherSettlementContent;

    /**
     * 其它费用ID
     */
    private Long otherSettlementId;

    /**
     * 其它费用数量
     */
    private Long otherSettlementQuantity;

    /**
     * 其它费用备注
     */
    private String otherSettlementRemarks;

    /**
     * 其它费用数量单位
     */
    private String otherSettlementUnit;


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("settleDeviceId", getSettleDeviceId())
                .append("otherAmountExcludingTax", getOtherAmountExcludingTax())
                .append("otherPriceExcludingTax", getOtherPriceExcludingTax())
                .append("otherSettlementContent", getOtherSettlementContent())
                .append("otherSettlementId", getOtherSettlementId())
                .append("otherSettlementQuantity", getOtherSettlementQuantity())
                .append("otherSettlementRemarks", getOtherSettlementRemarks())
                .append("otherSettlementUnit", getOtherSettlementUnit())
                .toString();
    }
}
