package com.sam.rabbitmq.webapp.response;

public class MessageDetails implements Comparable<MessageDetails> {  
	private Integer messageId;
	private String messageText;
	private String multiMediaUrl;
	private String messageRecivedDate;
	private String messageSentDate;
	private Boolean senderMessages;
	private Boolean readStatus;
	private String readStatusCode;
	
	public Integer getMessageId() {
		return messageId;
	}
	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}
	public String getMessageText() {
		return messageText;
	}
	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}
	public String getMultiMediaUrl() {
		return multiMediaUrl;
	}
	public void setMultiMediaUrl(String multiMediaUrl) {
		this.multiMediaUrl = multiMediaUrl;
	}
	public String getMessageRecivedDate() {
		return messageRecivedDate;
	}
	public void setMessageRecivedDate(String messageRecivedDate) {
		this.messageRecivedDate = messageRecivedDate;
	}
	public String getMessageSentDate() {
		return messageSentDate;
	}
	public void setMessageSentDate(String messageSentDate) {
		this.messageSentDate = messageSentDate;
	}
	
	public int compareTo(MessageDetails o) {
		return this.messageId.compareTo(o.getMessageId());
	}
	public Boolean getSenderMessages() {
		return senderMessages;
	}
	public void setSenderMessages(Boolean senderMessages) {
		this.senderMessages = senderMessages;
	}
	public Boolean getReadStatus() {
		return readStatus;
	}
	public void setReadStatus(Boolean readStatus) {
		this.readStatus = readStatus;
	}
	public String getReadStatusCode() {
		return readStatusCode;
	}
	public void setReadStatusCode(String readStatusCode) {
		this.readStatusCode = readStatusCode;
	}
}
