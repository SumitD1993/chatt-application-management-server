package com.sam.rabbitmq.webapp.entity;
// Generated 14 Apr, 2020 9:09:44 PM by Hibernate Tools 4.3.5.Final

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * UserMessageHistory generated by hbm2java
 */
@Entity
@Table(name = "user_message_history")
public class UserMessageHistory implements java.io.Serializable {

	private long id;
	private MessageContentType messageContentType;
	private MessageStatus messageStatus;
	private User userBySenderUserId;
	private User userByReceiverUserId;
	private Date createdDate;
	private Date modifiedDate;
	private Set<Messages> messageses = new HashSet<Messages>(0);

	public UserMessageHistory() {
	}

	public UserMessageHistory(MessageContentType messageContentType, User userBySenderUserId,
			User userByReceiverUserId) {
		this.messageContentType = messageContentType;
		this.userBySenderUserId = userBySenderUserId;
		this.userByReceiverUserId = userByReceiverUserId;
	}

	public UserMessageHistory(MessageContentType messageContentType, MessageStatus messageStatus,
			User userBySenderUserId, User userByReceiverUserId, Date createdDate, Set<Messages> messageses) {
		this.messageContentType = messageContentType;
		this.messageStatus = messageStatus;
		this.userBySenderUserId = userBySenderUserId;
		this.userByReceiverUserId = userByReceiverUserId;
		this.createdDate = createdDate;
		this.messageses = messageses;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "message_type", nullable = false)
	public MessageContentType getMessageContentType() {
		return this.messageContentType;
	}

	public void setMessageContentType(MessageContentType messageContentType) {
		this.messageContentType = messageContentType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status")
	public MessageStatus getMessageStatus() {
		return this.messageStatus;
	}

	public void setMessageStatus(MessageStatus messageStatus) {
		this.messageStatus = messageStatus;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sender_user_id", nullable = false)
	public User getUserBySenderUserId() {
		return this.userBySenderUserId;
	}

	public void setUserBySenderUserId(User userBySenderUserId) {
		this.userBySenderUserId = userBySenderUserId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "receiver_user_id", nullable = false)
	public User getUserByReceiverUserId() {
		return this.userByReceiverUserId;
	}

	public void setUserByReceiverUserId(User userByReceiverUserId) {
		this.userByReceiverUserId = userByReceiverUserId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date", length = 19)
	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userMessageHistory")
	public Set<Messages> getMessageses() {
		return this.messageses;
	}

	public void setMessageses(Set<Messages> messageses) {
		this.messageses = messageses;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modified_date", length = 19)
	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

}
