package org.jrsoft.Casino.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.jrsoft.Casino.base.Casino;
import org.jrsoft.Casino.base.Ciudad;
import org.jrsoft.Casino.base.Juego;
import org.jrsoft.Casino.base.Jugador;
import org.jrsoft.Casino.beans.TablaCasino;
import org.jrsoft.Casino.beans.TablaCiudad;
import org.jrsoft.Casino.beans.TablaJuego;
import org.jrsoft.Casino.beans.TablaJugador;
import org.jrsoft.Casino.util.Constantes;
import org.jrsoft.Casino.util.Util;
import org.jrsoft.Casino.util.Util.AccionDialogo;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import com.db4o.cs.Db4oClientServer;

public class Ventana {

	private JFrame frmPractica;
	private JMenuBar menuBar;
	private JMenu mnMenu;
	private JMenuItem mntmConectar;
	private JMenuItem mntmDesconectar;
	private JToolBar herramientas;
	private JButton btAlta;
	private JButton btModificar;
	private JButton btEliminar;
	private JButton btnBuscar;
	private JTabbedPane tabbedPane;
	private JPanel panel_casino;
	private JPanel panel_ciudad;
	private JPanel panel_juego;
	private JPanel panel_jugador;
	private JScrollPane scroll_casino;
	private JScrollPane scroll_ciudad;
	private JScrollPane scroll_juego;
	private JScrollPane scroll_jugador;
	private TablaJuego tablaJuego;
	private TablaCiudad tablaCiudad;
	private TablaCasino tablaCasino;
	private JTextField tfFiltro;
	private JComboBox<String> cbCampos;
	private TablaJugador tablaJugador;
	private JMenuItem mntmExportJugador;



	/**
	 * Create the application.
	 */
	public Ventana() {
		initialize();
		inicializar();
	}
	
	private void inicializar(){
		
		conectar();
		tablaJuego.listar();
		tablaCiudad.listar();
		tablaCasino.listar();
		tablaJugador.listar();
	}
	
	private void conectar(){
		
		Util.db = Db4oClientServer.openClient("localhost", 5555, "usuario", "contrasena");
		

	}
	
	private void desconectar(){
		
		Util.db.close();
		System.exit(0);
	}

	private void nuevo(){
		
		int pestana = tabbedPane.getSelectedIndex();
		
		switch (pestana) {
		case Constantes.casino:
			JDCasino jdCasino = new JDCasino();
			if(jdCasino.mostrarDialogo() == AccionDialogo.CANCELAR)
				return;
			
			Casino casino = jdCasino.getCasino();
			Util.db.store(casino);
			Util.db.commit();
			tablaCasino.listar();
			break;
			
		case Constantes.ciudad:
			
			JDCiudad jdCiudad = new JDCiudad();
			if(jdCiudad.mostrarDialogo() == AccionDialogo.CANCELAR)
				return;
			
			Ciudad ciudad = jdCiudad.getCiudad();
			Util.db.store(ciudad);
			Util.db.commit();
			
			tablaCiudad.listar();
			break;
			
		case Constantes.juego:
			
			JDJuego jdJuego = new JDJuego();
			if(jdJuego.mostrarDialogo() == AccionDialogo.CANCELAR)
				return;
			
			Juego juego = jdJuego.getJuego();
			Util.db.store(juego);
			Util.db.commit();
			
			tablaJuego.listar();
			
			break;
		
		case Constantes.jugador:
			JDJugador jdJugador = new JDJugador();
			if(jdJugador.mostrarDialogo() == AccionDialogo.CANCELAR)
				return;
			
			Jugador jugador = jdJugador.getJugador();
			Util.db.store(jugador);
			Util.db.commit();
			
			tablaJugador.listar();
			
			break;

		default:
			break;
		}
		
	}
	
