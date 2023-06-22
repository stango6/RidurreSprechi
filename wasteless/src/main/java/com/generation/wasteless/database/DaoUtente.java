package com.generation.wasteless.database;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.generation.wasteless.interfaces.IDao;

public class DaoUtente implements IDao {
	@Autowired
	private Database db;
	private String insert = 
			"insert into utenti (nome, cognome, email, password, ruolo) values (? ,?, ?, ?, ?)";
	
	private String read = 
			"select * from utenti";
	
	private String update = 
			"update utenti set nome = ?, cognome = ?, email = ?, password = ? where id = ?" ;
	
	private String delete = 
			"delete from utenti where id = ?";
	
	
	
	public void add(Map<String, String> map) {
		db.update(insert, map.get("nome"), map.get("cognome"),map.get("email"),map.get("password"), map.get("ruolo") );
	}

	public List<Map<String, String>> read() {
		
		return db.execute(read);
	}
	
	
	public void update(Map<String, String> map) {
		
		db.update(update, map.get("nome"), map.get("cognome"),map.get("email"),map.get("password"), map.get("id") );
	}

	
	public void delete(int id) {
		
		db.update(delete, Integer.toString(id));
	}

	public List<Map<String,String>> leggiTutti()
	{
		return db.execute("select * from utenti");
	}

	public boolean isUserExists(String user){
		List<Map<String,String>> ris = leggiTutti();
		for(Map<String,String> m : ris){
			if(m.get("email").equals(user))
				return true;
		}
		return false;
	}
}
