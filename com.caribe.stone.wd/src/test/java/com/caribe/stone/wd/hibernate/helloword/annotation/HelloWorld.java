package com.caribe.stone.wd.hibernate.helloword.annotation;

import static org.junit.Assert.assertNotNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.classic.Session;
import org.junit.Test;

public class HelloWorld {

	@Test
	public void xmlHello() throws Exception {
		AnnotationConfiguration configure = new AnnotationConfiguration()
				.configure("com/caribe/stone/wd/hibernate/helloword/hibernate.cfg.xml");
		configure.addAnnotatedClass(Message.class);
		SessionFactory sessionFactory = configure.buildSessionFactory();
		Session session = sessionFactory.openSession();

		Transaction tx = session.beginTransaction();
		Message message = new Message("Hello World");
		session.save(message);
		tx.commit();
		session.close();

		assertNotNull(message.getId());
	}
}

@Entity
class Message {
	private Long id;
	private String text;

	Message() {
	}

	public Message(String text) {
		this.text = text;
	}

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	private void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
