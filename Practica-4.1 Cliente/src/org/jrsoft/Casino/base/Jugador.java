package org.jrsoft.Casino.base;

import java.util.Date;

public class Jugador {

	private String nombre;
	private String apellidos;
	private Date fechaNacimiento;
	private Casino casino;
	private Juego juego;
	

	public Jugador(){}
	
	public Jugador(String nombre, String apellidos, Date fechaNacimiento,
		Casino casino, Juego juego) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fechaNacimiento = fechaNacimiento;
		this.casino = casino;
		this.juego = juego;
	
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Casino getCasino() {
		return casino;
	}

	public void setCasino(Casino casino) {
		this.casino = casino;
	}

	public Juego getJuego() {
		return juego;
	}

	public void setJuego(Juego juego) {
		this.juego = juego;
	}
	
	
}
