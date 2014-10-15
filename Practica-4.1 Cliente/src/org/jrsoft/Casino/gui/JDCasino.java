package org.jrsoft.Casino.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.jrsoft.Casino.base.Casino;
import org.jrsoft.Casino.base.Ciudad;
import org.jrsoft.Casino.util.Util;
import org.jrsoft.Casino.util.Util.AccionDialogo;

import javax.swing.JComboBox;

public class JDCasino extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfNombre;
	private JTextField tfCp;
	private JTextField tfIngresos;
	private JTextField tfPerdidas;

	private Casino casino;
	private AccionDialogo accion;
	private boolean esNueva = true;
	private JComboBox cbCiudad;

	

	public Casino getCasino() {
		return casino;
	}


	public void setCasino(Casino casino) {

		esNueva = false;
		this.casino = casino;
		
		tfNombre.setText(casino.getNombre());
		tfCp.setText(casino.getCodigopostal());
		tfIngresos.setText(String.valueOf(casino.getIngresos()));
		tfPerdidas.setText(String.valueOf(casino.getPerdidas()));
		cbCiudad.setSelectedItem(casino.getCiudad());
		
	}
	
	private void aceptar(){

		
		if(esNueva){
			casino = new Casino();
		}
		
		casino.setNombre(tfNombre.getText());
		casino.setCodigopostal(tfCp.getText());
		casino.setIngresos(Double.parseDouble(tfIngresos.getText()));
		casino.setPerdidas(Double.parseDouble(tfPerdidas.getText()));
		casino.setCiudad((Ciudad) cbCiudad.getSelectedItem());
		
		accion = AccionDialogo.ACEPTAR;
		setVisible(false);
	}
	
	private void cancelar(){
		accion = AccionDialogo.CANCELAR;
		setVisible(false);
	
	}

	public AccionDialogo mostrarDialogo(){
		setVisible(true);
		return accion;
	}
	
	private void iniciarComboCiudad(){
		
		List<Ciudad> ciudades = Util.db.query(Ciudad.class);
		
		for(Ciudad ciudad : ciudades){
			
			cbCiudad.addItem(ciudad);
		}
	}
	


	/**
	 * Create the dialog.
	 */
	public JDCasino() {
		setTitle("Formulario Casino");
		setModal(true);
		setBounds(100, 100, 278, 330);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		{
			JLabel lbNombre = new JLabel("Nombre :");
			lbNombre.setBounds(10, 22, 94, 14);
			contentPanel.add(lbNombre);
		}
		{
			JLabel lbcp = new JLabel("Codigo Postal :");
			lbcp.setBounds(10, 63, 94, 14);
			contentPanel.add(lbcp);
		}
		{
			JLabel lbIngresos = new JLabel("Ingresos  :");
			lbIngresos.setBounds(10, 106, 94, 14);
			contentPanel.add(lbIngresos);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("Perdidas :");
			lblNewLabel_3.setBounds(10, 152, 94, 14);
			contentPanel.add(lblNewLabel_3);
		}
		{
			tfNombre = new JTextField();
			tfNombre.setBounds(105, 19, 86, 20);
			contentPanel.add(tfNombre);
			tfNombre.setColumns(10);
		}
		{
			tfCp = new JTextField();
			tfCp.setBounds(105, 60, 86, 20);
			contentPanel.add(tfCp);
			tfCp.setColumns(10);
		}
		{
			tfIngresos = new JTextField();
			tfIngresos.setBounds(105, 103, 86, 20);
			contentPanel.add(tfIngresos);
			tfIngresos.setColumns(10);
		}
		{
			tfPerdidas = new JTextField();
			tfPerdidas.setBounds(105, 149, 86, 20);
			contentPanel.add(tfPerdidas);
			tfPerdidas.setColumns(10);
		}
		{
			JLabel lblCiudad = new JLabel("Ciudad :");
			lblCiudad.setBounds(10, 203, 69, 14);
			contentPanel.add(lblCiudad);
		}
		
		cbCiudad = new JComboBox();
		cbCiudad.setBounds(105, 200, 86, 20);
		contentPanel.add(cbCiudad);
		
	
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						aceptar();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cancelar();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		iniciarComboCiudad();
	}
}
