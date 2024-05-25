package com.liuzemin.server.framework.model.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * (TplJpushDeviceT)实体类
 *
 * @author feng yi
 * @since 2020-10-30 10:38:53
 */
@ApiModel("极光推送设备")
@Data
public class JPushDevice extends BaseModel {

    @ApiModelProperty("设备id")
    private String registrationId;

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("别名")
    private String alias;

    @ApiModelProperty("用户ids")
    private List<Long> userIds;

    @ApiModelProperty("别名集合")
    private List<String> aliasList;

    @ApiModelProperty("消息ids")
    private Map<Long, List<String>> messageMap;

    @ApiModelProperty("补发短信内容")
    private Map<String, Map<String, String>> smsContent;

    @ApiModelProperty("发送时间")
    private String date;
}