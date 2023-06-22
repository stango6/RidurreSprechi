package com.generation.wasteless.context;

import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

import com.generation.wasteless.entities.Utente;
import com.generation.wasteless.entities.Prodotto;

@Configuration
public class ModelContext {


	@Bean
	@Scope("prototype")
	@Primary
	public Utente utente(Map<String, String> params) {
		Utente utente = new Utente();
		utente.fromMap(params);
		return utente;
	}

	@Bean
	@Scope("prototype")
	public Utente utenteVuoto(){
		Utente u = new Utente();
		return u;
	}

	@Bean
	@Scope("prototype")
	public Prodotto prodotto(Map<String, String> params) {
		Prodotto prodotto = new Prodotto();
		prodotto.fromMap(params);
		return prodotto;
	}
}