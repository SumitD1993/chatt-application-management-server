package com.sam.rabbitmq.webapp.dao;

import org.hibernate.Session;

import com.sam.rabbitmq.webapp.entity.MessageContentType;
import com.sam.rabbitmq.webapp.entity.MessageStatus;
import com.sam.rabbitmq.webapp.entity.Messages;
import com.sam.rabbitmq.webapp.entity.UserMessageHistory;

public interface MessageDAO {
	public void saveUserMessageHistory(Session session, UserMessageHistory userMessageHistory) ;
	public void updateUserMessageHistory(Session session, UserMessageHistory userMessageHistory) ;
	public void saveMessages(Session session, Messages messages) ;
	public void updateMessages(Session session, Messages messages) ;
	public MessageContentType getMessageContentTypeByCode(Session session, String code) ;
	public MessageStatus getMessageStatusByCode(Session session, String code) ;
	public UserMessageHistory getUserMessageHistoryByMessageId(Session session, Long messageId);
}