	private void modificar(){
		
	int pestana = tabbedPane.getSelectedIndex();
		
		switch (pestana) {
		case Constantes.casino:
			JDCasino jdCasino = new JDCasino();
			jdCasino.setCasino(tablaCasino.getCasinoSeleccionado());
			
			if(jdCasino.mostrarDialogo() == AccionDialogo.CANCELAR)
				return;
			
			Casino casino = jdCasino.getCasino();
			Util.db.store(casino);
			Util.db.commit();
			tablaCasino.listar();
			break;
			
		case Constantes.ciudad:
			JDCiudad jdCiudad = new JDCiudad();
			jdCiudad.setCiudad(tablaCiudad.getJuegoSeleccionado());
			
			if(jdCiudad.mostrarDialogo() == AccionDialogo.CANCELAR)
				return;
			
			Ciudad ciudad = jdCiudad.getCiudad();
			Util.db.store(ciudad);
			Util.db.commit();
			tablaCiudad.listar();
			
			break;
			
		case Constantes.juego:
			JDJuego jdJuego = new JDJuego();
			jdJuego.setJuego(tablaJuego.getJuegoSeleccionado());
			
			if(jdJuego.mostrarDialogo() == AccionDialogo.CANCELAR)
				return;
			
			Juego juego = jdJuego.getJuego();
			Util.db.store(juego);
			Util.db.commit();
			tablaJuego.listar();
			
			break;
		
		case Constantes.jugador:
			JDJugador jdJugador = new JDJugador();
			jdJugador.setJugador(tablaJugador.getJugadorSeleccionado());
			
			if(jdJugador.mostrarDialogo() == AccionDialogo.CANCELAR)
				return;
			
			Jugador jugador = jdJugador.getJugador();
			Util.db.store(jugador);
			Util.db.commit();
			tablaJugador.listar();
			
			break;

		default:
			break;
		}
		
	}
	
	private void eliminar(){
		
	int pestana = tabbedPane.getSelectedIndex();
		
		switch (pestana) {
		case Constantes.casino:
			if (JOptionPane.showConfirmDialog(null, "¿Está seguro?", "Eliminar", JOptionPane.YES_NO_OPTION) == 
			JOptionPane.NO_OPTION) 
				return;
			
			Casino casino = tablaCasino.getCasinoSeleccionado();
			Util.db.delete(casino);
			Util.db.commit();
			tablaCasino.listar();
			break;
			
		case Constantes.ciudad:
			if (JOptionPane.showConfirmDialog(null, "¿Está seguro?", "Eliminar", JOptionPane.YES_NO_OPTION) == 
			JOptionPane.NO_OPTION) 
				return;
			
			Ciudad ciudad = tablaCiudad.getJuegoSeleccionado();
			Util.db.delete(ciudad);
			Util.db.commit();
			tablaCiudad.listar();
			
			break;
			
		case Constantes.juego:
			if (JOptionPane.showConfirmDialog(null, "¿Está seguro?", "Eliminar", JOptionPane.YES_NO_OPTION) == 
			JOptionPane.NO_OPTION) 
				return;
			
			Juego juego = tablaJuego.getJuegoSeleccionado();
			Util.db.delete(juego);
			Util.db.commit();
			tablaJuego.listar();
			break;
		
		case Constantes.jugador:
			if (JOptionPane.showConfirmDialog(null, "¿Está seguro?", "Eliminar", JOptionPane.YES_NO_OPTION) == 
			JOptionPane.NO_OPTION) 
				return;
			
			Jugador jugador = tablaJugador.getJugadorSeleccionado();
			Util.db.delete(jugador);
			Util.db.commit();
			tablaJugador.listar();
			break;

		default:
			break;
		}
		
	}
	
	private void buscar(){
		
		int pestana = tabbedPane.getSelectedIndex();

		
		switch (pestana) {
		case Constantes.casino:
			int campoCasino= cbCampos.getSelectedIndex();
		
			tablaCasino.listar(tfFiltro.getText(), campoCasino);
			break;
			
		case Constantes.ciudad:
			int campoCiudad= cbCampos.getSelectedIndex();

			tablaCiudad.listar(tfFiltro.getText(), campoCiudad);
			
			break;

		case Constantes.juego:
			int campoJuego = cbCampos.getSelectedIndex();

			tablaJuego.listar(tfFiltro.getText(), campoJuego);
			break;
			
		case Constantes.jugador:
			int campoJugador = cbCampos.getSelectedIndex();
			
			tablaJugador.listar(tfFiltro.getText(), campoJugador);
			
			break;
			
		default:
			break;
		}
		
	}
	
