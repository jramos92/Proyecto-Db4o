package org.jrsoft.Casino.beans;

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.jrsoft.Casino.base.Ciudad;
import org.jrsoft.Casino.util.Constantes;
import org.jrsoft.Casino.util.Util;

import com.db4o.ObjectSet;
import com.db4o.query.Predicate;

public class TablaCiudad extends JTable {
	
	private DefaultTableModel modeloDatos;

	public TablaCiudad() {
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
		
		modeloDatos.addColumn(Constantes.CIU_NOMBRE);
		modeloDatos.addColumn(Constantes.CIU_HABITANTES);
		modeloDatos.addColumn(Constantes.CIU_CAPITAL);

		setModel(modeloDatos);
	
		
	}
	
	public void listar(){
		
		List<Ciudad> ciudades = Util.db.query(Ciudad.class);
		cargarFilas(ciudades);
	}
	
	public void listar(final String filtro, int campo){
		
		Ciudad ciudad = null;
		List<Ciudad> ciudades = null;
		
		switch (campo) {
		case Constantes.C_CIU_TODOS:
			
			ciudades = Util.db.query(new Predicate<Ciudad>(){
				
				@Override
				public boolean match(Ciudad ciudad){
					
					if(ciudad.getNombre().contains(filtro) || ciudad.getCapital().contains(filtro))
						return true;
					
					return false;
						
				}
		});
		break;
			
		case Constantes.C_CIU_NOMBRE:
			ciudad = new Ciudad();
			ciudad.setNombre(filtro);
			ciudades = Util.db.queryByExample(ciudad);
			
			break;
			
		case Constantes.C_CIU_HABITANTES:
			ciudad = new Ciudad();
			ciudad.setHabitantes(Integer.parseInt(filtro));
			ciudades = Util.db.queryByExample(ciudad);
			
			break;
			
		case Constantes.C_CIU_CAPITAL:
			ciudad = new Ciudad();
			ciudad.setCapital(filtro);
			ciudades = Util.db.queryByExample(ciudad);
			break;

		default:
			break;
		}
		
		cargarFilas(ciudades);
		
	}
	
	private void cargarFilas(List<Ciudad> ciudades){
		
		modeloDatos.setNumRows(0);
		
		for(Ciudad ciudad : ciudades){
			Object[] fila = new Object[]{
				ciudad.getNombre(),
				ciudad.getHabitantes(),
				ciudad.getCapital()};
			
			modeloDatos.addRow(fila);
		}
	}
	
	public Ciudad getJuegoSeleccionado(){
		
		int filaSeleccionada = 0;
		
		filaSeleccionada = getSelectedRow();
		if(filaSeleccionada == -1)
			return null;
		
		String nombre = (String) getValueAt(filaSeleccionada, 0);
		Ciudad ciudad = new Ciudad();
		ciudad.setNombre(nombre);
		
		ObjectSet<Ciudad> resultado = Util.db.queryByExample(ciudad);
		ciudad = resultado.next();
		
		return ciudad;
		
	}
	
	public void vaciar(){
		
		modeloDatos.setNumRows(0);
	}

}
