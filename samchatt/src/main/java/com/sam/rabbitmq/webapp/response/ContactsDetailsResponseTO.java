package com.sam.rabbitmq.webapp.response;

import java.util.List;

import com.sam.rabbitmq.webapp.chatt.to.ContactDTO;

public class ContactsDetailsResponseTO {
	private ResponseStatus responseStatus;
	private List<ContactDTO> data;
	
	public ResponseStatus getResponseStatus() {
		return responseStatus;
	}
	public void setResponseStatus(ResponseStatus responseStatus) {
		this.responseStatus = responseStatus;
	}
	public List<ContactDTO> getData() {
		return data;
	}
	public void setData(List<ContactDTO> data) {
		this.data = data;
	}
}
