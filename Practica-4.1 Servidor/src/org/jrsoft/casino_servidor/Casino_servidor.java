package org.jrsoft.casino_servidor;

import org.jrsoft.casino_servidor.util.Constantes;

import com.db4o.ObjectServer;
import com.db4o.cs.Db4oClientServer;

public class Casino_servidor {

	public static void main(String[] args) {

		new Casino_servidor().iniciarServidor();
	}
	
	public void iniciarServidor() {
		System.out.println("Servidor iniciado");

		
		synchronized (this) {
			final ObjectServer servidor = Db4oClientServer.openServer(Constantes.DB4O_FILENAME, Constantes.PUERTO);
			servidor.grantAccess(Constantes.USUARIO, Constantes.CONTRASENA);
			
			try {
				wait(Long.MAX_VALUE);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
