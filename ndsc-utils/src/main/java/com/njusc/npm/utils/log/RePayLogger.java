package com.njusc.npm.utils.log;

import org.slf4j.LoggerFactory;

/**
 * 还款日志
 * @description TODO
 * @version 1.0
 */
public final class RePayLogger extends BaseLogger {

	/**
	 * 创建一个logger
	 */
	public RePayLogger() {
		super(LoggerFactory.getLogger(Thread.currentThread().getStackTrace()[2]
				.getClassName()));
	}

	/**
	 * 创建一个logger
	 * 
	 * @param name
	 *            logger的名称
	 */
	public RePayLogger(String name) {
		super(LoggerFactory.getLogger(name));
	}

	/**
	 * 创建一个logger
	 */
	public RePayLogger(Class<?> cls) {
		super(LoggerFactory.getLogger(cls));
	}

}
