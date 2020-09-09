package com.sam.rabbitmq.webapp.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
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
import com.sam.rabbitmq.webapp.entity.MessageStatus;
import com.sam.rabbitmq.webapp.entity.User;
import com.sam.rabbitmq.webapp.entity.UserMessageHistory;
import com.sam.rabbitmq.webapp.exception.CustomException;
import com.sam.rabbitmq.webapp.response.MessageDetails;
import com.sam.rabbitmq.webapp.response.ResponseCodes;
import com.sam.rabbitmq.webapp.response.ResponseStatus;
import com.sam.rabbitmq.webapp.response.UserChatHistoryDetails;
import com.sam.rabbitmq.webapp.response.UserMainPageDetailsTO;
import com.sam.rabbitmq.webapp.response.UserRegitrationResponseTO;
import com.sam.rabbitmq.webapp.sender.Sender;
import com.sam.rabbitmq.webapp.service.UserService;
import com.sam.rabbitmq.webapp.to.UserLoginTO;
import com.sam.rabbitmq.webapp.to.UserRegistrationTO;

@Service
@Transactional(propagation=Propagation.REQUIRED, noRollbackFor= {Exception.class})
public class UserServiceImpl implements UserService {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private CommonUtils commonUtils;
	@Autowired
	private MessageDAO messageDAO;
	@Autowired
	Sender sendToQueue;
	
	private  EmailValidator emailvalidator = EmailValidator.getInstance();
	
	public UserRegitrationResponseTO saveUser(UserRegistrationTO userRegistrationTO) throws Exception{
		Session session = sessionFactory.getCurrentSession(); 
		UserRegitrationResponseTO regitrationResponseTO = new UserRegitrationResponseTO();
		User user = new User();
		if(userRegistrationTO == null) {
			throw new CustomException(ResponseCodes.INVALID_POST_DATA);
		}
		if(userRegistrationTO.getMobileNumber() == null && StringUtils.isNotBlank(userRegistrationTO.getEmailId())) {
			throw new CustomException(ResponseCodes.MOBILE_NUM_OR_EMAIL_ID_REQUIRED);
		}
		boolean validMobileNumber = false;
		if(userRegistrationTO.getMobileNumber() != null ) {
			if((String.valueOf(userRegistrationTO.getMobileNumber())).length() != 10) {
				throw new CustomException(ResponseCodes.MOBILE_NUM_SHOULD_BE_10_DIGIT_LONG);
			}
			if(userDAO.checkDupMobileNumber(session, userRegistrationTO.getMobileNumber())) {
				throw new CustomException(ResponseCodes.MOBILE_NUMBER_ALREADY_EXISTS);
			}
			user.setMobileNum(userRegistrationTO.getMobileNumber());
			validMobileNumber = true;
		}
		
		if(validMobileNumber) {
			if(StringUtils.isNotBlank(userRegistrationTO.getEmailId())){
				if(!emailvalidator.isValid(userRegistrationTO.getEmailId())) {
					throw new CustomException(ResponseCodes.INVALID_EMAIL_ID);
				}
				if(userDAO.checkDupEmailId(session, userRegistrationTO.getEmailId())) {
					throw new CustomException(ResponseCodes.EMAIL_ID_ALREADY_EXISTS);
				}
				user.setEmailId(userRegistrationTO.getEmailId());
			}
		}else {
			if(StringUtils.isNotBlank(userRegistrationTO.getEmailId())){
				if(!emailvalidator.isValid(userRegistrationTO.getEmailId())) {
					throw new CustomException(ResponseCodes.INVALID_EMAIL_ID);
				}
				if(userDAO.checkDupEmailId(session, userRegistrationTO.getEmailId())) {
					throw new CustomException(ResponseCodes.EMAIL_ID_ALREADY_EXISTS);
				}
				user.setEmailId(userRegistrationTO.getEmailId());
			}else {
				throw new CustomException(ResponseCodes.EMAIL_ID_REQUIRED);
			}
		}
		
		if(StringUtils.isNotBlank(userRegistrationTO.getFirstName())) {
			user.setFirstName(userRegistrationTO.getFirstName());
		}else {
			throw new CustomException(ResponseCodes.FIRST_NAME_REQUIRED);
		}
		if(StringUtils.isNotBlank(userRegistrationTO.getLastName())) {
			user.setLastName(userRegistrationTO.getLastName());
		}else {
			throw new CustomException(ResponseCodes.LAST_NAME_REQUIRED);
		}
		if(StringUtils.isNotBlank(userRegistrationTO.getMiddleName())) {
			user.setMiddleName(userRegistrationTO.getMiddleName());
		}
		
		if(StringUtils.isNotBlank(userRegistrationTO.getPassword())){
			user.setPassword(userRegistrationTO.getPassword());
		}else {
			throw new CustomException(ResponseCodes.PASSWORD_REQUIRED);
		}
		user.setCreatedDate(new Date());
		String sessionId = commonUtils.generateUniqueIdentifierNumber();
		user.setSessionId(sessionId);
		userDAO.saveUser(session, user);
		userRegistrationTO.setName(userRegistrationTO.getFirstName()+" "+userRegistrationTO.getLastName());
		userRegistrationTO.setFirstName(null);
		userRegistrationTO.setLastName(null);
		userRegistrationTO.setMiddleName(null);
		userRegistrationTO.setPassword(null);
		regitrationResponseTO.setData(userRegistrationTO);
		ResponseStatus responseStatus = commonUtils.generateResponseStatusObject(ResponseCodes.REGISTRATION_SUCCESS);
		regitrationResponseTO.setResponseStatus(responseStatus);
		return regitrationResponseTO;
	}

