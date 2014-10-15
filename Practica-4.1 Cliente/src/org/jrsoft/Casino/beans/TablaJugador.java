package org.jrsoft.Casino.beans;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.jrsoft.Casino.base.Jugador;
import org.jrsoft.Casino.util.Constantes;
import org.jrsoft.Casino.util.Util;

import com.db4o.ObjectSet;
import com.db4o.query.Predicate;

public class TablaJugador extends JTable{
	
	private DefaultTableModel modeloDatos;
	
	public TablaJugador() {
		super();
		inicializar();
	}

	private void inicializar(){
		
			modeloDatos = new DefaultTableModel(){
			
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		
		};
		
		modeloDatos.addColumn(Constantes.J_NOMBRE);
		modeloDatos.addColumn(Constantes.J_APELLIDOS);
		modeloDatos.addColumn(Constantes.J_NACIMIENTO);
		modeloDatos.addColumn(Constantes.J_CASINO);
		modeloDatos.addColumn(Constantes.J_JUEGO);
		
		setModel(modeloDatos);
	}
	
	public void listar(){
		
		List<Jugador> jugadores = Util.db.query(Jugador.class);
		
		cargarFilas(jugadores);
	}
	
	public void listar(final String filtro, int campo){
		
		Jugador jugador = null;
		List<Jugador> jugadores = null;
		
		switch (campo) {
		case Constantes.C_J_TODOS:
			
			jugadores = Util.db.query(new Predicate<Jugador>() {
				
				@Override
				public boolean match(Jugador jugador){

					if(jugador.getNombre().contains(filtro) || jugador.getApellidos().contains(filtro) ||
							jugador.getCasino().getNombre().contains(filtro) || 
							jugador.getJuego().getNombre().contains(filtro))

					return true;
					
			
				return false;
					
				}
			});
			
			break;
		
		case Constantes.C_J_NOMBRE:
			jugador = new Jugador();
			jugador.setNombre(filtro);
			jugadores = Util.db.queryByExample(jugador);
			
			break;
		case Constantes.C_J_APELLIDO:
			jugador = new Jugador();
			jugador.setApellidos(filtro);
			jugadores = Util.db.queryByExample(jugador);
			
			break;
			
		case Constantes.C_J_NACIMIENTO:
		try{
			jugador = new Jugador();
			jugador.setFechaNacimiento(new SimpleDateFormat().parse(filtro));
			jugadores = Util.db.queryByExample(jugador);
			
		}catch (ParseException pse) {
			pse.printStackTrace();
		}
			break;
			
		case Constantes.C_J_CASINO:
			
			jugadores = Util.db.query(new Predicate<Jugador>() {
				
				@Override
				public boolean match(Jugador jugador){

					if(jugador.getCasino().getNombre().contains(filtro))
							return true;
					
			
				return false;
					
				}
			});
			
			break;
			
		case Constantes.C_J_JUEGO:
			jugadores = Util.db.query(new Predicate<Jugador>() {
				
				@Override
				public boolean match(Jugador jugador){

					if(jugador.getJuego().getNombre().contains(filtro))
					return true;
					
			
				return false;
					
				}
			});

		default:
			break;
		}
		
		cargarFilas(jugadores);
	}
	
	private void cargarFilas(List<Jugador> jugadores){
		
			modeloDatos.setNumRows(0);
			
			for(Jugador jugador : jugadores){
				Object[] fila = new Object[]{
						
						jugador.getNombre(),
						jugador.getApellidos(),
						jugador.getFechaNacimiento(),
						jugador.getCasino(),
						jugador.getJuego()
						
				};
				
				modeloDatos.addRow(fila);
			}
		
	}
	
	public Jugador getJugadorSeleccionado(){
		
		int filaSeleccionada = 0;
		
		filaSeleccionada = getSelectedRow();
		if(filaSeleccionada == -1)
			return null;
		
		String nombre = (String) getValueAt(filaSeleccionada, 0);
		Jugador jugador = new Jugador();
		jugador.setNombre(nombre);
		
		ObjectSet<Jugador> resultado = Util.db.queryByExample(jugador);
		jugador = resultado.next();
		
		return jugador;
		
	}
	
	public void vaciar(){
		
		modeloDatos.setNumRows(0);
	}
}