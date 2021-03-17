/**
 * ErrorCodeEnum.java
 */
package com.njusc.npm.utils.enumeration;

/** 订单状态
 * @author jinzf
 * @date Feb 15, 2015
 * @description TODO
 * @version 1.0
 */
public enum OrderEnum {
	未提交订单(0),
	待付款(1),
	已付款(2),
	待出库(3),
	付款失败(4),
	待收货(5),
	订单完成(100), 
	订单取消(200);
	
	
	public final int flag;
	private OrderEnum(int f){
		this.flag = f;
	}
}
