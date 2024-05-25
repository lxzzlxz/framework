package com.liuzemin.server.framework.model.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.liuzemin.server.framework.model.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * (CrecOrderT)实体类
 *
 * @author feng yi
 * @since 2021-04-07 10:15:06
 */
@ApiModel("订单")
@Data
public class Order extends BaseModel {


    @ApiModelProperty("下单企业id")
    private Long enterpriseId;

    private List<Long> enterpriseIdList;

    @ApiModelProperty("订单类型（1,vip,2.人才招聘）")
    private Integer orderType;

    @ApiModelProperty("交易号（支付宝、微信等的交易号）")
    private String tradeNo;

    @ApiModelProperty("订单来源（1微信，2支付宝，3银联(云闪付),4银行转账）")
    private Integer tradeSource;

    @Excel(name = "订单编号", width = 20)
    @ApiModelProperty("商户订单号（系统创建的订单号）")
    private String outTradeNo;

    @ApiModelProperty("商户订单号（系统创建的订单号）")
    private String orderNo;

    @ApiModelProperty("订单状态（1，待支付，2支付成功，3支付失败,4交易关闭,5已退款,6.已完成")
    private Integer orderStatus;


    @Excel(name = "本次缴费金额", width = 20)
    @ApiModelProperty("订单金额(单位为人民币（元），精确到小数点后2位)")
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal totalAmount;

    @ApiModelProperty("订单标题")
    private String subject;

    @ApiModelProperty("商品描述")
    private String body;


    @ApiModelProperty("交易付款时间")
    //  @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date paymentTime;

    @Excel(name = "缴费时间", width = 20)
    @ApiModelProperty("交易付款时间")
    private String paymentTimeShort;

    /**
     * 购买期限
     */
    @ApiModelProperty("购买期限")
    private Integer purchaseTerm;
    /**
     * 购买期限单位（1年）
     */
    @ApiModelProperty("购买期限单位（1年）")
    private Integer purchaseTermUnit;
    /**
     * 会员权益id
     */
    @ApiModelProperty("会员权益id")
    private Long membershipRightsId;

    /**
     * 华夏系统生成，发往微信支付宝银联通道的通道交易订单号
     */
    @ApiModelProperty("商户通道交易订单号")
    private String channelNo;
    /**
     * 华夏交易时间格式 yyyyMMddHHmmss
     */
    @ApiModelProperty("交易时间")
    //   @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date pcsDate;

    @ApiModelProperty("总退款金额")
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal refundAmount;

    @ApiModelProperty("退款时间")
    private Date refundDate;

    @ApiModelProperty("关闭时间")
    private Date closeDate;

    /**
     * 总价折扣
     */
    @ApiModelProperty("总价折扣")
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal discount;
    /**
     * 是否删除，1：否，2：是
     */
    @ApiModelProperty("是否删除，1：否，2：是")
    private Integer deleteFlag;

    @ApiModelProperty("退款状态，0：未退，1：已退")
    private Integer refundStatus;

    @ApiModelProperty("是否删除，1：否，2：是")
    private String deleteFlagName;
    /**
     * 发票状态（0未开，1已开）
     */
    @ApiModelProperty("发票状态（0未开，1已开,2待开票，5开票失败）")
    private Integer invoiceStatus;

    @ApiModelProperty("支付时间（按月）")
    private String payTime;

    @ApiModelProperty("订单详情")
    private List<OrderDetail> orderDetailList;

    @ApiModelProperty("会员权益")
    private MembershipRights membershipRights;

    /**
     * 订单来源名称
     */
    private String tradeSourceName;
    /**
     * 退款状态
     */
    private String refundStatusName;

    /**
     * 订单状态名称
     */
    private String orderStatusName;

    @ApiModelProperty(value = "应付金额", notes = "应付金额=年度上限-累计已缴")
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal amountPayable;

    private List<Long> ids;

    private List<String> outTradeNos;

    @ApiModelProperty("查询开始时间")
    private String startDate;

    @ApiModelProperty("查询结束时间")
    private String endDate;

    @ApiModelProperty("时间类型(1月2年3日)")
    private String dateType;
    @ApiModelProperty("时间类型(1月2年3日)")
    private String refundDateType;

    @ApiModelProperty("供应商名称")
    private String supplierName;

    private String dateFormatStr;
    private String refundDateFormatStr;
    private String invoiceStatusName;

    @ApiModelProperty("推荐单位")
    private Long recommendedUnit;

    @ApiModelProperty(value = "一级单位")
    private Long firstLevelUnit;

    @ApiModelProperty(value = "发票号码")
    private String fphm;

    @ApiModelProperty(value = "发票代码")
    private String fpdm;

    @ApiModelProperty(value = "0：专用发票 2：普通发票 41：卷式发票 51：电子发票 61：电子专用发票")
    private String invoiceType;

    @ApiModelProperty(value = "开票日期")
    private String kprq;

    @ApiModelProperty(value = "不含税金额")
    private String hjbhsje;

    @ApiModelProperty(value = "价税合计金额")
    private String jshjje;
    @ApiModelProperty(value = "发票类型名称")
    private String invoiceTypeName;

    @ApiModelProperty(value = "订单类型名称")
    private String orderTypeName;

    @ApiModelProperty(value = "用户类型名称")
    private String enterpriseTypeName;

    @ApiModelProperty("所属企业类型（1.供应商，2.需求方， 7.区域经销商，8.经销商推荐，11.项目部，12.中介公司，13.个人，14.注册用户'）")
    private Integer enterpriseType;

    @ApiModelProperty("商品类型（1招聘方卡，2求职方卡）")
    private Integer productType;

}
