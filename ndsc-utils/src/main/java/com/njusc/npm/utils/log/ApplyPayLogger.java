package com.njusc.npm.utils.log;

import org.slf4j.LoggerFactory;

/**
 * 线下用款申请日志
 * @description TODO
 * @version 1.0
 */
public final class ApplyPayLogger extends BaseLogger {

	/**
	 * 创建一个logger
	 */
	public ApplyPayLogger() {
		super(LoggerFactory.getLogger(Thread.currentThread().getStackTrace()[2]
				.getClassName()));
	}

	/**
	 * 创建一个logger
	 * @param name  logger的名称
	 */
	public ApplyPayLogger(String name) {
		super(LoggerFactory.getLogger(name));
	}

	/**
	 * 创建一个logger
	 */
	public ApplyPayLogger(Class<?> cls) {
		super(LoggerFactory.getLogger(cls));
	}

}
