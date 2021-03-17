/**
 * ErrorCodeEnum.java
 */
package com.njusc.npm.utils.enumeration;

/**贷款用途
 * @author jinzf
 * @date Feb 15, 2015
 * @description TODO
 * @version 1.0
 */
public enum LoanUseEnum {
	购买农资农具("1"),
	农业基础设施建设("2");
	
	public final String flag;
	public String getFlag() {
		return flag;
	}
	private LoanUseEnum(String f){
		this.flag = f;
	}
}
