package org.jrsoft.Casino.base;

public class Casino {

	private String nombre;
	private String codigopostal;
	private double ingresos;
	private double perdidas;

	private Ciudad ciudad;
	
	

	public Casino(){}
	
	public Casino(String nombre, String codigopostal, double ingresos,
			double perdidas, Ciudad ciudad) {
		super();
		this.nombre = nombre;
		this.codigopostal = codigopostal;
		this.ingresos = ingresos;
		this.perdidas = perdidas;
		this.ciudad = ciudad;
	   
	}
	
	

	public Ciudad getCiudad() {
		return ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getCodigopostal() {
		return codigopostal;
	}


	public void setCodigopostal(String codigopostal) {
		this.codigopostal = codigopostal;
	}


	public double getIngresos() {
		return ingresos;
	}


	public void setIngresos(double ingresos) {
		this.ingresos = ingresos;
	}


	public double getPerdidas() {
		return perdidas;
	}


	public void setPerdidas(double perdidas) {
		this.perdidas = perdidas;
	}
	
	public String toString(){
		return nombre;
	}


	
}
