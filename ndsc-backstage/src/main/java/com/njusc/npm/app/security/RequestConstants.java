package com.njusc.npm.app.security;

/**
 * 统一请求体的参数名称
 * （请求时间+请求uuid+版本号+前后台约定的md5字符串）
 * 即先拼接（request_time++usystemid+system_version+MD5_STR)，后使用md5生成摘要，并与signature比较是否一致，如果一直，则表示请求合法有效
 */
public class RequestConstants {
	//访问登陆权限的参数名
	public static final String ACCESSTOKEN = "accesstoken";
	
	//验证登录权限通过后，将userid放置到请求体中
	public static final String APPUSERID = "wqlwcuserid";
	
	//请求时间,System.currentTimeMillis()
	public static final String REQUESTTIME = "request_time";
	
	//随机数，即UUID
	public static final String USYSTEMID = "usystemid";
	
	//版本号
	public static final String SYSTEM_VERSION = "system_version";
	
	//MD5字符串,需提供给网站端
	public static final String MD5_STR = "1.0.0NJUSCgrFC)hn2&o";
	
	//MD5摘要名
	public static final String SIGNATURE = "signature";
}
