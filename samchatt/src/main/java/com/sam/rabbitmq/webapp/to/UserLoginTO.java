package com.sam.rabbitmq.webapp.to;

public class UserLoginTO {
	private String mobileNumberOrEmailId;
	private String password;
	
	public String getMobileNumberOrEmailId() {
		return mobileNumberOrEmailId;
	}
	public void setMobileNumberOrEmailId(String mobileNumberOrEmailId) {
		this.mobileNumberOrEmailId = mobileNumberOrEmailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
