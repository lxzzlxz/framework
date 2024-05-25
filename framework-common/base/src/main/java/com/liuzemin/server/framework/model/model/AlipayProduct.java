package com.liuzemin.server.framework.model.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class AlipayProduct implements Serializable {

	private static final long serialVersionUID = 7056027840331165000L;

	/**
	 * 商品ID,64个字符以内、可包含字母、数字、下划线；需保证在商户端不重复
	 */
	@ApiModelProperty(value = "商户订单号", required = false)
	private String outTradeNo;

	/**
	 * 销售产品码
	 */
	@ApiModelProperty(value = "销售产品码，目前仅支持FAST_INSTANT_TRADE_PAY", required = false)
	private String productCode;
	/**
	 * 支付金额
	 */
	@ApiModelProperty(value = "订单总金额，单位为元，精确到小数点后两位。", required = true)
	private BigDecimal totalAmount;

	/**
	 * 订单名称
	 */
	@ApiModelProperty(value = "订单标题", required = true)
	private String subject;

	/**
	 * 商品描述
	 */
	@ApiModelProperty(value = "商品描述", required = false)
	private String body;

	/**
	 * 订单包含的商品列表信息，Json格式： {&quot;show_url&quot;:&quot;https://或http://打头的商品的展示地址&quot;} ，在支付时，可点击商品名称跳转到该地址
	 */
	@ApiModelProperty(value = "商品详情", required = false)
	private String goodsDetail;

	/**
	 * 扩展参数
	 */
	@ApiModelProperty(value = "扩展参数", required = false)
	private String extendParams;

	/**
	 * 公用回传参数，如果请求时传递了该参数，则返回给商户时会回传该参数。支付宝只会在异步通知时将该参数原样返回。本参数必须进行UrlEncode之后才可以发送给支付宝
	 */
	@ApiModelProperty(value = "公共回传参数", required = false)
	private String passbackParams;

	@ApiModelProperty(value = "商品类型，0：虚拟商品，1：实物商品（默认），虚拟商品不支持花呗分期 ", required = false)
	private String goodsType;

	@ApiModelProperty(value = "该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 " + "该参数数值不接受小数点， 如 1.5h，可转换为 90m。\n"
			+ "该参数在请求到支付宝时开始计时。", required = false)
	private String timeoutExpress;

	@ApiModelProperty(value = "商户订单号", required = false)
	private Long userId;
	private SupplierServiceability supplierServiceability;
	
}
