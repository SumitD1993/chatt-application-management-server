package com.sam.rabbitmq.webapp.response;

import java.util.List;

import com.sam.rabbitmq.webapp.to.UserRegistrationTO;

public class UserMainPageDetailsTO{
	private ResponseStatus responseStatus;
	private UserRegistrationTO userInfo;
	private List<UserChatHistoryDetails> userChatHistoryDetails;
	
	public UserRegistrationTO getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserRegistrationTO userInfo) {
		this.userInfo = userInfo;
	}
	public List<UserChatHistoryDetails> getUserChatHistoryDetails() {
		return userChatHistoryDetails;
	}
	public void setUserChatHistoryDetails(List<UserChatHistoryDetails> userChatHistoryDetails) {
		this.userChatHistoryDetails = userChatHistoryDetails;
	}
	public ResponseStatus getResponseStatus() {
		return responseStatus;
	}
	public void setResponseStatus(ResponseStatus responseStatus) {
		this.responseStatus = responseStatus;
	}
}