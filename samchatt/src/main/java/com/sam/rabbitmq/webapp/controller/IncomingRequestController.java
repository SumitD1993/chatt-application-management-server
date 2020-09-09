package com.sam.rabbitmq.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sam.rabbitmq.webapp.chatt.to.MessageInformation;
import com.sam.rabbitmq.webapp.common.CommonUtils;
import com.sam.rabbitmq.webapp.exception.CustomException;
import com.sam.rabbitmq.webapp.response.ResponseCodes;
import com.sam.rabbitmq.webapp.response.ResponseStatus;
import com.sam.rabbitmq.webapp.response.SaveMessageReponseTO;
import com.sam.rabbitmq.webapp.sender.Sender;
import com.sam.rabbitmq.webapp.service.MessageService;
import com.sam.rabbitmq.webapp.service.UserService;

@RestController
public class IncomingRequestController {
	@Autowired
	Sender sendToQueue;
	@Autowired
	UserService userService;
	@Autowired
	MessageService messageService;
	@Autowired
	private CommonUtils commonUtils;
	
	public IncomingRequestController() {
		System.out.println("IncomingRequestController : ");
	}
	
	@RequestMapping(value="sendToQueue")
	public SaveMessageReponseTO sendMessageToReceipentQueue(@RequestBody MessageInformation information) {
		SaveMessageReponseTO saveMessageReponseTO = new SaveMessageReponseTO();
		try {
			saveMessageReponseTO = messageService.saveMessageDetails(information);
			sendToQueue.sendMessageToQueue(information);
		}catch (CustomException ce) {
			ResponseStatus responseStatus = commonUtils.generateResponseStatusObject(ce.getMessage());
			saveMessageReponseTO.setResponseStatus(responseStatus);
		}catch (Exception e) {
			e.printStackTrace();
			ResponseStatus responseStatus = commonUtils.generateResponseStatusObject(ResponseCodes.SYSTEM_ERROR);
			saveMessageReponseTO.setResponseStatus(responseStatus);
		}
		return saveMessageReponseTO;
	}
	
	
	
	@RequestMapping("getEngineStatus")
	@ResponseBody
	public String getStatus() {
		return "engine is running";
	}
}
