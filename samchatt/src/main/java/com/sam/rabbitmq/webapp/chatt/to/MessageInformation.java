package com.sam.rabbitmq.webapp.chatt.to;

import java.util.Date;

public class MessageInformation {
	private String sender;
	private String receiver;
	private String message;
	private Date recievedDate;
	private String messageID;
	private boolean acknowledgeMessage;
	private Boolean readStatus;
	private String action;

	public MessageInformation() {
		// TODO Auto-generated constructor stub
	}
	
	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getRecievedDate() {
		return recievedDate;
	}

	public void setRecievedDate(Date recievedDate) {
		this.recievedDate = recievedDate;
	}

	public String getMessageID() {
		return messageID;
	}

	public void setMessageID(String messageID) {
		this.messageID = messageID;
	}

	public boolean isAcknowledgeMessage() {
		return acknowledgeMessage;
	}

	public void setAcknowledgeMessage(boolean acknowledgeMessage) {
		this.acknowledgeMessage = acknowledgeMessage;
	}

	@Override
	public String toString() {
		return "MessageInformation [sender=" + sender + ", receiver=" + receiver + ", message=" + message
				+ ", recievedDate=" + recievedDate + ", messageID=" + messageID + "]";
	}

	public Boolean getReadStatus() {
		return readStatus;
	}

	public void setReadStatus(Boolean readStatus) {
		this.readStatus = readStatus;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

}
