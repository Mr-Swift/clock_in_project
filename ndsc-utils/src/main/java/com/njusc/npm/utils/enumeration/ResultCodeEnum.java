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
public enum ResultCodeEnum {
    未知的状态码(-1),
	成功(0),
	请勿重复提交(1088),
	接口未查询到数据(404),
	接口连接异常(5005),
	接口签名验证失败(5006),
	账户密码出错(1000),
	用户不存在(1001),
	用户已存在(1002),
	验证码错误(1003),
	验证码失效(1004),
	验证码已验证(1005),
	用户名格式不正确(1006),
	密码格式不正确(1007),
	手机号格式不正确(1008),
	手机号已存在(1009),
	手机号错误(1010),
	身份证格式不正确(1011),
	身份证错误(1012),
	邮箱格式不正确(1013),
	邮箱错误(1014),
	银行卡已被绑定(1015),
	每个账户银行卡绑定已达最大值(1016),
	银行卡号格式错误(1017),
	服务协议未同意(1018),
	鉴证书无效(1019),
	身份证号码已绑定(1020),
	邮箱未绑定(1021),
	用户已禁用(1022),
	未实名认证(1023),
	年龄不符合贷款要求(1024),
	京东账户未绑定(1025),
	抵押物剩余价值不足(1026),
	用款申请已删除(1027),
	用款申请已完成初审(1028),
	无可用支付方式(1029),
	账户过期(1030),
	可用余额不足(1031),
	账户不存在(1032),
	尚有未完成填写的贷款申请(1033),
	实名认证失败(1034),
	异常访问(1100),
	页面过期(1101),
	此功能当日短信已用完(1102),
	此ip当日短信已用完(1103),
	绑定京东账号失败(1104),
	保存用款申请失败(1105),
	绑定银行账号失败(1106),
	有贷款违约记录(1107),
	当前用户在其他地方登录请重新登录后再试(1035),
	//----------------安全设置begin-----------------------//
	原密码格式不正确(2100),
	新密码格式不正确(2101),
	原密码错误(2102),
	邮箱验证码错误(2103),
	验证码发送过于频繁(2104),
	邮箱已存在(2105),
	安保问题错误(2106),
	已完成实名认证(2107),
	//----------------安全设置end-------------------------//
	连续登录失败次数超过3次(1010),
	
	//----------------阳光村务-------------------------//
	地区代码已存在(3000),
	不能删除根节点(3001),
	不能删除存在后台用户的单位及其子单位(3002),
	不存在版本(9995),
	非法请求(9996),
	请重新登陆(9997),
	非法身份(9998),	
	参数异常(9999),
	系统异常(10000),
  //----------------短信-------------------------//
	短信验证码发送过频繁(160038),
	超出同模板同号天发送次数上限(160039),
	验证码超出同模板同号码天发送上限(160040),
	通知超出同模板同号码天发送上限(160041), 		
	号码格式有误(160042), 		
	应用与模板id不匹配(160043),		
	短信发送失败 (160050),

    //---------------------------------------//
	未查询到相应的数据(400),
    //--------导入专用------------------//
	数据格式有误(2001);
	public final int flag;
	private ResultCodeEnum(int f){
		this.flag = f;
	}
}
