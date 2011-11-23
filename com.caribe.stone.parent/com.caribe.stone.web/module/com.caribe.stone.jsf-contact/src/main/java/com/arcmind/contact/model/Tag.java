package com.arcmind.contact.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Tag implements Serializable {

	protected long id;
	private String name;
	
	
	public Tag () {
	}
	
	public Tag(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

}
