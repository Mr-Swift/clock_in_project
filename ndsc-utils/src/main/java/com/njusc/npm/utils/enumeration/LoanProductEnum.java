/**
 * ErrorCodeEnum.java
 */
package com.njusc.npm.utils.enumeration;

/** 贷款产品
 * @author jinzf
 * @date Feb 15, 2015
 * @description TODO
 * @version 1.0
 */
public enum LoanProductEnum {
	JND("京农贷","10","京东金融","/jdApplyLoan","applyloan/jdFinance/"),
	NYR("农e融","20","江苏银行","/jsbApplyLoan","applyloan/jsBank/");
	
	public final String name;//贷款产品名称
	public final String code;//贷款产品编码
	public final String company;//贷款机构
	public final String applyurl;//贷款链接地址  例   /loan/index.htm
	public final String jspurl;//页面路径  例   /loan/applyloan
	private LoanProductEnum(String name,String code,String company,String applyurl,String jspurl){
		this.name = name;
		this.code = code;
		this.company = company;
		this.applyurl = applyurl;
		this.jspurl = jspurl;
	}
}
