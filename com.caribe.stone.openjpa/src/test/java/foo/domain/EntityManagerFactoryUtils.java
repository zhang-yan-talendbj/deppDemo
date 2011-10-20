package foo.domain;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.core.io.ClassPathResource;

public class EntityManagerFactoryUtils {

	private static EntityManagerFactory factory;
	static {
		
		Properties p=new Properties();
		try {
			p.load(new ClassPathResource("h2.properties").getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		factory = Persistence.createEntityManagerFactory("h2_jpa", p);
	}

	public static EntityManager getEntityManager() {
		return factory.createEntityManager();
	}

	public static void closeEntityManager(EntityManager em) {
		if (em != null) {
			if (em.isOpen()) {
				em.close();
			}
		}
	}

	public static void main(String[] args) {
		//create table
		System.out.println(EntityManagerFactoryUtils.getEntityManager());
	}
}
