package org.com.caribe.stone.jpa1;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import com.caribe.stone.domain.entities.Message;

public class CRUDTest {

	@Test
	public void CRUD() throws Exception {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("crudUnit");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Message m = new Message();
		m.setMessage("msg");
		em.persist(m);
		em.getTransaction().commit();
		Message m2 = getMessage(em, m);
		assertEquals("msg", m2.getMessage());
		
		m2.setMessage("updateMsg");
		em.getTransaction().begin();
		em.merge(m2);
		em.getTransaction().commit();
		
		Message m3 = getMessage(em, m);
		assertEquals("updateMsg", m3.getMessage());
		
		em.getTransaction().begin();
		em.remove(m3);
		em.getTransaction().commit();
		
		Message m4 = getMessage(em, m);
		assertNull(m4);
	}

	public Message getMessage(EntityManager em, Message m) {
		Message m2 = em.find(Message.class, m.getId());
		return m2;
	}
}
