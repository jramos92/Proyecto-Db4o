package org.jrsoft.Casino.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.jrsoft.Casino.base.Ciudad;
import org.jrsoft.Casino.util.Util.AccionDialogo;

public class JDCiudad extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblNombre;
	private JTextField tfNombre;
	
	private AccionDialogo accion;
	private Ciudad ciudad;
	private boolean esNueva = true;
	private JLabel lblHabitantes;
	private JTextField tfHabitantes;
	private JLabel lblCapital;
	private JTextField tfCapital;
	
	
	public Ciudad getCiudad() {
		return ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		esNueva = false;
		this.ciudad = ciudad;
		
		tfNombre.setText(ciudad.getNombre());
		tfHabitantes.setText(String.valueOf(ciudad.getHabitantes()));
		tfCapital.setText(ciudad.getCapital());
	
	}

	private void aceptar(){
		
		if(esNueva)
			ciudad = new Ciudad();
		
		ciudad.setNombre(tfNombre.getText());
		ciudad.setHabitantes(Integer.parseInt(tfHabitantes.getText()));
		ciudad.setCapital(tfCapital.getText());
		
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


	
	/**
	 * Create the dialog.
	 */
	public JDCiudad() {
		setModal(true);
		setTitle("Formulario Ciudad");
		setBounds(100, 100, 284, 236);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		
		lblNombre = new JLabel("Nombre :");
		lblNombre.setBounds(10, 24, 128, 14);
		contentPanel.add(lblNombre);
		
		tfNombre = new JTextField();
		tfNombre.setBounds(138, 21, 86, 20);
		contentPanel.add(tfNombre);
		tfNombre.setColumns(10);
		
		lblHabitantes = new JLabel("Habitantes :");
		lblHabitantes.setBounds(10, 68, 128, 14);
		contentPanel.add(lblHabitantes);
		
		tfHabitantes = new JTextField();
		tfHabitantes.setBounds(138, 65, 86, 20);
		contentPanel.add(tfHabitantes);
		tfHabitantes.setColumns(10);
		
		lblCapital = new JLabel("Capital :");
		lblCapital.setBounds(10, 114, 46, 14);
		contentPanel.add(lblCapital);
		
		tfCapital = new JTextField();
		tfCapital.setBounds(138, 111, 86, 20);
		contentPanel.add(tfCapital);
		tfCapital.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
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
	}
}
