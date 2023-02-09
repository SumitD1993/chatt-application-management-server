package com.sam.rabbitmq.webapp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sam.rabbitmq.webapp.common.CommonUtils;
import com.sam.rabbitmq.webapp.exception.CustomException;
import com.sam.rabbitmq.webapp.response.ResponseCodes;
import com.sam.rabbitmq.webapp.response.ResponseStatus;
import com.sam.rabbitmq.webapp.response.UserRegitrationResponseTO;
import com.sam.rabbitmq.webapp.service.UserService;
import com.sam.rabbitmq.webapp.to.UserRegistrationTO;

@RestController
public class UserRegistrationController {
	
	@Autowired
	UserService userService;
	@Autowired
	private CommonUtils commonUtils;
	
	@RequestMapping(value="registerUser", method=RequestMethod.POST)
	@ResponseBody 
	public UserRegitrationResponseTO getUserDetails(@RequestBody UserRegistrationTO userRegistrationTO) {
		UserRegitrationResponseTO userRegitrationResponseTO = new UserRegitrationResponseTO();
		try {
			userRegitrationResponseTO = userService.saveUser(userRegistrationTO);
		}catch(CustomException ce) {
			ResponseStatus responseStatus = commonUtils.generateResponseStatusObject(ce.getMessage());
			userRegitrationResponseTO.setResponseStatus(responseStatus);
		}catch (Exception e) {
			ResponseStatus responseStatus = commonUtils.generateResponseStatusObject(ResponseCodes.SYSTEM_ERROR);
			userRegitrationResponseTO.setResponseStatus(responseStatus);
		}
		return userRegitrationResponseTO;
	}
	
}
