package jpa.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import jpa.entities.Autor;
import jpa.entities.Mensaje;

public class NuevoAutorMensaje {

	public static void main(String[] args) {

		//agregar la tabla/entidad
		Autor autor;
		
		//crear la factoria de entities managers y un entity principal
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mensajes");
		EntityManager em = emf.createEntityManager();
		
		//SIEMRE DEBE SER TRANSCCIONAL
		em.getTransaction().begin();
		
		//PEDIR LOS DATOS DEL AUTOR
		String email = leerTexto("introduce el correo");
		String nombre = leerTexto("introduce nombre");
		autor = new Autor(nombre,email);
		
		//Agregar a la base de datos el autor
		em.persist(autor);
		
		//crear datos del mensaje
		String mensajeStr = leerTexto("introduce un mensaje: ");
		Mensaje mens = new Mensaje(mensajeStr,autor);
		
		//establecer los campos
		mens.setFecha(new Date());
		
		//guardamos en la bae de datos
		em.persist(mens);
		
		
		
		//cerrar la transaccion
		em.getTransaction().commit(); 
		//cerrar mi entitymanager
		em.close();
		//cerrar mi factoria de entities
		emf.close();
		

	}
	public static String leerTexto(String mensaje) {
		String texto;
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in) );
			System.out.print(mensaje);
			texto = in.readLine();
			
		} catch (IOException e) {
			texto = "error";
		}
		return texto;
	}

}
