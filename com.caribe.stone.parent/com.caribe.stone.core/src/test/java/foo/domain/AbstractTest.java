package foo.domain;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;

public class AbstractTest {

	protected EntityManager em = null;

	protected AbstractTest() {
	}

	@Before
	public void setUp() throws Exception {
		em=EntityManagerFactoryUtils.getEntityManager();
	}

	@After
	public void tearDown() {
		EntityManagerFactoryUtils.closeEntityManager(em);
	}

}