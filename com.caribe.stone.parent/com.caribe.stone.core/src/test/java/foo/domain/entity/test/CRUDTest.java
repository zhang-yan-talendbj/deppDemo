package foo.domain.entity.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import org.junit.Test;

import foo.domain.AbstractTest;
import foo.domain.entity.Address;
import foo.domain.entity.Customer;
import foo.domain.entity.one2Many.Box;
import foo.domain.entity.one2Many.Stuff;

public class CRUDTest extends AbstractTest{

	@Test
	public void testCreate() throws Exception {
		Address entity = getAddress();
		em.getTransaction().begin();
		em.persist(entity);
		em.getTransaction().commit();
	}

	private Address getAddress() {
		Address entity = new Address();
		entity.setName("Room 502");
		return entity;
	}
	
	@Test
	public void testCascade() throws Exception {
		Customer c=new Customer();
		c.setName("bruce");
		c.setAddress(getAddress());
		em.getTransaction().begin();
		em.persist(c);
			
		em.getTransaction().commit();
	}
	@Test
	public void testOne2Many() throws Exception {
		Box b=new Box();
		b.setBoxNumber(100L);
		b.setSize(10);
		
		Collection<Stuff> list=new ArrayList<Stuff>(3);
		list.add(getStuff(b));
		list.add(getStuff(b));
		list.add(getStuff(b));
		b.setStuff(list);
		
		em.getTransaction().begin();
		em.persist(b);
//		for (Stuff stuff : list) {
//			em.persist(stuff);
//		}
		em.getTransaction().commit();
	}

	private Stuff getStuff(Box b) {
		Stuff stuff=new Stuff();
		stuff.setName("openJpa"+new Random().nextInt(9999));
		stuff.setBox(b);
		System.out.println(stuff.getName());
		return stuff;
	}
}
