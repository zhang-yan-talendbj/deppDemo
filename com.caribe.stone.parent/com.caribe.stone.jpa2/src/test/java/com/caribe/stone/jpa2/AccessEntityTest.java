package com.caribe.stone.jpa2;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import com.caribe.stone.jpa2.domain.entities.AccessEntity;

public class AccessEntityTest {

	@Test
	public void t2() throws Exception {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("teaUnit");
		EntityManager em = emFactory.createEntityManager();
		AccessEntity entity = new AccessEntity();
		entity.setField("testField");
		//Access
		entity.setPhoneNumberForDb("13011269290");
		em.getTransaction().begin();
		em.persist(entity);
		em.getTransaction().commit();
		
		AccessEntity e2 = em.find(AccessEntity.class, entity.getId());
		assertEquals("testField", e2.getField());
		
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
