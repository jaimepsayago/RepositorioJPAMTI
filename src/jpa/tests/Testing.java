package jpa.tests;

import static org.junit.Assert.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

public class Testing {
	
	//test para persistencia de datos
	@Test
	public void createEntityManagerTest() {
		//creando objetos de persistencia
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mensajes");
		EntityManager em = emf.createEntityManager();
		
		//prueba
		assertNotNull(em);
		
		//cerrar la persistencia
		em.close();
		
	}
	

}
