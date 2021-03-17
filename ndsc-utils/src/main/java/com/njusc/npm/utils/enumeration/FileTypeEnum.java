/**
 * ErrorCodeEnum.java
 */
package com.njusc.npm.utils.enumeration;

/**
 * @author jinzf
 * @date Feb 15, 2015
 * @description TODO
 * @version 1.0
 */
public enum FileTypeEnum {
	贷款申请表("0"),
	征信查询授权书("1"),
	头像("2"),
	
	//贷款附件
	个人或配偶身份证("bdb3c41f-cd69-11e6-891e-00ff04bee40c"),
	个人户口本("f4c6f106-cd69-11e6-891e-00ff04bee40c"),
	配偶户口本("fefca386-cd69-11e6-891e-00ff04bee40c"),
	结婚证或单身证明("0af828e6-cd6a-11e6-891e-00ff04bee40c"),
	订单合同("06b44a82-cd6d-11e6-891e-00ff04bee40c"),
	借款合同("17779ac7-cd6a-11e6-891e-00ff04bee40c"),
	最高额抵押合同("fe2ca386-cd69-11e6-891e-00ff04bee40c"),
	保险保单("1f65250c-cd6a-11e6-891e-00ff04bee40c"),
	担保推荐函("250fcecd-cd6a-11e6-891e-00ff04bee40c"),
	实地经营照片("126a432c-cd6d-11e6-891e-00ff04bee40c"),
	征信报告("319602bf-cd6a-11e6-891e-00ff04bee40c"),
	银行流水("3749ccbf-cd6a-11e6-891e-00ff04bee40c"),
	房产或车产等资产证明("3ea1a231-cd6a-11e6-891e-00ff04bee40c"),
	其它("4493d7fb-cd6a-11e6-891e-00ff04bee40c"),
	身份证正面("fefca386-cd69-11e6-891e-00ff04bhh42c"),
	身份证反面("fefca386-cd69-11e6-891e-00ff04bhh43c"),
	结婚证("fefca386-cd69-11e6-891e-00ff04bhh41c"),
	离婚证("fefca386-cd69-11e6-891e-00ff04bhh40c"),
	本人户口本("fefca386-cd69-11e6-891e-00ff04bhh44c");

	public final String flag;
	private FileTypeEnum(String f){
		this.flag = f;
	}
}
