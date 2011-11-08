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
	private static Properties p = null;

	private static void createFactory(Map map) {
		try {
			p = new Properties();
			p.load(new ClassPathResource("db2.properties").getInputStream());
			if (map != null) {
				p.putAll(map);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		factory = Persistence.createEntityManagerFactory("jpa", p);
	}

	public static EntityManager getDB2EntityManager() {
		try {
			p = new Properties();
			p.load(new ClassPathResource("db2.properties").getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		factory = Persistence.createEntityManagerFactory("jpa", p);

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

	public static EntityManager getH2EntityManager() {
		try {
			p = new Properties();
			p.load(new ClassPathResource("h2.properties").getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		factory = Persistence.createEntityManagerFactory("jpa", p);
		return factory.createEntityManager();
	}

	public static EntityManager getEntityManager() throws Exception {
		ClassPathResource properties = new ClassPathResource("bruce.properties");
		Properties profile = new Properties();
		profile.load(properties.getInputStream());
		String dbName = profile.getProperty("db");
		if ("h2".equals(dbName)) {
			p = new Properties();
			p.load(new ClassPathResource("h2.properties").getInputStream());
			factory = Persistence.createEntityManagerFactory("jpa", p);
		}else if("db2".equals(dbName)){
			p = new Properties();
			p.load(new ClassPathResource("db2.properties").getInputStream());
			factory = Persistence.createEntityManagerFactory("jpa", p);
		}else{
			throw new Exception("No DB config.");
		}
		return factory.createEntityManager();
	}
}
