package com.sam.rabbitmq.webapp.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import com.sam.rabbitmq.webapp.dao.MessageDAO;
import com.sam.rabbitmq.webapp.entity.MessageContentType;
import com.sam.rabbitmq.webapp.entity.MessageStatus;
import com.sam.rabbitmq.webapp.entity.Messages;
import com.sam.rabbitmq.webapp.entity.UserMessageHistory;

@Repository
@PropertySource(value = { "classpath:queries.properties" })
public class MessageDAOImpl implements MessageDAO{
	
	@Autowired
	Environment env;
	
	public void saveUserMessageHistory(Session session, UserMessageHistory userMessageHistory) {
		session.save(userMessageHistory);
	}
	public void updateUserMessageHistory(Session session, UserMessageHistory userMessageHistory) {
		session.update(userMessageHistory);
	}
	public void saveMessages(Session session, Messages messages) {
		session.save(messages);
	}
	public void updateMessages(Session session, Messages messages) {
		session.update(messages);
	}
	
	public MessageContentType getMessageContentTypeByCode(Session session, String code) {
		Query query = session.createQuery(env.getProperty("FETCH_MESSAGE_CONTENT_TYPE_BY_CODE_HQL"));
		query.setParameter("code", code);
		MessageContentType messageContentType =  (MessageContentType) query.uniqueResult();
		return messageContentType;
	}
	
	public MessageStatus getMessageStatusByCode(Session session, String code) {
		Query query = session.createQuery(env.getProperty("FETCH_MESSAGE_STATUS_BY_CODE_HQL"));
		query.setParameter("code", code);
		MessageStatus messageStatus =  (MessageStatus) query.uniqueResult();
		return messageStatus;
	}
	public UserMessageHistory getUserMessageHistoryByMessageId(Session session, Long messageId) {
		return (UserMessageHistory) session.get(UserMessageHistory.class, messageId);
	}
	
}
