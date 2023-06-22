package com.generation.wasteless.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.generation.wasteless.database.DaoUtente;
import com.generation.wasteless.entities.Utente;
import com.generation.wasteless.service.ServiceProdotto;
import com.generation.wasteless.service.ServiceUtente;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	@Autowired
	private DaoUtente daoUtente;
	@Autowired
	private ApplicationContext context;
	@Autowired
	private ServiceUtente serviceUtente;
	@Autowired
	private ServiceProdotto serviceProdotto;

	@GetMapping("/")
	public String home(HttpSession session){
		return "principale.html";
	}


	@GetMapping("/formlogin")
	public String formlogin() {
		return "login.html";
	}


	@PostMapping("/login")
	public String login(@RequestParam("email") String email,
						@RequestParam("password") String password,
						HttpSession session){

		Utente utente = serviceUtente.login(email, password);
		String ris = "";



		if(utente == null){
			ris = "redirect:formlogin";
		}else{

			session.setAttribute("loggato","true");
			session.setAttribute("utente", utente);
			serviceProdotto.setUtente(utente);
			ris = "redirect:dispensa";

		}
		return ris;
	}

	@GetMapping("/logout")
	public String logout(HttpSession session)
	{
		//setto nuovamente il valore dell'attributo loggato a null
		//setto anche il valore dell'istanza utente a null in modo da togliere quei dati dalla session
		session.setAttribute("loggato",null);
		session.setAttribute("utente", null);
		return "principale.html";
	}

}