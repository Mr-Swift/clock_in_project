package com.njusc.npm.utils.log;

import org.slf4j.LoggerFactory;

/**
 * controller方法执行时间的日志
 * @author jinzf
 * @date Feb 25, 2015
 * @description TODO
 * @version 1.0
 */
public final class ExecuteTimeLogger extends BaseLogger {

	/**
	 * 创建一个logger
	 */
	public ExecuteTimeLogger() {
		super(LoggerFactory.getLogger(Thread.currentThread().getStackTrace()[2]
				.getClassName()));
	}

	/**
	 * 创建一个logger
	 * 
	 * @param name
	 *            logger的名称
	 */
	public ExecuteTimeLogger(String name) {
		super(LoggerFactory.getLogger(name));
	}

	/**
	 * 创建一个logger
	 */
	public ExecuteTimeLogger(Class<?> cls) {
		super(LoggerFactory.getLogger(cls));
	}

}
