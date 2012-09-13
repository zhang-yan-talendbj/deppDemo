package com.arcmind.contact.model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class ContactRepository {
	private Map<Long, Contact> contacts = new LinkedHashMap<Long, Contact>(); 
	private static long counter = 1l;

	public List<Contact> list() {
		return new ArrayList<Contact>(contacts.values());
	}

	public synchronized Contact persist(Contact contact) {
		if (contact.id == 0) {
			contact.id = counter++;
		}
		return contacts.put(contact.id, contact);
	}

	public synchronized void remove(Contact contact) {
		contacts.remove(contact.id);
	}
	
}
