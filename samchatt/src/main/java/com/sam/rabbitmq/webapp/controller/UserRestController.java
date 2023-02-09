package com.sam.rabbitmq.webapp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sam.rabbitmq.webapp.common.CommonUtils;
import com.sam.rabbitmq.webapp.exception.CustomException;
import com.sam.rabbitmq.webapp.response.ContactsDetailsResponseTO;
import com.sam.rabbitmq.webapp.response.ResponseCodes;
import com.sam.rabbitmq.webapp.response.ResponseStatus;
import com.sam.rabbitmq.webapp.response.UserChatHistoryDetails;
import com.sam.rabbitmq.webapp.response.UserMainPageDetailsTO;
import com.sam.rabbitmq.webapp.response.UserRegitrationResponseTO;
import com.sam.rabbitmq.webapp.service.UserService;
import com.sam.rabbitmq.webapp.to.UserLoginTO;

@RestController
@RequestMapping("{sessionId}")
public class UserRestController {
	@Autowired
	UserService userService;
	@Autowired
	private CommonUtils commonUtils;
	
	@RequestMapping(value="userDetails", method=RequestMethod.GET)
	@ResponseBody 
	public  UserMainPageDetailsTO getUserDetails(@PathVariable("sessionId") String sessionId) {
		UserMainPageDetailsTO userMainPageDetailsTO = new UserMainPageDetailsTO();
		try {
			userMainPageDetailsTO = userService.fetchUserDetails(sessionId);
		}catch (CustomException ce) {
			ResponseStatus responseStatus = commonUtils.generateResponseStatusObject(ce.getMessage());
			userMainPageDetailsTO.setResponseStatus(responseStatus);
		}catch (Exception e) {
			e.printStackTrace();
			ResponseStatus responseStatus = commonUtils.generateResponseStatusObject(ResponseCodes.SYSTEM_ERROR);
			userMainPageDetailsTO.setResponseStatus(responseStatus);
		}
		return userMainPageDetailsTO;
	}
	
	@RequestMapping(value="userDetailsByRecId", method=RequestMethod.GET)
	@ResponseBody 
	public  UserMainPageDetailsTO getUserDetailsById(@PathVariable("sessionId") String sessionId, @RequestParam(required=true, value="recUserId") Integer recieverUserID) {
		UserMainPageDetailsTO userMainPageDetailsTO = new UserMainPageDetailsTO();
		try {
			userMainPageDetailsTO = userService.fetchUserDetailsByRecvId(sessionId, recieverUserID);
		}catch (CustomException ce) {
			ResponseStatus responseStatus = commonUtils.generateResponseStatusObject(ce.getMessage());
			userMainPageDetailsTO.setResponseStatus(responseStatus);
		}catch (Exception e) {
			e.printStackTrace();
			ResponseStatus responseStatus = commonUtils.generateResponseStatusObject(ResponseCodes.SYSTEM_ERROR);
			userMainPageDetailsTO.setResponseStatus(responseStatus);
		}
		return userMainPageDetailsTO;
	}
	
	@RequestMapping(value="login")
	@ResponseBody 
	public  UserRegitrationResponseTO authenticateUser(@PathVariable("sessionId") String sessionId, @RequestBody UserLoginTO userLoginTO ) {
		UserRegitrationResponseTO userRegitrationResponseTO = new UserRegitrationResponseTO();
		try {
			userRegitrationResponseTO = userService.authenticateUser(userLoginTO);
		}catch (CustomException ce) {
			ResponseStatus responseStatus = commonUtils.generateResponseStatusObject(ce.getMessage());
			userRegitrationResponseTO.setResponseStatus(responseStatus);
		}catch (Exception e) {
			e.printStackTrace();
			ResponseStatus responseStatus = commonUtils.generateResponseStatusObject(ResponseCodes.SYSTEM_ERROR);
			userRegitrationResponseTO.setResponseStatus(responseStatus);
		}
		return userRegitrationResponseTO;
	}
	
	@RequestMapping(value="userChatHistory", method=RequestMethod.GET)
	@ResponseBody 
	public  UserChatHistoryDetails getUserChatHistoryByUserIds(@PathVariable("sessionId") String sessionId, @RequestParam(required=true, value="recUserId") Integer recieverUserID) {
		UserChatHistoryDetails userChatHistoryDetails = new UserChatHistoryDetails();
		try {
			userChatHistoryDetails = userService.fetchUserChatHistory(sessionId, recieverUserID);
		}catch (CustomException ce) {
			ResponseStatus responseStatus = commonUtils.generateResponseStatusObject(ce.getMessage());
			userChatHistoryDetails.setResponseStatus(responseStatus);
		}catch (Exception e) {
			e.printStackTrace();
			ResponseStatus responseStatus = commonUtils.generateResponseStatusObject(ResponseCodes.SYSTEM_ERROR);
			userChatHistoryDetails.setResponseStatus(responseStatus);
		}
		return userChatHistoryDetails;
	}
	
	@RequestMapping(value="updateMessageStatus", method=RequestMethod.GET)
	@ResponseBody 
	public  UserChatHistoryDetails updateMessageStatus(@PathVariable("sessionId") String sessionId, @RequestParam(required=true, value="messageId") Long messageId, @RequestParam(required=true, value="status") String statusCode) {
		UserChatHistoryDetails userChatHistoryDetails = new UserChatHistoryDetails();
		try {
			userChatHistoryDetails = userService.updateMessageStatusByMessageId(sessionId, messageId, statusCode);
		}catch (CustomException ce) {
			ResponseStatus responseStatus = commonUtils.generateResponseStatusObject(ce.getMessage());
			userChatHistoryDetails.setResponseStatus(responseStatus);
		}catch (Exception e) {
			e.printStackTrace();
			ResponseStatus responseStatus = commonUtils.generateResponseStatusObject(ResponseCodes.SYSTEM_ERROR);
			userChatHistoryDetails.setResponseStatus(responseStatus);
		}
		return userChatHistoryDetails;
	}
	
	@RequestMapping(value="getContacts", method=RequestMethod.GET)
	@ResponseBody 
	public  ContactsDetailsResponseTO getContacts(@PathVariable("sessionId") String sessionId) {
		ContactsDetailsResponseTO contactsResponse = new ContactsDetailsResponseTO();
		try {
			contactsResponse = userService.fetchContacts(sessionId);
		}catch (CustomException ce) {
			ResponseStatus responseStatus = commonUtils.generateResponseStatusObject(ce.getMessage());
			contactsResponse.setResponseStatus(responseStatus);
		}catch (Exception e) {
			e.printStackTrace();
			ResponseStatus responseStatus = commonUtils.generateResponseStatusObject(ResponseCodes.SYSTEM_ERROR);
			contactsResponse.setResponseStatus(responseStatus);
		}
		return contactsResponse;
	}
	
}
