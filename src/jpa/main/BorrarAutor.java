package jpa.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import jpa.entities.Autor;

public class BorrarAutor {

	public static void main(String[] args) {
		//factoria de entities
				EntityManagerFactory emf = Persistence.createEntityManagerFactory("mensajes");
				//entity manager
				EntityManager em = emf.createEntityManager();

				//SIEMRE DEBE SER TRANSCCIONAL
				em.getTransaction().begin();
				
				//introducir los datos a eliminar
				System.out.println("eliminando autor");
				String palabra = leerTexto("introduce el id del autor: ");
				Long id = new Long(palabra);
				//busqueda
				Autor autor = em.find(Autor.class,id); 
				
				//eliminacion 
				em.remove(autor);
				
				//cierro la transaccionalidad
				em.getTransaction().commit();
				
				em.close();
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
