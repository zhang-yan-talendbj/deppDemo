/*package foo.domain;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;

import org.junit.Test;

public class EntityManagerFactoryUtilsTest {

	@Test
	public void testGetDB2EntityManager() throws Exception {
	assertNotNull(EntityManagerFactoryUtils.getEntityManager());
	}
	

	@Test
	public void testCloseEntityManager() throws Exception {
		final EntityManager em = EntityManagerFactoryUtils.getEntityManager();
		assertNotNull(em);
		EntityManagerFactoryUtils.closeEntityManager(em);
		assertTrue(!em.isOpen());
	}

}
*/