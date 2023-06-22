package com.generation.wasteless.database;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.generation.wasteless.interfaces.IDao;

public class DaoProdotto implements IDao {
	@Autowired
	private Database db;

	private String insert =
			"insert into prodotti (nome,dataScadenza,quantita,descrizione,prezzo,"
					+ "alimento,stato,giorni,idUtente) values (?,?,?,?,?,?,?,?,?)";

	private String read =
			"select * from prodotti";

	private String select = "select * from prodotti where idUtente = ?";

	private String update =
			"update prodotti set nome = ?, dataScadenza = ?, quantita = ?, descrizione = ?,"
					+ "prezzo = ?, alimento = ?, stato = ?, giorni = ?, idUtente = ? where id = ?";

	private String delete =
			"delete from prodotti where id = ?";

	public void add(Map<String, String> map) {
		db.update(insert, map.get("nome"), map.get("datascadenza"),map.get("quantita"),map.get("descrizione"),
				map.get("prezzo"),(map.get("alimento").equals("true")?"1":"0"),map.get("stato"),
				map.get("giorni"), map.get("idutente"));

	}


	public List<Map<String, String>> read() {
		return db.execute(read);
	}


	public List<Map<String, String>> select(int id) {

		return db.execute(select, Integer.toString(id));
	}


	public void update(Map<String, String> map) {
		db.update(update, map.get("nome"), map.get("datascadenza"),map.get("quantita"),map.get("descrizione"),
				map.get("prezzo"), (map.get("alimento").equals("true")?"1":"0"),map.get("stato"),
				map.get("giorni"), map.get("idutente"), map.get("id"));
	}


	public void delete(int id) {

		db.update(delete, Integer.toString(id));
	}

}