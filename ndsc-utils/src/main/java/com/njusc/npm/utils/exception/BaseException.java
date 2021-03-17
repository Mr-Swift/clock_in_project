package com.njusc.npm.utils.exception;

/**
 * @author jinzf
 * @date Oct 13, 2014
 * @description TODO
 * @version 1.0
 */
public class BaseException extends Exception {

	public BaseException() {
		super();
	}

	public BaseException(String message, Throwable cause) {
		super(message, cause);
	}

	public BaseException(String message) {
		super(message);
	}

	public BaseException(Throwable cause) {
		super(cause);
	}

}
