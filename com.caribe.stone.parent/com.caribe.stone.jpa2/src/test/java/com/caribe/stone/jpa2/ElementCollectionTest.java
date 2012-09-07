package com.caribe.stone.jpa2;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import com.caribe.stone.jpa2.domain.entities.ElementCollectionEntity;

public class ElementCollectionTest {
	@Test
	public void elementCollection() throws Exception {
		ElementCollectionEntity e = new ElementCollectionEntity("ElementCollectionEntity");
		
		Set<String> nickNames = new HashSet<String>();
		nickNames.add("n1");
		nickNames.add("n2");
		nickNames.add("n3");
		e.setNickNames(nickNames);
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("elementCollectionUnit");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(e);
		em.getTransaction().commit();
	}
}
