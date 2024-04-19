package com.liuzemin.server.framework.model.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends UserPriciple {

	private static final long serialVersionUID = 3851565970060361266L;

	/**
	 * 状态，1：启用，2：锁定
	 */
	@ApiModelProperty(value = "状态，1：启用，2：锁定 必填", required = false)
	private Integer status;

	/**
	 * 是否删除，1：否，2：是
	 */
	private Integer deleteFlag;

	/**
	 * 生日
	 */
	private Date birthDate;

	/**
	 * 性别1：男，2：女
	 */
	@ApiModelProperty(value = "性别，1：男，2：女 必填", required = false)
	private Integer gender;

	/**
	 * 头像
	 */
	@ApiModelProperty(value = "头像", required = false)
	private String headImg;

	@ApiModelProperty(value = "用户旧密码", required = false)
	private String oldPassword;

	private List<Long> userIds;

	@ApiModelProperty(value = "身份证正面照片", required = false)
	private String idCardFront;

	@ApiModelProperty(value = "身份证背面照片", required = false)
	private String idCardSide;

	@ApiModelProperty(value = "认证状态 0.未认证 1.认证成功 2.认证失败", required = false)
	private Integer authStatus;

	@ApiModelProperty(value = "身份证合照", required = false)
	private String idCardImg;

	@ApiModelProperty("真实姓名")
	private String realName;

	@ApiModelProperty("认证次数")
	private Integer authTimes;

	private List<Long> ids;

	@ApiModelProperty("时间类型(0日1月2年)")
	private String dateType;

	@ApiModelProperty("开始时间")

	private String startDate;
	@ApiModelProperty("结束时间")
	private String endDate;

	@ApiModelProperty("时间格式")
	private String dateFormatStr;


	@Override
	public String toString() {
		return "User{" +
				"status=" + status +
				", deleteFlag=" + deleteFlag +
				", birthDate=" + birthDate +
				", gender=" + gender +
				", headImg='" + headImg + '\'' +
				", oldPassword='" + oldPassword + '\'' +
				", userIds=" + userIds +
				", idCardFront='" + idCardFront + '\'' +
				", idCardSide='" + idCardSide + '\'' +
				", authStatus=" + authStatus +
				", idCardImg='" + idCardImg + '\'' +
				", realName='" + realName + '\'' +
				", authTimes=" + authTimes +
				'}';
	}
}
