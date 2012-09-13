package com.caribe.stone.domain.entities;

import javax.persistence.Entity;

@Entity
public class IdentityEntityWithTableGenerator extends TableIdEntity {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
