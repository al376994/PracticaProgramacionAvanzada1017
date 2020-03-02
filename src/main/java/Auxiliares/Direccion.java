package Auxiliares;

import java.util.Dictionary;
import java.util.Hashtable;

public class Direccion {
	private int cp;
	private String provincia;
	private String poblacion;
	static private Hashtable<String, Integer> listedCp = new Hashtable<>();
	static private int actualCp = 0;

	public Direccion(String provincia, String poblacion) {
		this.provincia = provincia;
		this.poblacion = poblacion;
		this.cp = assignCp(provincia, poblacion);
	}

	public int getCp() {
		return cp;
	}

	public String getProvincia() {
		return provincia;
	}

	public String getPoblacion() {
		return poblacion;
	}

	private int assignCp(String provincia, String poblacion) {
		String key = provincia + poblacion;
		if (!listedCp.containsKey(key)) {
			actualCp+=1;
			listedCp.put(key, actualCp);
		}
		return listedCp.get(key);


	}
}
