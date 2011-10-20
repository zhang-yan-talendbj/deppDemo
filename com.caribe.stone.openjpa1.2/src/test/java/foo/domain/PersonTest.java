package foo.domain;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class PersonTest extends AbstractTest {

	@Test
	public void testSave() {
		em.getTransaction().begin();
		Person p = new Person();
		p.setName("林忠青");
		p.setBirthday(new Date());
		em.persist(p);
		em.getTransaction().commit();
		System.out.println(p.getCreatedDate());
	}

	@Test
	public void testAnnotationPrePersist() throws Exception {
		em.getTransaction().begin();
		Person p = getRandomPerson(Person.class);
		em.persist(p);
		em.getTransaction().commit();
		System.out.println(p.getCreatedDate());
	}

	@Test
	public void testName() throws Exception {
		Query q = em.createQuery("select p from Person as p where 1=1 and p.name =:name");
//		q.setParameter(1, "林忠青");
		List list = q.getResultList();
		System.out.println(list.size());
	}
	private <OBJ> OBJ getRandomPerson(Class<?> c) throws Exception {
		@SuppressWarnings("unchecked")
		OBJ obj = (OBJ) c.getInterfaces();
		while (c.getSuperclass() != Object.class) {

			Field[] fields = c.getDeclaredFields();
			for (Field f : fields) {
				f.setAccessible(true);

				final Class<?> type = f.getType();
				if (type.equals(String.class)) {
					f.set(obj, f.getName() + new Random().nextInt(100));
					// } else if (type.equals(Date.class)) {
					// f.set(obj, new Date());
				} else if (type.equals(Long.class)) {
					f.set(obj, new Random().nextLong());
				} else {
					System.out.println(f.getName() + " not setted!");
				}
			}
			c = c.getSuperclass();
		}
		return obj;
	}


	public void testFind() {
	}

	public void testNamedQuery() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("h2_jpa");
		EntityManager em = factory.createEntityManager();
		System.out.println(em.createNamedQuery("findAll").getResultList());
		em.close();
		factory.close();
	}

}
