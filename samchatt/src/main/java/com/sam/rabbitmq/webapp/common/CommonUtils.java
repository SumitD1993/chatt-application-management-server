package com.sam.rabbitmq.webapp.common;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.sam.rabbitmq.webapp.response.ResponseStatus;

@Component
@Scope("singleton")
@PropertySource(value = { "classpath:restMessages.properties" })
public class CommonUtils {
	
	@Autowired
	private Environment env;
	
	public String generateUniqueIdentifierNumber(){
		 UUID uniqueId = UUID.randomUUID();
		 return uniqueId.toString();
	}

	public ResponseStatus generateResponseStatusObject(Integer statusCode) {
		ResponseStatus responseStatus = new ResponseStatus();
		responseStatus.setStatusCode(statusCode);
		responseStatus.setMessage(env.getProperty(statusCode+""));
		return responseStatus;
	}
	public ResponseStatus generateResponseStatusObject(String statusCode) {
		return generateResponseStatusObject(Integer.parseInt(statusCode));
	}
	
	public static class ContentType{
		public static String TEXT="TEXT";
		public static String MULTIMEDIA="MULTIMEDIA";
	}
	public static class MessageStatus{
		public static String PENDING="PENDING";
		public static String READ="READ";
		public static String DELETED="DELETED";
		public static String RECIEVED="RECIEVED";
		public static String SENT="SENT";
	}
}
