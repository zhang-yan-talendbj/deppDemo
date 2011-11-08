<<<<<<< HEAD
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
	public static EntityManagerFactory factory = null;

	@BeforeClass
	public static void init() {
		factory = Persistence.createEntityManagerFactory("h2_jpa");
	}

	@AfterClass
	public static void destroy() {
		factory.close();
	}

	public AbstractTest() {
		super();
	}

	@Before
	public void setUp() {
		EntityManager em = factory.createEntityManager();
	}

	@After
	public void tearDown() {
		em.close();
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
	public static EntityManagerFactory factory = null;

	@BeforeClass
	public static void init() {
		factory = Persistence.createEntityManagerFactory("h2_jpa");
	}

	@AfterClass
	public static void destroy() {
		factory.close();
	}

	public AbstractTest() {
		super();
	}

	@Before
	public void setUp() {
		EntityManager em = factory.createEntityManager();
	}

	@After
	public void tearDown() {
		em.close();
	}

>>>>>>> d64a92cd44c1cd1279e7c9921940f7cb9d860b60
}