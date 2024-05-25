package com.liuzemin.server.framework.model.model;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

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

	public String getIdCardFront() {
		return idCardFront;
	}

	public void setIdCardFront(String idCardFront) {
		this.idCardFront = idCardFront;
	}

	public String getIdCardSide() {
		return idCardSide;
	}

	public void setIdCardSide(String idCardSide) {
		this.idCardSide = idCardSide;
	}

	public Integer getAuthStatus() {
		return authStatus;
	}

	public void setAuthStatus(Integer authStatus) {
		this.authStatus = authStatus;
	}

	public String getIdCardImg() {
		return idCardImg;
	}

	public void setIdCardImg(String idCardImg) {
		this.idCardImg = idCardImg;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public List<Long> getUserIds() {
		return userIds;
	}

	public void setUserIds(List<Long> userIds) {
		this.userIds = userIds;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Integer getAuthTimes() {
		return authTimes;
	}

	public void setAuthTimes(Integer authTimes) {
		this.authTimes = authTimes;
	}

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

	public List<Long> getIds() {
		return ids;
	}

	public void setIds(List<Long> ids) {
		this.ids = ids;
	}

	public String getDateType() {
		return dateType;
	}

	public void setDateType(String dateType) {
		this.dateType = dateType;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getDateFormatStr() {
		return dateFormatStr;
	}

	public void setDateFormatStr(String dateFormatStr) {
		this.dateFormatStr = dateFormatStr;
	}
}
