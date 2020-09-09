package com.sam.rabbitmq.webapp.bean.provider;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;


@Component
public class BeansProvider {
	
	@Bean
	public ObjectMapper getMApper() {
		return new ObjectMapper().setSerializationInclusion(Include.NON_NULL);
	}
}