	private void cambiarPestana(){
		
		int pestana = tabbedPane.getSelectedIndex();
		
		switch (pestana) {
		case Constantes.casino:
			cbCampos.removeAllItems();
			cbCampos.addItem("<Todos>");
			cbCampos.addItem(Constantes.CA_NOMBRE);
			cbCampos.addItem(Constantes.CA_CP);
			cbCampos.addItem(Constantes.CA_INGRESOS);
			cbCampos.addItem(Constantes.CA_PERDIDAS);
			cbCampos.addItem(Constantes.CA_CIUDAD);
			break;
			
		case Constantes.ciudad:

			cbCampos.removeAllItems();
			cbCampos.addItem("<Todos>");
			cbCampos.addItem(Constantes.CIU_NOMBRE);
			cbCampos.addItem(Constantes.CIU_HABITANTES);
			cbCampos.addItem(Constantes.CIU_CAPITAL);
			break;
			
		case Constantes.juego:
			cbCampos.removeAllItems();
			cbCampos.addItem("<Todos>");
			cbCampos.addItem(Constantes.JUE_NOMBRE);
			cbCampos.addItem(Constantes.JUE_CROUPIER);
			cbCampos.addItem(Constantes.JUE_PREMIO);
			cbCampos.addItem(Constantes.JUE_APUESMAX);
			cbCampos.addItem(Constantes.JUE_APUESMIN);
			
			break;
			
		case Constantes.jugador:
			cbCampos.removeAllItems();
			cbCampos.addItem("<Todos>");
			cbCampos.addItem(Constantes.J_NOMBRE);
			cbCampos.addItem(Constantes.J_APELLIDOS);
			cbCampos.addItem(Constantes.J_NACIMIENTO);
			cbCampos.addItem(Constantes.J_CASINO);
			cbCampos.addItem(Constantes.J_JUEGO);
			
			break;

		default:
			break;
		}
		
	}

