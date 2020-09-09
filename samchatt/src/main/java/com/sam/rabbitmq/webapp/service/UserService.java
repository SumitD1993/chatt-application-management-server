package com.sam.rabbitmq.webapp.service;

import com.sam.rabbitmq.webapp.response.UserChatHistoryDetails;
import com.sam.rabbitmq.webapp.response.UserMainPageDetailsTO;
import com.sam.rabbitmq.webapp.response.UserRegitrationResponseTO;
import com.sam.rabbitmq.webapp.to.UserLoginTO;
import com.sam.rabbitmq.webapp.to.UserRegistrationTO;

public interface UserService {
	UserRegitrationResponseTO saveUser(UserRegistrationTO userRegistrationTO) throws Exception;
	UserRegitrationResponseTO authenticateUser(UserLoginTO userLoginTO) throws Exception;
	UserMainPageDetailsTO fetchUserDetails(String sessionId) throws Exception;
	UserChatHistoryDetails fetchUserChatHistory(String sessionId, Integer recieverUserID) throws Exception;
	UserChatHistoryDetails updateMessageStatusByMessageId(String sessionId, Long messageId, String statusCode) throws Exception;
}

