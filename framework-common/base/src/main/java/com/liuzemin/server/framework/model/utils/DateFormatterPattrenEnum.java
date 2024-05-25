package com.liuzemin.server.framework.model.utils;

import java.util.Calendar;

public enum DateFormatterPattrenEnum {
	yyyyMMddHHmmss(new String[] {"yyyy-MM-dd HH:mm:ss","yyyy/MM/dd HH:mm:ss","yyyyMMdd HH:mm:ss"},new int[] {Calendar.MILLISECOND},1),
	yyyyMMddHHmm(new String[] {"yyyy-MM-dd HH:mm","yyyy/MM/dd HH:mm","yyyyMMdd HH:mm"},new int[] {Calendar.SECOND,Calendar.MILLISECOND},2),
	yyyyMMddHH(new String[] {"yyyy-MM-dd HH","yyyy/MM/dd HH","yyyyMMdd HH"},new int[] {Calendar.MINUTE,Calendar.SECOND,Calendar.MILLISECOND},3),
	yyyyMMdd(new String[] {"yyyy-MM-dd","yyyy/MM/dd","yyyyMMdd"},new int[] {Calendar.HOUR_OF_DAY,Calendar.MINUTE,Calendar.SECOND,Calendar.MILLISECOND},4),
	yyyyMM(new String[] {"yyyy-MM","yyyy/MM","yyyyMM"},new int[] {Calendar.DAY_OF_MONTH,Calendar.HOUR_OF_DAY,Calendar.MINUTE,Calendar.SECOND,Calendar.MILLISECOND},5),
	yyyy(new String[] {"yyyy"},new int[] {Calendar.MONTH,Calendar.DAY_OF_MONTH,Calendar.HOUR_OF_DAY,Calendar.MINUTE,Calendar.SECOND,Calendar.MILLISECOND},6),
	;
	private String[] pattrens;
	private int[] zeroFields;
	/**等级，数字越小，等级越高*/
	private int level;
	public String[] getPattrens() {
		return pattrens;
	}
	public int[] getZeroFields() {
		return zeroFields;
	}
	public int getLevel() {
		return level;
	}
	private DateFormatterPattrenEnum(String[] pattrens, int[] zeroFields, int level) {
		this.pattrens = pattrens;
		this.zeroFields = zeroFields;
		this.level = level;
	}
}