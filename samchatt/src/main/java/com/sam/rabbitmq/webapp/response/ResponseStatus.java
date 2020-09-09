package com.sam.rabbitmq.webapp.response;

public class ResponseStatus {
	private int statusCode;
	private String message;
	
	public ResponseStatus(int statusCode, String message) {
		super();
		this.statusCode = statusCode;
		this.message = message;
	}
	
	public ResponseStatus() {
		// TODO Auto-generated constructor stub
	}
	
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
