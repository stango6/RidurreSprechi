package com.generation.wasteless.context;

import com.generation.wasteless.entities.Utente;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.generation.wasteless.service.ServiceProdotto;
import com.generation.wasteless.service.ServiceUtente;
import org.springframework.context.annotation.Scope;


@Configuration
public class ContextService {
	
	@Bean
	@Scope("prototype")
	public ServiceProdotto serviceProdotto() {
		return new ServiceProdotto();
	}
	
	
	@Bean
	@Scope("prototype")
	public ServiceUtente serviceUtente() {
		return new ServiceUtente();
	}



}