package com.caribe.stone.wd.hibernate.helloword.hbm;

import static org.junit.Assert.*;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.junit.Test;

public class HelloWorld {

	@Test
	public void xmlHello() throws Exception {
		Configuration configure = new Configuration()
				.configure("com/caribe/stone/wd/hibernate/helloword/hibernate.cfg.xml");
		configure
				.addResource("com/caribe/stone/wd/hibernate/helloword/hbm/Message.hbm.xml");
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

class Message {
	private Long id;
	private String text;
	private Message nextMessage;

	Message() {
	}

	public Message(String text) {
		this.text = text;
	}

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

	public Message getNextMessage() {
		return nextMessage;
	}

	public void setNextMessage(Message nextMessage) {
		this.nextMessage = nextMessage;
	}
}
