package org.jrsoft.Casino.base;

public class Ciudad {
	
	private String nombre;
	private int habitantes;
	private String capital;
	
	public Ciudad(){}

	public Ciudad(String nombre, int habitantes, String capital) {
		super();
		this.nombre = nombre;
		this.habitantes = habitantes;
		this.capital = capital;
	
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getHabitantes() {
		return habitantes;
	}

	public void setHabitantes(int habitantes) {
		this.habitantes = habitantes;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}


	
	public String toString(){
		return nombre;
	}

}
