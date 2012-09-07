package org.com.caribe.stone.jpa1;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import com.caribe.stone.domain.entities.Husband;
import com.caribe.stone.domain.entities.Wife;

public class Many2OneTest {

	@Test
	public void tableGenerator() throws Exception {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("many2one");
		EntityManager em = emf.createEntityManager();

		Wife w1 = new Wife("one");
		Wife w2 = new Wife("two");
		Husband h = new Husband("king");
		Set<Wife> wifes = new HashSet<Wife>();
		wifes.add(w1);
		wifes.add(w2);
		h.setWifes(wifes);
		em.getTransaction().begin();
		w1.setHusband(h);
		w2.setHusband(h);
		em.persist(h);
		em.persist(w1);
		em.persist(w2);
		em.getTransaction().commit();

		Husband hus = em.find(Husband.class, h.getId());
		assertEquals(2, hus.getWifes().size());
	}
}
