package com.arcmind.contact.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ContactRepositoryTest {

	private ContactRepository repo = new ContactRepository(); 
	private Contact c1= new Contact("r", "r");
	private Contact c2= new Contact("r", "r");
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testRemoveContact() {
		repo.persist(c1);
		repo.remove(c2);
		assertEquals(0, repo.list().size());
	}

}
