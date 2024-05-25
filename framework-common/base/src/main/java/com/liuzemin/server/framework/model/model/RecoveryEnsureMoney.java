package com.liuzemin.server.framework.model.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 保证金状态表
 * @author Scott_xin
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RecoveryEnsureMoney extends BaseModel {

    private static final long serialVersionUID = 1L;

    /** 回收商缴纳保证金id */
    private Long id;

    /** 废旧物资需求id */
    private Long demandId;

    /** 废旧物资需求id */
    private String  demandCode;

    /** 回收商ID */
    private Long recoveryId;

    /** 回收商名称 */
    private String recoveryName;

    /** 回收商管理员电话 */
    private String phone;

    /** 保证金 */
    private BigDecimal ensureMoney;

    /** 订单id */
    private Long orderId;
    /** 订单编号 */
    private String orderNo;

    /** 状态（0：未交；1：已交） */
    private Integer status;

    /** 生效时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date activeTime;

    /** 是否选中 */
    private String isSelect;

    /** 佣金 */
    private BigDecimal commission;

    /** 支付回执单 */
    private String receipt;

    /** 支付结算单 */
    private String settlement;

    /** 实际成交额 */
    private BigDecimal volume;

    /** 退款金额 */
    private BigDecimal refundAmount;

    /** 退款状态 -1：未达到退款条件  0：未退款（已达到退款条件） 1：退款排队中 2：退款中 待银行确认 3：已退款  4：退款失败
     */
    private Integer refundStatus;


    /** 提交退款时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date settlementTime;

    List<RecoveryEnsureMoneyDetail> detailList;
    /** 需求状态 */
    private Integer demandStatus;
    /** 计划退款时间  */
    private Date planRefundTime;

    /** 用户申请退款状态    0：未申请，1：已申请 */
    private Integer applyStatus;

    /** 管理员退款确认状态    0：未确认，1：已确认 */
    private Integer confirmStatus;

    /** 驳回原因 */
    private String rejectReason;

    /** 驳回历史 */
    private List<RejectRecord> rejectRecords;

    /** 退款确认人 */
    private String confirmUser;

    /** 退款确认时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date confirmTime;


}
