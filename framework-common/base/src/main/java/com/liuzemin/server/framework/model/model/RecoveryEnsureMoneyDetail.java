package com.liuzemin.server.framework.model.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 回收商缴纳保证金对象 crec_recovery_ensure_money_detail_t
 *
 * @author zhangxinxin
 * @date 2023-12-04
 */
@Data
public class RecoveryEnsureMoneyDetail {
    private static final long serialVersionUID = 1L;

    /** 回收商缴纳保证金id */
    private Long id;

    /** 保证金主键id */
    private Long ensureId;

    /** 物资或设别单项保证金 */
    private BigDecimal ensureMoney;

    /** 佣金比例 */
    private BigDecimal proportion;

    /** 佣金 */
    private BigDecimal commission;


    /** 实际成交额 */
    private BigDecimal volume;

    /** 退款金额 */
    private BigDecimal refundAmount;


    /** 创建人 */
    private Long createdBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date creationDate;

    /** 类型（1：设备；2：物资） */
    private Integer type;

    /** 物资设备id，(type为1，设备表id；type=2, 物资表id) */
    private Long goodsId;

    private Object goods;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("ensureId", getEnsureId())
                .append("ensureMoney", getEnsureMoney())
                .append("createdBy", getCreatedBy())
                .append("creationDate", getCreationDate())
                .append("type", getType())
                .append("goodsId", getGoodsId())
                .toString();
    }
}

