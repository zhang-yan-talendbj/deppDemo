package com.caribe.stone.jpa2;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Before;
import org.junit.Test;

import com.caribe.stone.domain.entities.MessageNote;

public class CRUDTest {
	private EntityManager em;
	private EntityManagerFactory emf;

	@Test
	public void testName() throws Exception {

		MessageNote message = saveMessage();
		MessageNote m2 = em.find(MessageNote.class, message.getId());
		assertEquals("msg", m2.getMessage());
		updateMessage(em, m2);
		assertEquals("updateMsg", m2.getMessage());
		removeMessage(em, m2);

		MessageNote nullMessage = em.find(MessageNote.class, m2.getId());
		assertNull(nullMessage);
	}

	@Before
	public void setup() {
		emf = Persistence.createEntityManagerFactory("teaUnit");
		em = emf.createEntityManager();
	}

	public void removeMessage(EntityManager em, MessageNote m2) {
		em.getTransaction().begin();
		em.remove(m2);
		em.getTransaction().commit();
	}

	public void updateMessage(EntityManager em, MessageNote m2) {
		em.getTransaction().begin();
		m2.setMessage("updateMsg");
		em.getTransaction().commit();
	}

	public MessageNote saveMessage() {
		em.getTransaction().begin();
		MessageNote message = new MessageNote();
		message.setMessage("msg");
		em.persist(message);
		em.getTransaction().commit();
		return message;
	}

	@Test
	public void testFetch() throws Exception {
		MessageNote m = new MessageNote();
		m.setMessage("tooMany");

		em.getTransaction().begin();
		em.persist(m);
		em.getTransaction().commit();

		MessageNote m2 = em.find(MessageNote.class, m.getId());
		assertEquals("tooMany", m2.getMessage());
	}

	// @Test
	// public void t22() throws Exception {
	// EntityManagerFactory emf =
	// Persistence.createEntityManagerFactory("teaUnit");
	// EntityManager em = emf.createEntityManager();
	// em.getTransaction().begin();
	// Husband h = new Husband();
	// h.setName("bruce");
	// Wife w = new Wife();
	// w.setName("story");
	// w.setHusband(h);
	// em.persist(h);
	// em.persist(w);
	// em.getTransaction().commit();
	// }
}
