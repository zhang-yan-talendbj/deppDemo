package foo.domain;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;

import org.junit.Test;

public class EntityManagerFactoryUtilsTest {

	@Test
	public void testGetDB2EntityManager() {
	assertNotNull(EntityManagerFactoryUtils.getEntityManager());
	}
	

	@Test
	public void testCloseEntityManager() {
		final EntityManager em = EntityManagerFactoryUtils.getEntityManager();
		assertNotNull(em);
		EntityManagerFactoryUtils.closeEntityManager(em);
		assertTrue(!em.isOpen());
	}

}
