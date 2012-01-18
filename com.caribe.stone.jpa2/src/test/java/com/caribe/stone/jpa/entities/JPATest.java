package com.caribe.stone.jpa.entities;

import static org.junit.Assert.*;

import org.apache.openjpa.persistence.OpenJPAEntityManager;
import org.apache.openjpa.persistence.OpenJPAEntityManagerFactory;
import org.apache.openjpa.persistence.OpenJPAPersistence;
import org.junit.Test;

/**
 * -javaagent:"openjpa-all-2.1.1.jar"
 * 
 * @author db2admin
 * 
 */
public class JPATest {
	@Test
	public void simple() throws Exception {
		OpenJPAEntityManagerFactory emf = OpenJPAPersistence.createEntityManagerFactory("simpleUnit",
				"JPA-CONFIG/simple.xml");
		OpenJPAEntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		SimpleEntity simple = new SimpleEntity();
		simple.setName("simple");
		em.persist(simple);
		em.getTransaction().commit();

		assertNotNull(simple.getId());
		SimpleEntity s = em.find(SimpleEntity.class, simple.getId());
		assertEquals("simple", s.getName());
	}
}
