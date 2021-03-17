/**
 * ErrorCodeEnum.java
 */
package com.njusc.npm.utils.enumeration;

/** 贷款申请状态
 * @author jinzf
 * @date Feb 15, 2015
 * @description TODO
 * @version 1.0
 */
public enum LoanStatusEnum {
	用户贷款申请(0),
	填写贷款主体信息(1),
	填写抵押物信息(2),
	会员提交贷款(3),
	系统初筛不通过(4),
	系统初筛通过(5),
	金融机构审核不通过(6),
	金融机构审核通过(7),
	提交合同附件(71),
	抵押融资审核中(8),
	抵押融资审核不通过(9),
	获取他项权利证书(10),
	授信失败(11),
	授信成功(12),
	申请取消(13);
	
	public final int flag;
	private LoanStatusEnum(int f){
		this.flag = f;
	}
}