	public UserRegitrationResponseTO authenticateUser(UserLoginTO userLoginTO) throws Exception {
		Session session = sessionFactory.getCurrentSession(); 
		UserRegitrationResponseTO regitrationResponseTO = new UserRegitrationResponseTO();
		if(userLoginTO == null) {
			throw new CustomException(ResponseCodes.INVALID_POST_DATA);
		}
		long contactNumber = 0l;
		String emailId = null;
		String password = null;
		if(StringUtils.isNotBlank(userLoginTO.getMobileNumberOrEmailId())) {
			if(StringUtils.isNumeric(userLoginTO.getMobileNumberOrEmailId())) {
				if(userLoginTO.getMobileNumberOrEmailId().length() != 10) {
					throw new CustomException(ResponseCodes.MOBILE_NUM_SHOULD_BE_10_DIGIT_LONG);
				}
				contactNumber = Long.parseLong(userLoginTO.getMobileNumberOrEmailId());
				emailId =""; 
			}else {
				if(!emailvalidator.isValid(userLoginTO.getMobileNumberOrEmailId())) {
					throw new CustomException(ResponseCodes.INVALID_EMAIL_ID);
				}
				emailId = userLoginTO.getMobileNumberOrEmailId();
			}
		}else {
			throw new CustomException(ResponseCodes.MOBILE_NUM_OR_EMAIL_ID_REQUIRED);
		}
		
		if(StringUtils.isNotBlank(userLoginTO.getPassword())){
			password = userLoginTO.getPassword();
		}else {
			throw new CustomException(ResponseCodes.PASSWORD_REQUIRED);
		}
		
		UserRegistrationTO loginRespDetails = userDAO.userLogin(session, contactNumber, emailId, password);
		if(loginRespDetails != null) {
			if(StringUtils.isBlank(loginRespDetails.getSessionId())) {
				User user = userDAO.getUserById(session, loginRespDetails.getUserId().intValue());
				String sessionId = commonUtils.generateUniqueIdentifierNumber();
				user.setSessionId(sessionId);
				userDAO.updateUser(session, user);
				loginRespDetails.setSessionId(sessionId);
			}
			regitrationResponseTO.setData(loginRespDetails);
			ResponseStatus responseStatus = commonUtils.generateResponseStatusObject(ResponseCodes.LOGIN_SUCCESS);
			regitrationResponseTO.setResponseStatus(responseStatus);
		}else {
			throw new CustomException(ResponseCodes.INVALID_CREDENTIALS);
		}
		return regitrationResponseTO;
	}

