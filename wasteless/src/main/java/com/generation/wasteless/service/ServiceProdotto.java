package com.generation.wasteless.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.generation.wasteless.database.DaoProdotto;
import com.generation.wasteless.entities.Prodotto;
import com.generation.wasteless.entities.Utente;

public class ServiceProdotto {

	@Autowired
	private DaoProdotto daoProdotto;

	@Autowired
	private ApplicationContext context;


	public Utente utente;

	public void setUtente(Utente u) {
		utente = u;
	}


	public List<Prodotto> prodotti = new ArrayList<>();


	// restituisce solo per prodotti ancora consumabili
	public List<Prodotto> getProdotti(){
		List<Prodotto> edibili = new ArrayList<>();;
		for (Prodotto p : tuttiProdotti()) {
			if (p.getStato().equalsIgnoreCase("Edibile")
					|| p.getStato().equalsIgnoreCase("In scadenza")) {
				edibili.add(p);
			}
		}
		return edibili;
	}

	//restituisce lista con tutti i prodotti, sia edibili che non
	public List<Prodotto> tuttiProdotti(){
		if(prodotti.isEmpty());
		aggiornaProdotti();
		aggiornaScandenza();
		System.out.println("LISTA PRODOTTI:" + prodotti);;
		//mettere aggirnoemnto con scadenza  e inscadenza dentro
		return prodotti;
	}


	// da usare per prendere i dati dal dao
	public void aggiornaProdotti(){
		List<Prodotto> listProdotti = new ArrayList<>();

		for(Map<String, String> params : daoProdotto.select(utente.getId())) {
			listProdotti.add(context.getBean(Prodotto.class, params));
			scadenza(listProdotti.get(listProdotti.size()-1));
		}
		prodotti = listProdotti;
	}

	public void aggiornaScandenza () {
		/*for (Prodotto p : Prodotti) {
			scadenza(p);
		}
		inScadenza();*/
		LocalDate dataAttuale = LocalDate.now();
		for (Prodotto p : prodotti) {
			scadenza(p);
			if (p.getDataScadenza().isBefore(dataAttuale)) {
				p.setStato("Scaduto");
			} else if (p.getStato().equalsIgnoreCase("Edibile") && p.getGiorni() <= 3) {
				p.setStato("In scadenza");
			}
		}
		inScadenza();
	}

	/*private void scadenza (Prodotto p) {
		LocalDate dataAttuale = LocalDate.now();
		p.setGiorni((int) dataAttuale.until(p.getDataScadenza(), ChronoUnit.DAYS));
	}*/

	private void scadenza(Prodotto p) {
		LocalDate dataAttuale = LocalDate.now();
		p.setGiorni((int) ChronoUnit.DAYS.between(dataAttuale, p.getDataScadenza()));
	}
	private void inScadenza () {
		LocalDate dataAttuale = LocalDate.now();
		for (Prodotto p : prodotti) {
			if (p.getStato().equalsIgnoreCase("Edibile")){
				if(p.getGiorni()<=3) {
					p.setStato("In scadenza");
				}

				if(p.getDataScadenza().isBefore(dataAttuale)){
					p.setStato("Scaduto");
				}
			}
		}
	}

	//da richiamare qunado il cliente  consuma il prodotto
	public void consumato (Prodotto p) {
		p.setStato("consumato");
		daoProdotto.update(p.toMap());
		aggiornaProdotti();
	}

	//da richiamare qunado il cliente spreca il prodotto
	public void sprecato (Prodotto p) {
		p.setStato("sprecato");
		daoProdotto.update(p.toMap());
		aggiornaProdotti();
	}

	//per ottenere la lista degli alimenti cosumati
	public  List<Prodotto> alimentiConsumati () {
		List<Prodotto> consumati = new ArrayList<>();;
		for (Prodotto p : tuttiProdotti()) {
			if (p.getStato().equalsIgnoreCase("consumato")) {
				consumati.add(p);
			}
		}
		return consumati;
	}
	//per ottenere la lista degli alimenti sprecati
	public  List<Prodotto> alimentiSprecati () {
		List<Prodotto> sprecati = new ArrayList<>();;
		for (Prodotto p : tuttiProdotti()) {
			if (p.getStato().equalsIgnoreCase("sprecato")) {
				sprecati.add(p);
			}
		}
		return sprecati;
	}


	//per ottenere i soldi spesi in alimenti sprecati
	public float soldiSprecati() {
		float sprecati = 0;
		for (Prodotto p : tuttiProdotti()) {
			if (p.getStato().equalsIgnoreCase("sprecato")) {
				sprecati += p.getPrezzo() * p.getQuantita();
			}
		}
		return sprecati;
	}

	public void updateProdotto(Map<String, String> params, int userId) {
		Prodotto prodotto = context.getBean(Prodotto.class, params);
		Map<String, String> map = prodotto.toMap();
		//map.put("idUtente", params.get("idUtente"));
		System.out.println("stampa dentro update" + map);
		daoProdotto.update(map);
	}

	public void deleteProdotto(int id) {
		daoProdotto.delete(id);
	}

	public void addProdotto(Map<String, String> params, int userId) {
		Prodotto prodotto = context.getBean(Prodotto.class, params);
		prodotto.setStato("Edibile");
		prodotto.setIdUtente(userId);
		//System.out.println("prodotto dentro addProdotto: " + prodotto);
		Map<String, String> map = prodotto.toMap();

		daoProdotto.add(map);
		//daoProdotto.addIdUtente(userId);
	}

	public void aggiornaQuantita (int id, int q) {
		for (Prodotto p : tuttiProdotti()) {
			if (p.getId() == id) {
				if (q <= p.getQuantita()){
					p.setQuantita(p.getQuantita() - q);
					if (p.getQuantita() == 0){
						daoProdotto.delete(p.getId());
					}else{
						daoProdotto.update(p.toMap());
						aggiornaProdotti();
					}
				}
			}
		}
	}

	//per ottenere il numero di alimenti sprecati
	public int nSprecati() {
		int sprecati = 0;
		for (Prodotto p : tuttiProdotti()) {
			if (p.getStato().equalsIgnoreCase("Scaduto")) {
				sprecati += p.getQuantita();
			}
		}
		return sprecati;
	}

	//per ottenere il numero di prodotti ancora consumabili

	public int nEdibili() {
		int edibili = 0;
		for (Prodotto p : tuttiProdotti()) {
			if (p.getStato().equalsIgnoreCase("Edibile")
					|| p.getStato().equalsIgnoreCase("In scadenza")) {
				edibili += p.getQuantita();

			} }
		return edibili;
	}

}