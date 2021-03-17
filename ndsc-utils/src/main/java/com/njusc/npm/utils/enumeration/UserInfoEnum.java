/**
 * ErrorCodeEnum.java
 */
package com.njusc.npm.utils.enumeration;

/** 会员信息数据枚举类
 * @author jinzf
 * @date Feb 15, 2015
 * @description TODO
 * @version 1.0
 */
public enum UserInfoEnum {
	更新时间(0),
	贷款申请(1),
	贷款成功申请(2),
	贷款失败申请(3),
	成功交易(4);
	public final int index;
	private UserInfoEnum(int i){
		this.index = i;
	}
}
