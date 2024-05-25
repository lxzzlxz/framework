package com.liuzemin.server.framework.model.constant;

public enum JobTopicEnum {

    //供应商认证通过
    APPROVE_PASSED_NOTIFY("1"),
    //供应商（社会需求方）认证驳回
    APPROVE_REJECT_NOTIFY("2"),
    //社会需求方认证通过
    DEMANDER_APPROVE_PASSED_NOTIFY("3"),
    //供应商收到需求匹配消息通知
    DEMAND_NOTIFY("4"),
    //需求方收到需求截止后7天未选择供应商的提醒
    DEMAND_SOON_CLOSE_NOTIFY("5"),
    //供应商收到中选的消息通知
    SELECTED_NOTIFY("6"),
    //供应商收到未中选的消息通知
    NOT_SELECTED_NOTIFY("7"),
    //战略需求方和区域经销商认证通过
    DEMANDER_PASSED_NOTIFY("8"),
    //战略需求方和区域经销商认证失败
    DEMANDER_REJECT_NOTIFY("9"),
    //订单延迟关闭
    ORDER_DELAY_CLOSE("10"),
    //订单服务生效
    ORDER_SERVICE_COMPLETE("11"),
    //会员权益到期通知
    MEMBERSHIP_EXPIRE_NOTIFY("12"),
    //供应商复审
    SUPPLIER_REVIEW_NUMBER("13") ,
    //物流运输需求推送
    LOGISTICS_NOTIFY("14"),
    //回收商认证驳回
    RECOVERY_REJECT_NUMBER("15"),
    //回收商认证通过
    RECOVERY_PASSED_NUMBER("16"),
    //需求认证驳回
    DEMAND_REJECT_NUMBER("17"),
    //需求认证通过
    DEMAND_PASSED_NUMBER("18");

    //推送让供应商上传设备,先不做
    // 开通会员VIP的消息通知先不做

    private String value;

    JobTopicEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
