package com.sam.rabbitmq.webapp.chatt.to;
public class ContactDTO {
	private Integer userId;
	private String contactName;
	private Long contactNumber;
	
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public Long getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(Long contactNumber) {
		this.contactNumber = contactNumber;
	}
	
	@Override
	public String toString() {
		return "ContactDTO [userId=" + userId + ", contactName=" + contactName + ", contactNumber=" + contactNumber
				+ "]";
	}
}