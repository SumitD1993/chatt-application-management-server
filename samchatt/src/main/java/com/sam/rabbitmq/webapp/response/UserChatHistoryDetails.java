package com.sam.rabbitmq.webapp.response;

import java.util.List;

public class UserChatHistoryDetails {
	private ResponseStatus responseStatus;
	private String name;
	private Long mobileNumber;
	private String lastMessageDate;
	private String lastMessageText;
	private String profileImageUrl;
	private String messageId;
	private Integer unReadMessagesCount;
	private Long userId;
	private List<MessageDetails> messages;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getLastMessageDate() {
		return lastMessageDate;
	}
	public void setLastMessageDate(String lastMessageDate) {
		this.lastMessageDate = lastMessageDate;
	}
	public String getLastMessageText() {
		return lastMessageText;
	}
	public void setLastMessageText(String lastMessageText) {
		this.lastMessageText = lastMessageText;
	}
	public String getProfileImageUrl() {
		return profileImageUrl;
	}
	public void setProfileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}
	public List<MessageDetails> getMessages() {
		return messages;
	}
	public void setMessages(List<MessageDetails> messages) {
		this.messages = messages;
	}
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public ResponseStatus getResponseStatus() {
		return responseStatus;
	}
	public void setResponseStatus(ResponseStatus responseStatus) {
		this.responseStatus = responseStatus;
	}
	public Integer getUnReadMessagesCount() {
		return unReadMessagesCount;
	}
	public void setUnReadMessagesCount(Integer unReadMessagesCount) {
		this.unReadMessagesCount = unReadMessagesCount;
	}
}
