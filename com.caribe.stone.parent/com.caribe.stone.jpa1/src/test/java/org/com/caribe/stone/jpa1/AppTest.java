package org.com.caribe.stone.jpa1;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import com.caribe.stone.domain.entities.Message;

/**
 * Unit test for simple App.
 */
public class AppTest {

	@Test
	public void testName() throws Exception {
		
		assertEquals(1, 1);
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("teaUnit");
		EntityManager em = emf.createEntityManager();
		
		
		em.getTransaction().begin();
		Message entity = new Message();
		em.persist(entity);
		em.getTransaction().commit();
		
	}
}
