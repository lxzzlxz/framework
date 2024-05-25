package com.liuzemin.server.framework.model.utils;

public enum RedisKeyType {

	SYSTEM_DICT_ALL("sys_dict_all",7),
	SYSTEM_MEMBER_PRICE("sys_mem_price",7)
	;
	
	private String prefix;// 前缀
	private long expiredTime; // 有效期
	
	RedisKeyType(String prefix,long expiredTime){
		this.prefix=prefix;
		this.expiredTime=expiredTime;
	}
	
	public String getPrefix() {
		return prefix;
	}

	public long getExpiredTime() {
		return expiredTime;
	}

	@Override
	public String toString() {
		return this.getPrefix();
	}
}
