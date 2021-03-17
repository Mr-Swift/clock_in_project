/**
 * ErrorCodeEnum.java
 */
package com.njusc.npm.utils.enumeration;

/** 系统数据枚举类
 * @author jinzf
 * @date Feb 15, 2015
 * @description TODO
 * @version 1.0
 */
public enum SystemDataEnum {
	总览(100),
	会员总数量(101),		//非数据库类别
	总提交申请贷款笔数(102),	//非数据库类别
	总提交申请贷款金额(103),	//非数据库类别
	总成功申请贷款笔数(104),	//非数据库类别
	总已成功贷款金额(105),	//非数据库类别
	
	新增会员1次(1),
	提交贷款1次(2),
	提交申请贷款金额(3),
	贷款发放失败1次(4),
	贷款发放失败金额(5),
	贷款发放成功1次(6),
	已成功发放贷款金额(7),
	贷款使用1次(8),
	贷款使用金额(9),
	贷款还款金额(10),
	交易金额(11),
	交易1次(12);
	public final int index;
	private SystemDataEnum(int i){
		this.index = i;
	}
}
