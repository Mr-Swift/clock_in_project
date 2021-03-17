package com.njusc.npm.utils.log;

import org.slf4j.LoggerFactory;

/**
 * 申请贷款日志
 * @author jinzf
 * @date Feb 25, 2015
 * @description TODO
 * @version 1.0
 */
public final class ApplyLoanLogger extends BaseLogger {

	/**
	 * 创建一个logger
	 */
	public ApplyLoanLogger() {
		super(LoggerFactory.getLogger(Thread.currentThread().getStackTrace()[2]
				.getClassName()));
	}

	/**
	 * 创建一个logger
	 * 
	 * @param name
	 *            logger的名称
	 */
	public ApplyLoanLogger(String name) {
		super(LoggerFactory.getLogger(name));
	}

	/**
	 * 创建一个logger
	 */
	public ApplyLoanLogger(Class<?> cls) {
		super(LoggerFactory.getLogger(cls));
	}

}
