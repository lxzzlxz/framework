package com.liuzemin.server.framework.model.constant;

/**
 * @author Scott_xin
 */
public class DataTransLogConstant {

    /**
     * 数据类型 供应商
     */
    public static final Integer LOG_TYPE_SUPPLIER = 1;


    /**
     * 数据类型 需求
     */
    public static final Integer LOG_TYPE_DEMAND = 2;

    /**
     * 推送状态 未发送
     */
    public static final Integer LOG_STATUS_UN_SEND = 1;

    /**
     * 推送状态 成功
     */
    public static final Integer LOG_STATUS_SUCCESS = 2;

    /**
     * 推送状态 失败
     */
    public static final Integer LOG_STATUS_FAILED = 3;
    /**
     * 推送状态 失败
     */
    public static final String SEND_TYPE_POST = "POST";
    /**
     * 推送状态 失败
     */
    public static final String SEND_TYPE_GET = "GET";

    public static final String SUPPLIER_URL = "https://jzgj.crfeb.com.cn/api/realTimeLeasing/suppliers?app_key=6BECD2CC7B187CCC3F572FF46BEE00C5&app_secret=FEE9E1A59DC08E199C3309B7FF6C3113";
    public static final String DEMAND_URL = "https://jzgj.crfeb.com.cn/api/realTimeLeasing/demand?app_key=6BECD2CC7B187CCC3F572FF46BEE00C5&app_secret=FEE9E1A59DC08E199C3309B7FF6C3113";
}
