package com.njusc.npm.app.security;

/**
 * redis数据存储工具类
 */
public class RedisConstants {
	
	//REIDS中TOKEN的前缀
	public static final String TOKEN = "TOKEN";
	//私钥存储前缀
	public static final String PRIMARY_KEY = "PRIMARYKEY";
	//REDIS中token存储时间
	public static final int TOKEN_SECONDS = 30*24*60*60;
	//REDIS中私钥存储时间
	public static final int  PRIMARYKEY_SECONDS = 10;
	
	
}
