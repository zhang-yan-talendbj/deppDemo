package org.com.caribe.stone.jpa1;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import com.caribe.stone.domain.entities.IdentityEntityWithTableGenerator;

public class IdentityTest {

	@Test
	public void tableGenerator() throws Exception {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("identityUnit");
		EntityManager em = emf.createEntityManager();
		
		IdentityEntityWithTableGenerator e = new IdentityEntityWithTableGenerator();
		e.setName("IdentityEntityWithTableGenerator");
		em.getTransaction().begin();
		em.persist(e);
		em.getTransaction().commit();
		assertNotNull(e.getId());
	}
}
