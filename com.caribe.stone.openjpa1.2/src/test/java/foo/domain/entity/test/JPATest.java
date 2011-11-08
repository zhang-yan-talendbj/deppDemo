package foo.domain.entity.test;

import java.sql.Date;
import java.util.List;

import javax.persistence.Query;

import junit.framework.Assert;

import org.junit.Test;

import foo.domain.AbstractTest;
import foo.domain.entity.one2one.Person;
import foo.domain.entity.one2one.User;

public class JPATest extends AbstractTest {

	@Test
	public void testOne2One() throws Exception {
//		Person p=new Person();
//		p.setName("张岩");
//		p.setBirthday(new Date(1985,8,30));
//		User u=new User();
//		u.setUserName("thinkdeeply");
//		u.setPassword("19850930");
//		u.setPerson(p);
//		em.getTransaction().begin();
//		em.persist(u);
//		em.getTransaction().commit();
		
//		Query query = em.createQuery("select p from Person p where p.name='张岩'");
//		List list = query.getResultList();
//		Assert.assertEquals(1,list.size());
//		em.getTransaction().begin();
//		em.remove(list.get(0));
//		em.getTransaction().commit();
//		List nullList=query.getResultList();
//		Assert.assertTrue(nullList.isEmpty());
	}
}
