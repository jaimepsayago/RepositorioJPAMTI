package jpa.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import jpa.entities.Mensaje;

public class BuscarMensaje {

	
	// jpql = sql pero para objetos
	
			//consulta de busqueda de mensajes con parametros
			private static final String QUERY_BUSCA_MESAJES = 
					"Select m from Mensaje m where m.texto like :patron ";
			
	public static void main(String[] args) {
		
		//factoria de entities
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mensajes");
		//entity manager
		EntityManager em = emf.createEntityManager();
		
		//no necesito transaccionalidad
		System.out.println("buscando los mensajes");
		String palabra = leerTexto("introduce una palabra: ");
		
		//palabra para reemplazar en la consulta /query
		String patron = "%" + palabra + "%";
		
		//consulta con el parametro patron
		Query query = em.createQuery(QUERY_BUSCA_MESAJES);
		query.setParameter("patron", patron);
		
		//listado de la consulta realizada
		List<Mensaje> mensajes = query.getResultList();
			if(mensajes.isEmpty()) {
				System.out.println("no existen datos en mensajes");
			}else {
				for (Mensaje mensaje : mensajes) {
					System.out.println(mensaje.getTexto()+ "--"+ mensaje.getAutor().getNombre());
				}
			}
		
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
