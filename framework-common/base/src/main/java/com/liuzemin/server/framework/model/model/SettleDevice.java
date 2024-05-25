package com.liuzemin.server.framework.model.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 租赁结算设备对象 crec_settle_device
 *
 * @author Scott_xin
 * @date 2023-10-25
 */

@Data
public class SettleDevice {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private Long id;

    /**
     * 租赁结算id
     */
    private Long settleId;

    /**
     * 不含税金额
     */
    private BigDecimal amountExcludingTax;

    /**
     * 设备简称
     */
    private String deviceAbbreviation;

    /**
     * 设备名称
     */
    private String deviceName;

    /**
     * 租赁类型
     */
    private String leaseMethod;

    /**
     * 不含税单价
     */
    private String priceExcludingTax;

    /**
     * 结算设备ID
     */
    private Long settlementDeviceId;

    /**
     * 结算开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date settlementStartDate;

    /**
     * 结算数量
     */
    private Long settlementQuantity;

    /**
     * 结算备注
     */
    private String settlementRemarks;

    /**
     * 结算结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date settlementEndDate;

    /**
     * 数量单位
     */
    private String settlementUnit;

    /**
     * $column.columnComment
     */
    private String demandCode;

    private List<SettleOther> otherList;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("settleId", getSettleId())
                .append("amountExcludingTax", getAmountExcludingTax())
                .append("deviceAbbreviation", getDeviceAbbreviation())
                .append("deviceName", getDeviceName())
                .append("leaseMethod", getLeaseMethod())
                .append("priceExcludingTax", getPriceExcludingTax())
                .append("settlementDeviceId", getSettlementDeviceId())
                .append("settlementStartDate", getSettlementStartDate())
                .append("settlementQuantity", getSettlementQuantity())
                .append("settlementRemarks", getSettlementRemarks())
                .append("settlementEndDate", getSettlementEndDate())
                .append("settlementUnit", getSettlementUnit())
                .append("demandCode", getDemandCode())
                .toString();
    }
}
