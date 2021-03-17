/**
 * ErrorCodeEnum.java
 */
package com.njusc.npm.utils.enumeration;

/** 申请付款状态
 * @author jinzf
 * @date Feb 15, 2015
 * @description TODO
 * @version 1.0
 */
public enum ApplyPayEnum {
	未提交(0),
	待审核(1),
	初审通过(2),
	初审不通过(3),
	待放款(4),
	放款成功(5),
	放款失败(6),
	已打款(100),  //线下打款给商户
	拒绝打款(101), //线下不给商户打款
	放贷申请取消(200);
	
	
	public final int flag;
	private ApplyPayEnum(int f){
		this.flag = f;
	}
}
