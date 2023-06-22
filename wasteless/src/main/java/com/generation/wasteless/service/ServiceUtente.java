package com.generation.wasteless.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.generation.wasteless.database.DaoUtente;
import com.generation.wasteless.entities.Utente;

public class ServiceUtente {

	@Autowired
	private DaoUtente daoUtente;

	@Autowired
	private ApplicationContext context;

	public Utente utente;

	public List<Utente> Utenti = new ArrayList<>();

	public List<Utente> getUtenti(){
		if(Utenti.isEmpty())
			aggiornaUtenti();
		return Utenti;
	}


	public void aggiornaUtenti(){
		List<Utente> listUtenti = new ArrayList<>();

		for(Map<String, String> params : daoUtente.read())
			listUtenti.add(context.getBean(Utente.class, params));

		Utenti = listUtenti;
	}
	//cache

	public Utente login (String email, String password) {
		List<Utente> listUtenti = getUtenti();
		for (Utente u : listUtenti) {
			if (u.getEmail().equalsIgnoreCase(email) && u.getPassword().equals(password))
				return u;
		}
		return null;
	}
	// login


	public boolean isUserExists(String user){

		for(Utente u : getUtenti()){
			if(u.getEmail().equalsIgnoreCase(user))
				return true;
		}
		return false;
	}

	public boolean registrazione (Utente u) {
		if (!isUserExists(u.getEmail())) {
			daoUtente.add(u.toMap());
			utente = u;
			aggiornaUtenti();
			return true;
		}
		return false;
	}
}
