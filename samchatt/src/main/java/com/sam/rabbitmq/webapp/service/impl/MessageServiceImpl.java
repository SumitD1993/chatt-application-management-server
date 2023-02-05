package com.sam.rabbitmq.webapp.service.impl;


import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sam.rabbitmq.webapp.chatt.to.MessageInformation;
import com.sam.rabbitmq.webapp.common.CommonUtils;
import com.sam.rabbitmq.webapp.dao.MessageDAO;
import com.sam.rabbitmq.webapp.dao.UserDAO;
import com.sam.rabbitmq.webapp.entity.MessageContentType;
import com.sam.rabbitmq.webapp.entity.Messages;
import com.sam.rabbitmq.webapp.entity.User;
import com.sam.rabbitmq.webapp.entity.UserMessageHistory;
import com.sam.rabbitmq.webapp.response.ResponseCodes;
import com.sam.rabbitmq.webapp.response.ResponseStatus;
import com.sam.rabbitmq.webapp.response.SaveMessageReponseTO;
import com.sam.rabbitmq.webapp.service.MessageService;

@Service
@Transactional(propagation=Propagation.REQUIRED, noRollbackFor= {Exception.class})
public class MessageServiceImpl implements MessageService{
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private MessageDAO messageDAO;
	@Autowired
	private CommonUtils commonUtils;
	
	
	public SaveMessageReponseTO saveMessageDetails(MessageInformation messageDetails) throws Exception {
		SaveMessageReponseTO saveMessageResponseTO = new SaveMessageReponseTO();
		Session session = sessionFactory.getCurrentSession();
		
		long contactNumber = 0l;
		String emailId = null;
		if(StringUtils.isNotBlank(messageDetails.getSender())) {
			if(StringUtils.isNumeric(messageDetails.getSender())) {
				contactNumber = Long.parseLong(messageDetails.getSender());
				emailId =""; 
			}else {
				emailId = messageDetails.getSender();
			}
		}
		
		User sender = userDAO.getUserByMobileNumberOrEmail(session, contactNumber, emailId);
		if(StringUtils.isNotBlank(messageDetails.getReceiver())) {
			if(StringUtils.isNumeric(messageDetails.getReceiver())) {
				contactNumber = Long.parseLong(messageDetails.getReceiver());
				emailId =""; 
			}else {
				emailId = messageDetails.getReceiver();
			}
		}
		User reciever = userDAO.getUserByMobileNumberOrEmail(session, contactNumber, emailId);
		MessageContentType messageContentType =  messageDAO.getMessageContentTypeByCode(session, CommonUtils.ContentType.TEXT);
		com.sam.rabbitmq.webapp.entity.MessageStatus messageStatus = messageDAO.getMessageStatusByCode(session, CommonUtils.MessageStatus.SENT);
		UserMessageHistory userMessageHistory = new UserMessageHistory();
		userMessageHistory.setUserBySenderUserId(sender);
		userMessageHistory.setUserByReceiverUserId(reciever);
		userMessageHistory.setMessageContentType(messageContentType);
		userMessageHistory.setMessageStatus(messageStatus);
		userMessageHistory.setCreatedDate(new Date());
		messageDAO.saveUserMessageHistory(session, userMessageHistory);
		
		Messages message = new Messages();
		message.setCreatedDate(new Date());
		message.setTextContent(messageDetails.getMessage());
		message.setUserMessageHistory(userMessageHistory);
		messageDAO.saveMessages(session, message);
		saveMessageResponseTO.setMessageId(userMessageHistory.getId());
		ResponseStatus responseStatus = commonUtils.generateResponseStatusObject(ResponseCodes.SUCCESS);
		saveMessageResponseTO.setResponseStatus(responseStatus);
		messageDetails.setMessageID(userMessageHistory.getId()+"");
		messageDetails.setAction("NEW_MESSAGE");
		messageDetails.setSenderId(sender.getId());
		messageDetails.setReceiverId(reciever.getId());
		messageDetails.setSenderName(sender.getFirstName()+ (sender.getLastName() == null ? "":(" "+sender.getLastName())));
		return saveMessageResponseTO;
	}

}
