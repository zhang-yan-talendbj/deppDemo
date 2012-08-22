package com.arcmind.contact.model;

public enum ContactType {
	BUSINESS, PERSONAL;
	
	@Override
	public String toString () {
		return this.name().toLowerCase();
	}
}
