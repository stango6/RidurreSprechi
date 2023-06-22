package com.generation.wasteless.context;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.generation.wasteless.service.ServiceProdotto;
import com.generation.wasteless.service.ServiceUtente;


@Configuration
public class ContextService {
	
	@Bean
	public ServiceProdotto serviceProdotto() {
		return new ServiceProdotto();
	}
	
	
	@Bean
	public ServiceUtente serviceUtente() {
		return new ServiceUtente();
	}
	
	
}