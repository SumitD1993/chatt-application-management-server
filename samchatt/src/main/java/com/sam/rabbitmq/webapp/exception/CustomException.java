package com.sam.rabbitmq.webapp.exception;

public class CustomException extends Exception {
	
	private static final long serialVersionUID = 238373499304348364L;

	public CustomException(Integer statusCode) {
		super(statusCode+"");
	}
}
