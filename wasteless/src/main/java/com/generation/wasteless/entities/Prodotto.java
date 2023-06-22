package com.generation.wasteless.entities;

import java.time.LocalDate;

public class Prodotto extends Entity{

	private String nome;
	private LocalDate dataScadenza;
	private int quantita;
	private String descrizione;
	private float prezzo;
	private float prezzoVendita;
	private boolean alimento;
	private String stato;
	private int giorni;
	private int idUtente;


	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public LocalDate getDataScadenza() {
		return dataScadenza;
	}
	public void setDataScadenza(LocalDate dataScadenza) {
		this.dataScadenza = dataScadenza;
	}
	public int getQuantita() {
		return quantita;
	}
	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public float getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}
	public boolean isAlimento() {
		return alimento;
	}
	public void setAlimento(boolean alimento) {
		this.alimento = alimento;
	}
	public String getStato() {
		return stato;
	}
	public void setStato(String stato) {
		this.stato = stato;
	}
	public int getIdUtente() {
		return idUtente;
	}
	public void setIdUtente(int idUtente) {
		this.idUtente = idUtente;
	}
	public int getGiorni() {
		return giorni;
	}
	public void setGiorni(int giorni) {
		this.giorni = giorni;
	}


	@Override
	public String toString() {
		return "prodotto [nome=" + nome + ", dataScadenza=" + dataScadenza + ", quantita=" + quantita + ", descrizione="
				+ descrizione + ", prezzo=" + prezzo + ", alimento=" + alimento + ", stato=" + stato + ", idUtente="
				+ idUtente + "]";
	}




}