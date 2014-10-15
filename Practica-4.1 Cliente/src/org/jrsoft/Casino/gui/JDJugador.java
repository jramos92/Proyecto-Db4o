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
import org.jrsoft.Casino.base.Juego;
import org.jrsoft.Casino.base.Jugador;
import org.jrsoft.Casino.beans.TablaCasino;
import org.jrsoft.Casino.util.Util;
import org.jrsoft.Casino.util.Util.AccionDialogo;

import com.toedter.calendar.JDateChooser;

import javax.swing.JComboBox;

public class JDJugador extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfNombre;
	private JTextField tfApellidos;
	private JDateChooser tfDateChosser;
	private JLabel lblCasino;
	private JLabel lblJuego;


	private AccionDialogo accion;
	private boolean esNuevo = true;
	private Jugador jugador;
	private JComboBox cbCasino;
	private JComboBox cbJuego;
	
	
	
	
	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		esNuevo = false;
		this.jugador = jugador;
		
		tfNombre.setText(jugador.getNombre());
		tfApellidos.setText(jugador.getApellidos());
		tfDateChosser.setDate(jugador.getFechaNacimiento());
		cbCasino.setSelectedItem(jugador.getCasino());
		cbJuego.setSelectedItem(jugador.getJuego());
		
		System.out.println(jugador.getCasino().getNombre());
		
	}

	private void aceptar(){
		
		if(esNuevo)
			jugador = new Jugador();
		
		jugador.setNombre(tfNombre.getText());
		jugador.setApellidos(tfApellidos.getText());
		jugador.setFechaNacimiento(tfDateChosser.getDate());
		jugador.setCasino((Casino) cbCasino.getSelectedItem());
		jugador.setJuego((Juego) cbJuego.getSelectedItem());
	
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
	
	private void inicializarComboCasino(){
		List<Casino> casinos = Util.db.query(Casino.class);
		
		for(Casino casino : casinos){
			
			cbCasino.addItem(casino);
		}
		
	
	}
	

	private void inicializarComboJuego(){
		
		List<Juego> juegos = Util.db.query(Juego.class);
		
		for(Juego juego : juegos){
			
			cbJuego.addItem(juego);
		}
	}

	/**
	 * Create the dialog.
	 */
	public JDJugador() {
		setModal(true);
		setTitle("Formulario Jugador");
		setBounds(100, 100, 294, 322);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		
		{
			JLabel lblNombre = new JLabel("Nombre :");
			lblNombre.setBounds(10, 23, 130, 14);
			contentPanel.add(lblNombre);
		}
		{
			JLabel lblApellidos = new JLabel("Apellidos :");
			lblApellidos.setBounds(10, 65, 137, 14);
			contentPanel.add(lblApellidos);
		}
		{
			JLabel lblFechaDeNacimiento = new JLabel("Fecha de Nacimiento :");
			lblFechaDeNacimiento.setBounds(10, 113, 130, 14);
			contentPanel.add(lblFechaDeNacimiento);
		}
		{
			tfNombre = new JTextField();
			tfNombre.setBounds(144, 20, 86, 20);
			contentPanel.add(tfNombre);
			tfNombre.setColumns(10);
		}
		{
			tfApellidos = new JTextField();
			tfApellidos.setBounds(144, 62, 86, 20);
			contentPanel.add(tfApellidos);
			tfApellidos.setColumns(10);
		}
		
		tfDateChosser = new JDateChooser();
		tfDateChosser.setBounds(144, 113, 95, 20);
		contentPanel.add(tfDateChosser);
		
		lblCasino = new JLabel("Casino :");
		lblCasino.setBounds(10, 175, 86, 14);
		contentPanel.add(lblCasino);
		
		lblJuego = new JLabel("Juego :");
		lblJuego.setBounds(10, 212, 86, 14);
		contentPanel.add(lblJuego);
		
		cbCasino = new JComboBox();
		cbCasino.setBounds(93, 172, 137, 20);
		contentPanel.add(cbCasino);
		
		cbJuego = new JComboBox();
		cbJuego.setBounds(93, 209, 137, 20);
		contentPanel.add(cbJuego);
	
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
		
		
		inicializarComboCasino();
		inicializarComboJuego();
	}
}
