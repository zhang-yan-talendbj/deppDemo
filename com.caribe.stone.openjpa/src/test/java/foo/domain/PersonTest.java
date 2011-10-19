package foo.domain;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import junit.framework.TestCase;
import model.Address;
import model.Person;

public class PersonTest extends TestCase {

	public void testSave() {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("h2_jpa");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Person p = new Person();
		p.setName("林忠青");
		p.setBirthday(new Date());
		em.persist(p);
		em.getTransaction().commit();
		em.close();
		factory.close();
	}

	public void testFind() {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("h2_jpa");
		EntityManager em = factory.createEntityManager();
		String jpql = "select count(p.id) from Person as p where 1=1 and p.name like ?1";
		System.out.println(em.createQuery(jpql).setParameter(1, "%林%").getSingleResult());
		em.close();
		factory.close();
	}
	public void testNamedQuery() {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("h2_jpa");
		EntityManager em = factory.createEntityManager();
		System.out.println(em.createNamedQuery("findAll").getResultList());
		em.close();
		factory.close();
	}
	public void testAddress(){
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("h2_jpa");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Address adr=new Address();
		adr.setName("beijing");
		em.persist(adr);
		em.getTransaction().commit();
		em.close();
		factory.close();
		
	}
}
