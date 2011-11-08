<<<<<<< HEAD
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

=======
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
		em=EntityManagerFactoryUtils.getEntityManager();
	}

	@After
	public void tearDown() {
		EntityManagerFactoryUtils.closeEntityManager(em);
	}

>>>>>>> d64a92cd44c1cd1279e7c9921940f7cb9d860b60
}