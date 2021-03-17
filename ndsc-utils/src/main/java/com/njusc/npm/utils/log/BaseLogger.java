package com.njusc.npm.utils.log;

import org.slf4j.Logger;

/**
 * @author jinzf
 * @date Mar 3, 2015
 * @description TODO
 * @version 1.0
 */
public abstract class BaseLogger {

	protected final Logger logger;

	public BaseLogger(Logger log) {
		this.logger = log;
	}

	public void error(String msgPattern) {

		if (logger.isErrorEnabled()) {
			logger.error(msgPattern);
		}
	}

	public void error(String msgPattern, Object... os) {

		if (logger.isErrorEnabled()) {
			logger.error(msgPattern, os);
		}
	}

	public void error(Throwable cause, String msgPattern, Object... objects) {

		if (logger.isErrorEnabled()) {
			logger.error(msgPattern, objects, cause);
		}
	}

	public void error(Throwable cause, String msgPattern) {

		if (logger.isErrorEnabled()) {
			logger.error(msgPattern, cause);
		}
	}

	// ===========
	public void warn(String msgPattern) {
		if (logger.isWarnEnabled()) {
            logger.warn(msgPattern);
        }
	}

	public void warn(String msgPattern, Object... os) {
		if (logger.isWarnEnabled()) {
            logger.warn(msgPattern, os);
        }
	}

	public void warn(Throwable cause, String msgPattern, Object... objects) {
		if (logger.isWarnEnabled()) {
            logger.warn(msgPattern, objects, cause);
        }
	}

	public void warn(Throwable cause, String msgPattern) {
		if (logger.isWarnEnabled()) {
            logger.warn(msgPattern, cause);
        }
	}

	// ===========
	public void info(String msgPattern) {
		if (logger.isInfoEnabled()) {
            logger.info(msgPattern);
        }
	}

	public void info(String msgPattern, Object... os) {
		if (logger.isInfoEnabled()) {
            logger.info(msgPattern, os);
        }
	}

	public void info(Throwable cause, String msgPattern, Object... objects) {
		if (logger.isInfoEnabled()) {
            logger.info(msgPattern, objects, cause);
        }
	}

	public void info(Throwable cause, String msgPattern) {
		if (logger.isInfoEnabled()) {
            logger.info(msgPattern, cause);
        }
	}

	// ===========
	public void debug(String msgPattern) {
		if (logger.isDebugEnabled()) {
            logger.debug(msgPattern);
        }
	}

	public void debug(String msgPattern, Object... os) {
		if (logger.isDebugEnabled()) {
            logger.debug(msgPattern, os);
        }
	}

	public void debug(Throwable cause, String msgPattern, Object... objects) {
		if (logger.isDebugEnabled()) {
            logger.debug(msgPattern, objects, cause);
        }
	}

	public void debug(Throwable cause, String msgPattern) {
		if (logger.isDebugEnabled()) {
            logger.debug(msgPattern, cause);
        }
	}
}
