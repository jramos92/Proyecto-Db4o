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

import org.jrsoft.Casino.base.Juego;
import org.jrsoft.Casino.util.Util.AccionDialogo;

public class JDJuego extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblNombre;
	private JLabel lblCroupier;
	private JLabel lblPremio;
	private JLabel lblApuestaMinima;
	private JLabel lblApuestaMaxima;
	private JTextField tfNombre;
	private JTextField tfCroupier;
	private JTextField tfPremio;
	private JTextField tfApuestaMax;
	private JTextField tfApuesMin;
	
	private AccionDialogo accion;
	private Juego juego;
	private boolean esNueva = true;
	
	
	public Juego getJuego() {
		return juego;
	}

	public void setJuego(Juego juego) {
		this.juego = juego;
		esNueva = false;
		
		tfNombre.setText(juego.getNombre());
		tfCroupier.setText(juego.getCoupier());
		tfPremio.setText(String.valueOf(juego.getPremio()));
		tfApuestaMax.setText(String.valueOf(juego.getApuestamax()));
		tfApuesMin.setText(String.valueOf(juego.getApuestamin()));
	}
	
	private void aceptar(){
		
		if(esNueva)
			juego = new Juego();
		
		juego.setNombre(tfNombre.getText());
		juego.setCoupier(tfCroupier.getText());
		juego.setPremio(Integer.parseInt(tfPremio.getText()));
		juego.setApuestamax(Integer.parseInt(tfApuestaMax.getText()));
		juego.setApuestamin(Integer.parseInt(tfApuesMin.getText()));
		
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
	public JDJuego() {
		setModal(true);
		setTitle("Formulario Juego");
		setBounds(100, 100, 323, 340);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		
		lblNombre = new JLabel("Nombre :");
		lblNombre.setBounds(10, 24, 128, 14);
		contentPanel.add(lblNombre);
		
		lblCroupier = new JLabel("Croupier :");
		lblCroupier.setBounds(10, 65, 128, 14);
		contentPanel.add(lblCroupier);
		
		lblPremio = new JLabel("Premio :");
		lblPremio.setBounds(10, 110, 128, 14);
		contentPanel.add(lblPremio);
		
		lblApuestaMinima = new JLabel("Apuesta Minima :");
		lblApuestaMinima.setBounds(10, 207, 128, 14);
		contentPanel.add(lblApuestaMinima);
		
		lblApuestaMaxima = new JLabel("Apuesta Maxima :");
		lblApuestaMaxima.setBounds(10, 157, 128, 14);
		contentPanel.add(lblApuestaMaxima);
		
		tfNombre = new JTextField();
		tfNombre.setBounds(138, 21, 86, 20);
		contentPanel.add(tfNombre);
		tfNombre.setColumns(10);
		
		tfCroupier = new JTextField();
		tfCroupier.setBounds(138, 62, 86, 20);
		contentPanel.add(tfCroupier);
		tfCroupier.setColumns(10);
		
		tfPremio = new JTextField();
		tfPremio.setBounds(138, 107, 86, 20);
		contentPanel.add(tfPremio);
		tfPremio.setColumns(10);
		
		tfApuestaMax = new JTextField();
		tfApuestaMax.setBounds(138, 154, 86, 20);
		contentPanel.add(tfApuestaMax);
		tfApuestaMax.setColumns(10);
		
		tfApuesMin = new JTextField();
		tfApuesMin.setBounds(138, 204, 86, 20);
		contentPanel.add(tfApuesMin);
		tfApuesMin.setColumns(10);
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
