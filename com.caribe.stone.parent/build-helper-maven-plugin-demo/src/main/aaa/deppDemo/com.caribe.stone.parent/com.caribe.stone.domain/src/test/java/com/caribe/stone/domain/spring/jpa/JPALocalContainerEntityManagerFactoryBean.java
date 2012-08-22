package com.caribe.stone.domain.spring.jpa;

import static junit.framework.Assert.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.caribe.stone.domain.entities.SimpleEntity;

@ContextConfiguration()
public class JPALocalContainerEntityManagerFactoryBean extends AbstractJUnit4SpringContextTests {

	private EntityManagerFactory emf;

	@PersistenceUnit
	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}

	@PersistenceContext
	private EntityManager entityManager;

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Test
	public void persistenceUnit() throws Exception {
		EntityManager em = emf.createEntityManager();
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