	private void exportarJugadorXml(){

		List<Jugador> jugadores = Util.db.query(Jugador.class);
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		Document documento = null;
		
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			DOMImplementation dom = builder.getDOMImplementation();
			documento = dom.createDocument(null,  "xml", null);
			
			Element raiz = documento.createElement("Jugadores");
			documento.getDocumentElement().appendChild(raiz);
			
			Element nodoEquipo = null, nodoDatos = null;
			
			Text texto = null;
			for (Jugador jugador : jugadores) {
				nodoEquipo = documento.createElement("Jugador");
				raiz.appendChild(nodoEquipo);
				
				nodoDatos = documento.createElement("Nombre");
				nodoEquipo.appendChild(nodoDatos);
				
				texto = documento.createTextNode(jugador.getNombre());
				nodoDatos.appendChild(texto);
				
				nodoDatos = documento.createElement("Apellidos");
				nodoEquipo.appendChild(nodoDatos);
				
				texto = documento.createTextNode(jugador.getApellidos());
				nodoDatos.appendChild(texto);
				
				nodoDatos = documento.createElement("FechaNacimiento");
				nodoEquipo.appendChild(nodoDatos);
				
				texto = documento.createTextNode(String.valueOf(jugador.getFechaNacimiento()));
				nodoDatos.appendChild(texto);
				
				nodoDatos = documento.createElement("Casino");
				nodoEquipo.appendChild(nodoDatos);
				
				texto = documento.createTextNode(jugador.getCasino().getNombre());
				nodoDatos.appendChild(texto);
				
				nodoDatos = documento.createElement("Juego");
				nodoEquipo.appendChild(nodoDatos);
				
				texto = documento.createTextNode(jugador.getJuego().getNombre());
				nodoDatos.appendChild(texto);
			}
			
			Source source = new DOMSource(documento);
			Result resultado = new StreamResult(new File("Jugadores.xml"));
			
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.transform(source, resultado);
			
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerConfigurationException tce) {
			tce.printStackTrace();
		} catch (TransformerException te) {
			te.printStackTrace();
		}
		
	}
	
	
	/**
	 * Initialize the contents of the frame.
	 */

	private void initialize() {
		frmPractica = new JFrame();
		frmPractica.setTitle("Practica 4.1");
		frmPractica.setBounds(100, 100, 800, 600);
		frmPractica.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPractica.setLocationRelativeTo(null);
		
		menuBar = new JMenuBar();
		frmPractica.setJMenuBar(menuBar);
		
		mnMenu = new JMenu("Archivo ");
		menuBar.add(mnMenu);
		
		mntmConectar = new JMenuItem("Conectar");
		mntmConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				conectar();
			}
		});
		mnMenu.add(mntmConectar);
		
		mntmDesconectar = new JMenuItem("Desconectar");
		mntmDesconectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				desconectar();
			}
		});
		
		mntmExportJugador = new JMenuItem("Exportar Jugador XML");
		mntmExportJugador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				exportarJugadorXml();
			}
		});
		mnMenu.add(mntmExportJugador);
		mnMenu.add(mntmDesconectar);
		
		herramientas = new JToolBar();
		frmPractica.getContentPane().add(herramientas, BorderLayout.NORTH);
		
		btAlta = new JButton("Alta");
		btAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nuevo();
			}
		});
		herramientas.add(btAlta);
		
		btModificar = new JButton("Modificar");
		btModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modificar();
			}
		});
		herramientas.add(btModificar);
		
		btEliminar = new JButton("Eliminar");
		btEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminar();
			}
		});
		herramientas.add(btEliminar);
		
		tfFiltro = new JTextField();
		herramientas.add(tfFiltro);
		tfFiltro.setColumns(10);
		
		cbCampos = new JComboBox<String>();
		cbCampos.setPreferredSize(new Dimension(100, 20));

		herramientas.add(cbCampos);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscar();
			}
		});
		herramientas.add(btnBuscar);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				cambiarPestana();
			}
		});
		frmPractica.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		panel_casino = new JPanel();
		tabbedPane.addTab("Casino", null, panel_casino, "");
		panel_casino.setLayout(new BorderLayout(0, 0));
		
		scroll_casino = new JScrollPane();
		panel_casino.add(scroll_casino, BorderLayout.CENTER);
		
		tablaCasino = new TablaCasino();
		scroll_casino.setViewportView(tablaCasino);
		
		panel_ciudad = new JPanel();
		tabbedPane.addTab("Ciudad", null, panel_ciudad, null);
		panel_ciudad.setLayout(new BorderLayout(0, 0));
		
		scroll_ciudad = new JScrollPane();
		panel_ciudad.add(scroll_ciudad, BorderLayout.CENTER);
		
		tablaCiudad = new TablaCiudad();
		scroll_ciudad.setViewportView(tablaCiudad);
		
		panel_juego = new JPanel();
		tabbedPane.addTab("Juego", null, panel_juego, null);
		panel_juego.setLayout(new BorderLayout(0, 0));
		
		scroll_juego = new JScrollPane();
		panel_juego.add(scroll_juego, BorderLayout.CENTER);
		
		tablaJuego = new TablaJuego();
		scroll_juego.setViewportView(tablaJuego);
		
		panel_jugador = new JPanel();
		tabbedPane.addTab("Jugador", null, panel_jugador, null);
		panel_jugador.setLayout(new BorderLayout(0, 0));
		
		scroll_jugador = new JScrollPane();
		panel_jugador.add(scroll_jugador, BorderLayout.CENTER);
		
		tablaJugador = new TablaJugador();
		scroll_jugador.setViewportView(tablaJugador);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana window = new Ventana();
					window.frmPractica.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
