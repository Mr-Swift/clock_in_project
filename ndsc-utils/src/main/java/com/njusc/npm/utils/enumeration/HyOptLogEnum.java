package com.njusc.npm.utils.enumeration;

/**
 * 会员操作日志对应关系
 * @author jinzf
 * */
public enum HyOptLogEnum {
	hy_login("会员登录", 1),
	hy_regedit("会员注册",2),
	hy_forgetpwd("忘记密码",3),
	hy_modify_email("会员修改邮箱",4),
	hy_modify_phone("修改手机号码",5),
	hy_modify_pwd("修改登录密码",6),
	hy_modify_safequestion("修改安全问题",7),
	hy_modify_realname("实名认证",8),
	hy_applyloan("会员申请贷款",101);
	// 构造方法
	private HyOptLogEnum(String name, int code) {
		this.name = name;
		this.code = code;
	}

	private String name;
	private int code;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}
