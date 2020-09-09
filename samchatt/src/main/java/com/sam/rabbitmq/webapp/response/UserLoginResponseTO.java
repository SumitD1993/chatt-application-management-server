package com.sam.rabbitmq.webapp.response;

import com.sam.rabbitmq.webapp.to.UserRegistrationTO;

public class UserLoginResponseTO {
	private ResponseStatus responseStatus;
	private UserRegistrationTO data;
	
	public ResponseStatus getResponseStatus() {
		return responseStatus;
	}
	public void setResponseStatus(ResponseStatus responseStatus) {
		this.responseStatus = responseStatus;
	}
	public UserRegistrationTO getData() {
		return data;
	}
	public void setData(UserRegistrationTO data) {
		this.data = data;
	}
}
