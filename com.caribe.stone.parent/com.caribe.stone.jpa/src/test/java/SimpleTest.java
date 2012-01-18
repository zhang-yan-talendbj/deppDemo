import org.apache.openjpa.persistence.OpenJPAEntityManager;
import org.apache.openjpa.persistence.OpenJPAEntityManagerFactory;
import org.apache.openjpa.persistence.OpenJPAPersistence;
import org.junit.Test;

import com.caribe.stone.jpa.entities.SimpleEntity;


public class SimpleTest {

	@Test
	public void test() {
		OpenJPAEntityManagerFactory mf = OpenJPAPersistence.createEntityManagerFactory("simpleUnit", "jpa/simpleJPA.xml");
		OpenJPAEntityManager em = mf.createEntityManager();
		SimpleEntity se=new SimpleEntity();
		se.setName("simple");
		em.getTransaction().begin();
		em.persist(se);
		em.getTransaction().commit();
	}

}
