package com.generation.wasteless.context;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.generation.wasteless.database.DaoUtente;
import com.generation.wasteless.database.DaoProdotto;
import com.generation.wasteless.database.Database;


@Configuration
public class DatabaseContext {
	
		
		@Bean
		public Database database() {
			return new Database();
		}
		
		@Bean
		public DaoUtente daoUtente() {
			return new DaoUtente();
		}
	
		@Bean
		public DaoProdotto daoprodotto() {
			return new DaoProdotto();
		}
	

	
		
}
		