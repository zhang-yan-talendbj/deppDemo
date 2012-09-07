package com.caribe.stone.jpa2;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import com.caribe.stone.jpa2.domain.entities.AccessEntity;

public class AccessEntityTest {

	@Test
	public void access() throws Exception {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("accessUnit");
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
}
