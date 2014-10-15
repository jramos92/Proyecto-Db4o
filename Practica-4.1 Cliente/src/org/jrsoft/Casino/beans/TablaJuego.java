package org.jrsoft.Casino.beans;

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.jrsoft.Casino.base.Juego;
import org.jrsoft.Casino.util.Constantes;
import org.jrsoft.Casino.util.Util;

import com.db4o.ObjectSet;
import com.db4o.query.Predicate;

public class TablaJuego extends JTable {
	
	private DefaultTableModel modeloDatos;

	
	public TablaJuego(){
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
		
		modeloDatos.addColumn(Constantes.JUE_NOMBRE);
		modeloDatos.addColumn(Constantes.JUE_CROUPIER);
		modeloDatos.addColumn(Constantes.JUE_PREMIO);
		modeloDatos.addColumn(Constantes.JUE_APUESMAX);
		modeloDatos.addColumn(Constantes.JUE_APUESMIN);
		
		setModel(modeloDatos);
	}
	
	public void listar(){
		
		List<Juego> juegos = Util.db.query(Juego.class);
		cargarFilas(juegos);
	}
	
	public void listar(final String filtro, int campo){
		
		Juego juego = null;
		List<Juego> juegos = null;
		
		switch (campo) {
		case Constantes.C_JUE_TODOS:
			
			juegos = Util.db.query(new Predicate<Juego>(){
				
				@Override
				public boolean match(Juego juego){
					
					if(juego.getNombre().contains(filtro) || juego.getCoupier().contains(filtro))
						return true;
					
					return false;
				}
		});
	
			break;
			
		case Constantes.C_JUE_NOMBRE:
			juego = new Juego();
			juego.setNombre(filtro);
			juegos = Util.db.queryByExample(juego);
			
			break;
			
		case Constantes.C_JUE_CROUPIER:
			juego = new Juego();
			juego.setCoupier(filtro);
			juegos = Util.db.queryByExample(juego);
			
			break;
			
		case Constantes.C_JUE_PREMIO:
			juego = new Juego();
			juego.setPremio(Integer.parseInt(filtro));
			juegos = Util.db.queryByExample(juego);
			break;
			
		case Constantes.C_JUE_APUESMAX:
			juego = new Juego();
			juego.setApuestamax(Integer.parseInt(filtro));
			juegos = Util.db.queryByExample(juego);
			break;
			
		case Constantes.C_JUE_APUESMIN:
			juego = new Juego();
			juego.setApuestamin(Integer.parseInt(filtro));
			juegos = Util.db.queryByExample(juego);
			break;

		default:
			break;
		}
		
		cargarFilas(juegos);
		
	}
	
	private void cargarFilas(List<Juego> juegos){
		
		modeloDatos.setNumRows(0);
		
		for(Juego juego : juegos){
			Object[] fila = new Object[]{
					juego.getNombre(),
					juego.getCoupier(),
					juego.getPremio(),
					juego.getApuestamax(),
					juego.getApuestamin()};
			
			modeloDatos.addRow(fila);
		}
	}
	
	public Juego getJuegoSeleccionado(){
		
		int filaSeleccionada = 0;
		
		filaSeleccionada = getSelectedRow();
		if(filaSeleccionada == -1)
			return null;
		
		String nombre = (String) getValueAt(filaSeleccionada, 0);
		Juego juego = new Juego();
		juego.setNombre(nombre);
		
		ObjectSet<Juego> resultado = Util.db.queryByExample(juego);
		juego = resultado.next();
		
		return juego;
		
	}
	
	public void vaciar(){
		
		modeloDatos.setNumRows(0);
	}
}
