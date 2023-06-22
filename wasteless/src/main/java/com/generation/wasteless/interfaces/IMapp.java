package com.generation.wasteless.interfaces;


import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

public interface IMapp {
	default void fromMap(Map<String, String> map) {
		for(Method m: this.getClass().getMethods()) {
			if(m.getName().startsWith("set") && m.getParameterCount() == 1) {
				String nomeProp = m.getName().substring(3);
				nomeProp = Character.toLowerCase(nomeProp.charAt(0)) + nomeProp.substring(1);
				if(map.containsKey(nomeProp)) {
					String valore = map.get(nomeProp);
					if(valore == null)
						continue;
					try {
						String tipoParametro = m.getParameters()[0].getType().getSimpleName().toLowerCase();
						switch(tipoParametro) {
						case "string":
							m.invoke(this, valore);
							break;
						case "int":
							m.invoke(this, Integer.parseInt(valore));
							break;
						case "double":
							m.invoke(this, Double.parseDouble(valore));
							break;
						case "float":
							m.invoke(this, Float.parseFloat(valore));
							break;
						case "boolean":
							m.invoke(this,	valore.equals("1")		||
									valore.equalsIgnoreCase("true")	||
									valore.equalsIgnoreCase("t")	||
									valore.equalsIgnoreCase("vero")	||
									valore.equalsIgnoreCase("v")	||
									valore.equalsIgnoreCase("si")	||
									valore.equalsIgnoreCase("s")	||
									valore.equalsIgnoreCase("y"))	;
							break;
						 case "localdate":
                             DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                             LocalDate localDate = LocalDate.parse(valore, formatter);
                             m.invoke(this, localDate);
                             break;
						}
					}catch (NumberFormatException e) {
						//e.printStackTrace();
						System.err.println("controlla i valori numerici");
					}catch (Exception e) {
						//e.printStackTrace();
						System.err.println("errore generico");
					}
				}
			}
		}
	}
	
	default Map<String, String> toMap(){
		Map<String, String> ris = new LinkedHashMap<>();
		for(Method m : this.getClass().getMethods()) {
			if((m.getName().startsWith("get") || m.getName().startsWith("is")) &&
				!m.getName().equalsIgnoreCase("getClass") &&
				m.getParameterCount() == 0)
				try {
					int partenza = m.getName().startsWith("get") ? 3 : 2;
					String nomeProp = m.getName().substring(partenza).toLowerCase();
					ris.put(nomeProp, m.invoke(this) + "");
				}catch (Exception e) {
					e.printStackTrace();
				}
		}
		return ris;
	}
}
