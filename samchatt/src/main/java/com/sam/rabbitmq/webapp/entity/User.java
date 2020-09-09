package com.sam.rabbitmq.webapp.entity;
// Generated 14 Apr, 2020 9:09:44 PM by Hibernate Tools 4.3.5.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * User generated by hbm2java
 */
@Entity
@Table(name = "user")
public class User implements java.io.Serializable {

	private Integer id;
	private String firstName;
	private String middleName;
	private String lastName;
	private long mobileNum;
	private String emailId;
	private String password;
	private String sessionId;
	private Date createdDate;
	private Date modifiedDate;
	private Set<UserMessageHistory> userMessageHistoriesForSenderUserId = new HashSet<UserMessageHistory>(0);
	private Set<UserMessageHistory> userMessageHistoriesForReceiverUserId = new HashSet<UserMessageHistory>(0);

	public User() {
	}

	public User(String firstName, long mobileNum, String emailId, String password, Date createdDate) {
		this.firstName = firstName;
		this.mobileNum = mobileNum;
		this.emailId = emailId;
		this.password = password;
		this.createdDate = createdDate;
	}

	public User(String firstName, String middleName, String lastName, long mobileNum, String emailId, String password,
			String sessionId, Date createdDate, Date modifiedDate,
			Set<UserMessageHistory> userMessageHistoriesForSenderUserId,
			Set<UserMessageHistory> userMessageHistoriesForReceiverUserId) {
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.mobileNum = mobileNum;
		this.emailId = emailId;
		this.password = password;
		this.sessionId = sessionId;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.userMessageHistoriesForSenderUserId = userMessageHistoriesForSenderUserId;
		this.userMessageHistoriesForReceiverUserId = userMessageHistoriesForReceiverUserId;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "first_name", nullable = false, length = 256)
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "middle_name", length = 256)
	public String getMiddleName() {
		return this.middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	@Column(name = "last_name", length = 256)
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "mobile_num", nullable = false)
	public long getMobileNum() {
		return this.mobileNum;
	}

	public void setMobileNum(long mobileNum) {
		this.mobileNum = mobileNum;
	}

	@Column(name = "email_id", nullable = false, length = 256)
	public String getEmailId() {
		return this.emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@Column(name = "password", nullable = false, length = 256)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "session_id", length = 256)
	public String getSessionId() {
		return this.sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date", nullable = false, length = 19)
	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modified_date", length = 19)
	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userBySenderUserId")
	public Set<UserMessageHistory> getUserMessageHistoriesForSenderUserId() {
		return this.userMessageHistoriesForSenderUserId;
	}

	public void setUserMessageHistoriesForSenderUserId(Set<UserMessageHistory> userMessageHistoriesForSenderUserId) {
		this.userMessageHistoriesForSenderUserId = userMessageHistoriesForSenderUserId;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userByReceiverUserId")
	public Set<UserMessageHistory> getUserMessageHistoriesForReceiverUserId() {
		return this.userMessageHistoriesForReceiverUserId;
	}

	public void setUserMessageHistoriesForReceiverUserId(
			Set<UserMessageHistory> userMessageHistoriesForReceiverUserId) {
		this.userMessageHistoriesForReceiverUserId = userMessageHistoriesForReceiverUserId;
	}

}
