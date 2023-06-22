package com.generation.wasteless.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Database {
	
	private String nomeDb;
	private String percorso;
	private String username;
	private String password;
	private Connection c;
	
	private final static String DRIVER = "com.mysql.cj.jdbc.Driver";
	
	public Database() {
		this.nomeDb = "wasteless";
		this.username = "root";
		this.password = "root";
		setPercorso(nomeDb);
	}
	
	private void setPercorso(String nomeDb) {
		String timeZone = "useUnicode="
				+ "true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode"
				+ "=false&serverTimezone=UTC";
		String jdbc = "jdbc:mysql://localhost:3306/";
		this.percorso = jdbc + nomeDb + "?" + timeZone;
	}
	
	public void apriConn() {
		try {
			Class.forName(DRIVER);
			c = DriverManager.getConnection(percorso, username, password);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void chiudiConn() {
		try {
			c.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Map<String, String>> execute(String query, String...params){
		List<Map<String, String>> listaMappe = new ArrayList<>();
		PreparedStatement ps = null;
		apriConn();
		try {
			ps = c.prepareStatement(query);
			for(int i = 0; i < params.length; i++)
				ps.setString(i + 1, params[i]);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Map<String, String> m = new LinkedHashMap<>();
				int nColonne = rs.getMetaData().getColumnCount();
				for(int i = 1; i <= nColonne; i++) {
					m.put(rs.getMetaData().getColumnLabel(i), rs.getString(i));
				}
				listaMappe.add(m);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(ps != null)
				try {
					ps.close();
				}catch (Exception e) {
					e.printStackTrace();
				}
			chiudiConn();
		}
		return listaMappe;
	}
	
	public void update(String query, String...params) {
		PreparedStatement ps = null;
		apriConn();
		try {
			ps = c.prepareStatement(query);
			for(int i = 0; i < params.length; i++)
				ps.setString(i + 1, params[i]);
			ps.executeUpdate();
		}catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("Questa operazione non è consentita");
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(ps != null)
				try {
					ps.close();
				}catch (Exception e) {
					e.printStackTrace();
				}
			chiudiConn();
		}
	}
}
