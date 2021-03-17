package com.njusc.npm.utils.log;

import org.slf4j.LoggerFactory;

/**
 * 会员操作、安全中心日志
 * @author jinzf
 * @date Feb 25, 2015
 * @description TODO
 * @version 1.0
 */
public final class SmsLogger extends BaseLogger {

	/**
	 * 创建一个logger
	 */
	public SmsLogger() {
		super(LoggerFactory.getLogger(Thread.currentThread().getStackTrace()[2]
				.getClassName()));
	}

	/**
	 * 创建一个logger
	 * 
	 * @param name
	 *            logger的名称
	 */
	public SmsLogger(String name) {
		super(LoggerFactory.getLogger(name));
	}

	/**
	 * 创建一个logger
	 */
	public SmsLogger(Class<?> cls) {
		super(LoggerFactory.getLogger(cls));
	}

}
