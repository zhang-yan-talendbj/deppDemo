package foo.domain;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;

public abstract class AbstractTestStub {

	protected EntityManager em = null;

	protected AbstractTestStub() {
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