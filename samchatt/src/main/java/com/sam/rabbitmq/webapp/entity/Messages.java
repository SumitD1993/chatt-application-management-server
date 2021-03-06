package com.sam.rabbitmq.webapp.entity;
// Generated 14 Apr, 2020 9:09:44 PM by Hibernate Tools 4.3.5.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Messages generated by hbm2java
 */
@Entity
@Table(name = "messages")
public class Messages implements java.io.Serializable {

	private long id;
	private UserMessageHistory userMessageHistory;
	private String textContent;
	private String multimediaUrl;
	private Date createdDate;

	public Messages() {
	}

	public Messages(UserMessageHistory userMessageHistory) {
		this.userMessageHistory = userMessageHistory;
	}

	public Messages(UserMessageHistory userMessageHistory, String textContent, String multimediaUrl, Date createdDate) {
		this.userMessageHistory = userMessageHistory;
		this.textContent = textContent;
		this.multimediaUrl = multimediaUrl;
		this.createdDate = createdDate;
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
	@JoinColumn(name = "user_message_history_id", nullable = false)
	public UserMessageHistory getUserMessageHistory() {
		return this.userMessageHistory;
	}

	public void setUserMessageHistory(UserMessageHistory userMessageHistory) {
		this.userMessageHistory = userMessageHistory;
	}

	@Column(name = "text_content", length = 65535)
	public String getTextContent() {
		return this.textContent;
	}

	public void setTextContent(String textContent) {
		this.textContent = textContent;
	}

	@Column(name = "multimedia_url", length = 256)
	public String getMultimediaUrl() {
		return this.multimediaUrl;
	}

	public void setMultimediaUrl(String multimediaUrl) {
		this.multimediaUrl = multimediaUrl;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date", length = 19)
	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

}
