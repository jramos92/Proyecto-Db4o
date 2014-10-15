package org.jrsoft.Casino.util;

public class Constantes {
	
	//Tabla Juego
	public static final String JUE_NOMBRE = "Nombre";
	public static final String JUE_CROUPIER = "Croupier";
	public static final String JUE_PREMIO = "Premio";
	public static final String JUE_APUESMAX = "Apuesta Maxima";
	public static final String JUE_APUESMIN =  "Apuesta Minima";
	
	//Numero Casilla Juego
	public static final int C_JUE_TODOS = 0;
	public static final int C_JUE_NOMBRE = 1;
	public static final int C_JUE_CROUPIER = 2;
	public static final int C_JUE_PREMIO = 3;
	public static final int C_JUE_APUESMAX = 4;
	public static final int C_JUE_APUESMIN = 5;
	
	//Tabla Ciudad
	public static final String CIU_NOMBRE = "Nombre";
	public static final String CIU_HABITANTES = "Habitantes";
	public static final String CIU_CAPITAL = "Capital";
	
	//Numero Casilla Ciudad
	public static final int C_CIU_TODOS = 0;
	public static final int C_CIU_NOMBRE = 1;
	public static final int C_CIU_HABITANTES = 2;
	public static final int C_CIU_CAPITAL = 3;
	
	//Tabla Casino 
	public static final String CA_NOMBRE = "Nombre";
	public static final String CA_CP = "Codigo Postal";
	public static final String CA_INGRESOS = "Ingresos";
	public static final String CA_PERDIDAS = "Perdidas";
	public static final String CA_CIUDAD = "Ciudad";
	
	//Numero Casilla casino
	public static final int C_CA_TODOS = 0;
	public static final int C_CA_NOMBRE = 1;
	public static final int C_CA_CP = 2;
	public static final int C_CA_INGRESOS = 3;
	public static final int C_CA_PERDIDAS = 4;
	public static final int C_CA_CIUDAD = 5;
	
	//Tabla jugador
	public static final String J_NOMBRE = "Nombre";
	public static final String J_APELLIDOS = "Apellidos";
	public static final String J_NACIMIENTO = "Fecha De Nacimiento";
	public static final String J_CASINO = "Casino";
	public static final String J_JUEGO = "Juego";
	
	//Numero Casilla Jugador
	public static final int C_J_TODOS = 0;
	public static final int C_J_NOMBRE = 1;
	public static final int C_J_APELLIDO = 2;
	public static final int C_J_NACIMIENTO = 3;
	public static final int C_J_CASINO = 4;
	public static final int C_J_JUEGO = 5;



	//Pestañas
	public static final int casino = 0;
	public static final int ciudad = 1;
	public static final int juego = 2;
	public static final int jugador = 3;
}
