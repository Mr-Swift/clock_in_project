package com.njusc.npm.utils.log;

import org.slf4j.LoggerFactory;

/**
 * 登陆日志
 * @author jinzf
 * @date Feb 25, 2015
 * @description TODO
 * @version 1.0
 */
public final class LoginLogger extends BaseLogger {

	/**
	 * 创建一个logger
	 */
	public LoginLogger() {
		super(LoggerFactory.getLogger(Thread.currentThread().getStackTrace()[2]
				.getClassName()));
	}

	/**
	 * 创建一个logger
	 * 
	 * @param name
	 *            logger的名称
	 */
	public LoginLogger(String name) {
		super(LoggerFactory.getLogger(name));
	}

	/**
	 * 创建一个logger
	 */
	public LoginLogger(Class<?> cls) {
		super(LoggerFactory.getLogger(cls));
	}

}
