package foo.domain;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

public class AbstractTest {

	protected EntityManager em = null;

	protected AbstractTest() {
	}

	@Before
	public void setUp() {
		em=EntityManagerFactoryUtils.getDB2EntityManager();
	}

	@After
	public void tearDown() {
		EntityManagerFactoryUtils.closeEntityManager(em);
	}

}