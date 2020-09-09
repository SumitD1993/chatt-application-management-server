package com.sam.rabbitmq.webapp.response;

public class SaveMessageReponseTO {
	private ResponseStatus responseStatus;
	private Long messageId;
	
	public ResponseStatus getResponseStatus() {
		return responseStatus;
	}
	public void setResponseStatus(ResponseStatus responseStatus) {
		this.responseStatus = responseStatus;
	}
	public Long getMessageId() {
		return messageId;
	}
	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}
}
