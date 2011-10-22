package foo.domain;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.core.io.ClassPathResource;

public class EntityManagerFactoryUtils {

	private static EntityManagerFactory factory;
	static {
		createFactory(null);
	}

	private static void createFactory(Map map) {
		Properties p = null;
		try {
			p=new Properties();
			p.load(new ClassPathResource("db2.properties").getInputStream());
			if(map!=null){
				p.putAll(map);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		factory = Persistence.createEntityManagerFactory("jpa", p);
	}

	public static EntityManager getDB2EntityManager() {
		return factory.createEntityManager();
	}
	public static EntityManager getDB2EntityManager(Map map) {
		createFactory(map);
		return factory.createEntityManager();
	}

	public static void closeEntityManager(EntityManager em) {
		if (em != null) {
			if (em.isOpen()) {
				em.close();
			}
			em = null;
		}
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		//create table
		System.out.println(EntityManagerFactoryUtils.getDB2EntityManager());
	}
}
