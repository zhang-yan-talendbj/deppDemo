package foo.domain;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

<<<<<<< HEAD
=======
import org.junit.Test;

>>>>>>> 5233cf07bab044b6b2d6fda7befca71d17204def
import junit.framework.TestCase;
import model.Address;
import model.Person;

<<<<<<< HEAD
public class PersonTest extends TestCase {

	public void testSave() {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("h2_jpa");
		EntityManager em = factory.createEntityManager();
=======
public class PersonTest extends AbstractTest {
	@Test
	public void testSave() {
>>>>>>> 5233cf07bab044b6b2d6fda7befca71d17204def
		em.getTransaction().begin();
		Person p = new Person();
		p.setName("林忠青");
		p.setBirthday(new Date());
		em.persist(p);
		em.getTransaction().commit();
<<<<<<< HEAD
=======
	}

	public void tireDown() {
>>>>>>> 5233cf07bab044b6b2d6fda7befca71d17204def
		em.close();
		factory.close();
	}

	public void testFind() {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("h2_jpa");
		EntityManager em = factory.createEntityManager();
		String jpql = "select count(p.id) from Person as p where 1=1 and p.name like ?1";
<<<<<<< HEAD
		System.out.println(em.createQuery(jpql).setParameter(1, "%林%").getSingleResult());
		em.close();
		factory.close();
	}
=======
		System.out.println(em.createQuery(jpql).setParameter(1, "%林%")
				.getSingleResult());
		em.close();
		factory.close();
	}

>>>>>>> 5233cf07bab044b6b2d6fda7befca71d17204def
	public void testNamedQuery() {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("h2_jpa");
		EntityManager em = factory.createEntityManager();
		System.out.println(em.createNamedQuery("findAll").getResultList());
		em.close();
		factory.close();
	}
<<<<<<< HEAD
	public void testAddress(){
=======

	public void testAddress() {
>>>>>>> 5233cf07bab044b6b2d6fda7befca71d17204def
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("h2_jpa");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
<<<<<<< HEAD
		Address adr=new Address();
=======
		Address adr = new Address();
>>>>>>> 5233cf07bab044b6b2d6fda7befca71d17204def
		adr.setName("beijing");
		em.persist(adr);
		em.getTransaction().commit();
		em.close();
		factory.close();
<<<<<<< HEAD
		
=======
>>>>>>> 5233cf07bab044b6b2d6fda7befca71d17204def
	}
}