	public UserMainPageDetailsTO fetchUserDetails(String sessionId) throws Exception{
		Session session = sessionFactory.getCurrentSession(); 
		UserMainPageDetailsTO userMainPageDetailsTO = new UserMainPageDetailsTO();
		if( StringUtils.isBlank(sessionId) ){
			throw new CustomException(ResponseCodes.SESSION_ID_REQUIRED);
		}
		User user = userDAO.getUserBySessionId(session, sessionId);
		if(user == null) {
			throw new CustomException(ResponseCodes.INVALID_SESSION_ID);
		}
		
		UserRegistrationTO userInfo = new UserRegistrationTO();
		userInfo.setName(user.getFirstName()+" "+user.getLastName());
		userInfo.setMobileNumber(user.getMobileNum());
		userInfo.setUserId(new Long(user.getId()));
		userMainPageDetailsTO.setUserInfo(userInfo);
		
		List<UserChatHistoryDetails> userChatHistoryDetails = userDAO.fetchUserChatHistoryDetailsByUserId(session, user.getId());
		userMainPageDetailsTO.setUserChatHistoryDetails(userChatHistoryDetails);
		
		ResponseStatus responseStatus = commonUtils.generateResponseStatusObject(ResponseCodes.SUCCESS);
		userMainPageDetailsTO.setResponseStatus(responseStatus);
		
		return userMainPageDetailsTO;
	}

	public UserChatHistoryDetails fetchUserChatHistory(String sessionId, Integer recieverUserID) throws Exception {
		Session session = sessionFactory.getCurrentSession(); 
		UserChatHistoryDetails userChatHistoryDetails = new UserChatHistoryDetails();
		if( StringUtils.isBlank(sessionId) ){
			throw new CustomException(ResponseCodes.SESSION_ID_REQUIRED);
		}
		User user = userDAO.getUserBySessionId(session, sessionId);
		if(user == null) {
			throw new CustomException(ResponseCodes.INVALID_SESSION_ID);
		}
		
		User recUser = userDAO.getUserById(session, recieverUserID);
		
		if(recUser == null) {
			throw new CustomException(ResponseCodes.INVALID_REC_USER_ID);
		}
		
		List<MessageDetails> senderMessages = userDAO.fetchMessagesByUserIds(session, user.getId(), recieverUserID, true);
		List<MessageDetails> recieverMessages = userDAO.fetchMessagesByUserIds(session, recieverUserID, user.getId(), false);
		
		List<MessageDetails> allMessages = new LinkedList<MessageDetails>();
		allMessages.addAll(senderMessages);
		allMessages.addAll(recieverMessages);
		Collections.sort(allMessages);
		
		userChatHistoryDetails.setMessages(allMessages);
				
		ResponseStatus responseStatus = commonUtils.generateResponseStatusObject(ResponseCodes.SUCCESS);
		userChatHistoryDetails.setResponseStatus(responseStatus);
		
		return userChatHistoryDetails;
	}

	public UserChatHistoryDetails updateMessageStatusByMessageId(String sessionId, Long messageId, String statusCode) throws Exception {
		Session session = sessionFactory.getCurrentSession(); 
		UserChatHistoryDetails userChatHistoryDetails = new UserChatHistoryDetails();
		if( StringUtils.isBlank(sessionId) ){
			throw new CustomException(ResponseCodes.SESSION_ID_REQUIRED);
		}
		User user = userDAO.getUserBySessionId(session, sessionId);
		if(user == null) {
			throw new CustomException(ResponseCodes.INVALID_SESSION_ID);
		}
		
		MessageStatus messageStatus = messageDAO.getMessageStatusByCode(session, statusCode);
		if(messageStatus == null) {
			throw new CustomException(ResponseCodes.INVALID_STATUS);
		}
		
		UserMessageHistory userMessageHistory =  messageDAO.getUserMessageHistoryByMessageId(session, messageId);
		if(userMessageHistory != null) {
			userMessageHistory.setMessageStatus(messageStatus);
			userMessageHistory.setModifiedDate(new Date());
			messageDAO.updateUserMessageHistory(session, userMessageHistory );
		}
		
		ResponseStatus responseStatus = commonUtils.generateResponseStatusObject(ResponseCodes.SUCCESS);
		userChatHistoryDetails.setResponseStatus(responseStatus);
		MessageInformation information =  new MessageInformation();
		information.setReceiver(userMessageHistory.getUserBySenderUserId().getMobileNum()+"");
		information.setSender(userMessageHistory.getUserByReceiverUserId().getMobileNum()+"");
		information.setMessageID(messageId+"");
		information.setReadStatus(true);
		information.setAction("READ");
		information.setRecievedDate(userMessageHistory.getModifiedDate());
		sendToQueue.sendMessageToQueue(information);
		return userChatHistoryDetails;
	
	}
}
