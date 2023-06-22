package com.generation.wasteless.entities;

public class Utente extends Entity{
	
	private String nome;
	private String cognome;
	private String email;
	private String password;
	private String ruolo;
	
	public String getRuolo() {
		return ruolo;
	}
	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String username) {
		this.email = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
	@Override
	public String toString() {
		return "toString(): " + super.toString() + "\nusername: " + email + "\npassword: " + password ;
	}

	
	
	
}
