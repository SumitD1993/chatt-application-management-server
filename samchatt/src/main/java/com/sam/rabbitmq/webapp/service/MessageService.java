package com.sam.rabbitmq.webapp.service;

import com.sam.rabbitmq.webapp.chatt.to.MessageInformation;
import com.sam.rabbitmq.webapp.response.SaveMessageReponseTO;

public interface MessageService {
	SaveMessageReponseTO saveMessageDetails(MessageInformation messageDetails) throws Exception;
}
