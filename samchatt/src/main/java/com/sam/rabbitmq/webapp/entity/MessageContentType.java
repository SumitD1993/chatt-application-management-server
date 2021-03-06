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
 * MessageContentType generated by hbm2java
 */
@Entity
@Table(name = "message_content_type")
public class MessageContentType implements java.io.Serializable {

	private Integer id;
	private String code;
	private String name;
	private Date createdDate;
	private Date modifiedDate;
	private Set<UserMessageHistory> userMessageHistories = new HashSet<UserMessageHistory>(0);

	public MessageContentType() {
	}

	public MessageContentType(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public MessageContentType(String code, String name, Date createdDate, Date modifiedDate,
			Set<UserMessageHistory> userMessageHistories) {
		this.code = code;
		this.name = name;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.userMessageHistories = userMessageHistories;
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

	@Column(name = "code", nullable = false, length = 30)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "name", nullable = false, length = 30)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date", length = 19)
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "messageContentType")
	public Set<UserMessageHistory> getUserMessageHistories() {
		return this.userMessageHistories;
	}

	public void setUserMessageHistories(Set<UserMessageHistory> userMessageHistories) {
		this.userMessageHistories = userMessageHistories;
	}

}
