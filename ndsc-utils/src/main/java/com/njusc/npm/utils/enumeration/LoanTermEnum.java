/**
 * ErrorCodeEnum.java
 */
package com.njusc.npm.utils.enumeration;

/** 贷款申请期限
 * @author jinzf
 * @date Feb 15, 2015
 * @description TODO
 * @version 1.0
 */
public enum LoanTermEnum {
	_12个月("12");
	
	public final String flag;
	public String getFlag() {
		return flag;
	}
	private LoanTermEnum(String f){
		this.flag = f;
	}
}
