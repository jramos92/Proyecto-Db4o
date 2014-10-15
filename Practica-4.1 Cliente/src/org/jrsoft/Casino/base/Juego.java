package org.jrsoft.Casino.base;

public class Juego {

	private String nombre;
	private String coupier;
	private int premio;
	private int apuestamax;
	private int apuestamin;

	public Juego(){}
	
	
	public Juego(String nombre, String coupier, int premio, int apuestamax,
			int apuestamin) {
		this.nombre = nombre;
		this.coupier = coupier;
		this.premio = premio;
		this.apuestamax = apuestamax;
		this.apuestamin = apuestamin;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getCoupier() {
		return coupier;
	}


	public void setCoupier(String coupier) {
		this.coupier = coupier;
	}


	public int getPremio() {
		return premio;
	}


	public void setPremio(int premio) {
		this.premio = premio;
	}


	public int getApuestamax() {
		return apuestamax;
	}


	public void setApuestamax(int apuestamax) {
		this.apuestamax = apuestamax;
	}


	public int getApuestamin() {
		return apuestamin;
	}


	public void setApuestamin(int apuestamin) {
		this.apuestamin = apuestamin;
	}
	
	public String toString(){
		return nombre;
	}
	
}
