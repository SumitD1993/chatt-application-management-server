package com.sam.rabbitmq.webapp.sender;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import com.sam.rabbitmq.webapp.chatt.to.MessageInformation;
import com.sam.rabbitmq.webapp.response.ResponseStatus;

@Service
@Scope(value = WebApplicationContext.SCOPE_APPLICATION)
public class Sender {

	@Autowired
	ObjectMapper mapper;

	ConnectionFactory factory = null;
	Connection connection = null;
	Channel channel = null;

	{
		createRabbitmqChannel();
	}

	public String sendMessageToQueue(MessageInformation info) {
		String response ="";
		ResponseStatus responseStatus = null;
		try {
			System.out.println(mapper.writeValueAsString(info));
			channel.queueDeclare(info.getReceiver(), true, false, false, null);
			channel.basicPublish("",info.getReceiver(), MessageProperties.PERSISTENT_TEXT_PLAIN, mapper.writeValueAsString(info).getBytes());
			responseStatus = new ResponseStatus();
			responseStatus.setStatusCode(200);
			responseStatus.setMessage("success");
			response = mapper.writeValueAsString(responseStatus);
		} catch (Exception e) {
			responseStatus = new ResponseStatus();
			responseStatus.setStatusCode(200);
			responseStatus.setMessage("success");
			e.printStackTrace();
		}
		return response;
	}

	private void createRabbitmqChannel() {
		System.out.println("creating rabbitmq channel");
		try {
			this.factory = getConnectionbFactory();
			this.connection = getConnection();
			this.channel = connection.createChannel();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
	}

	private Connection getConnection() throws IOException, TimeoutException {
		return this.factory.newConnection();
	}

	private ConnectionFactory getConnectionbFactory() {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		factory.setPort(5672);
		return factory;
	}

}
