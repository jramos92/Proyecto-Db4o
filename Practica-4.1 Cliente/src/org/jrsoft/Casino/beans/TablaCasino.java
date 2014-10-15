package org.jrsoft.Casino.beans;

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.jrsoft.Casino.base.Casino;
import org.jrsoft.Casino.base.Ciudad;
import org.jrsoft.Casino.util.Constantes;
import org.jrsoft.Casino.util.Util;

import com.db4o.ObjectSet;
import com.db4o.query.Predicate;

public class TablaCasino extends JTable {

	private DefaultTableModel modeloDatos;
	
	public TablaCasino(){
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
		
		modeloDatos.addColumn(Constantes.CA_NOMBRE);
		modeloDatos.addColumn(Constantes.CA_CP);
		modeloDatos.addColumn(Constantes.CA_INGRESOS);
		modeloDatos.addColumn(Constantes.CA_PERDIDAS);
		modeloDatos.addColumn(Constantes.CA_CIUDAD);

		setModel(modeloDatos);
	}
	
	public void listar(){
		
		List<Casino> casinos = Util.db.query(Casino.class);
		cargarFilas(casinos);
	}
	
	public void listar(final String filtro, int campo){
		
		Casino casino = null;
		List<Casino> casinos = null;
		
		switch (campo) {
		case Constantes.C_CA_TODOS:
			
			casinos = Util.db.query(new Predicate<Casino>() {
				
				@Override
				public boolean match(Casino casino){
					
					if(casino.getNombre().contains(filtro) || casino.getCodigopostal().contains(filtro) ||
							casino.getCiudad().getNombre().contains(filtro))
						return true;
					
					return false;
				}
				
			});
			
			break;
			
		case Constantes.C_CA_NOMBRE:
			casino = new Casino();
			casino.setNombre(filtro);
			casinos = Util.db.queryByExample(casino);
			
			break;
			
		case Constantes.C_CA_CP:
			casino = new Casino();
			casino.setCodigopostal(filtro);
			casinos = Util.db.queryByExample(casino);
			
			break;

		case Constantes.C_CA_INGRESOS:
			casino = new Casino();
			casino.setIngresos(Integer.parseInt(filtro));
			casinos = Util.db.queryByExample(casino);
			
			break;
			
		case Constantes.C_CA_PERDIDAS:
			casino = new Casino();
			casino.setPerdidas(Integer.parseInt(filtro));
			casinos = Util.db.queryByExample(casino);
			
			break;
			
		case Constantes.C_CA_CIUDAD:
			casinos = Util.db.query(new Predicate<Casino>() {
				
				@Override
				public boolean match(Casino casino){
					
					if(casino.getCiudad().getNombre().contains(filtro))
						return true;
					
					return false;
				}
				
			});	
			
			break;
			
		default:
			break;
		}
		
		cargarFilas(casinos);
	}
	
	private void cargarFilas(List<Casino> casinos){
		
		modeloDatos.setNumRows(0);
		
		for(Casino casino : casinos){
			Object[] fila = new Object[]{
				casino.getNombre(),
				casino.getCodigopostal(),
				casino.getIngresos(),
				casino.getPerdidas(),
				casino.getCiudad()

		};
			
			modeloDatos.addRow(fila);
		}
	}
	
	public Casino getCasinoSeleccionado(){
		
		int filaSeleccionada = 0;
		
		filaSeleccionada = getSelectedRow();
		if(filaSeleccionada == -1)
			return null;
		
		String nombre = (String) getValueAt(filaSeleccionada, 0);
		Casino casino = new Casino();
		casino.setNombre(nombre);
		
		ObjectSet<Casino> resultado = Util.db.queryByExample(casino);
		casino = resultado.next();
		
		return casino;
		
	}
	
	public void vaciar(){
		
		modeloDatos.setNumRows(0);
	}
}
