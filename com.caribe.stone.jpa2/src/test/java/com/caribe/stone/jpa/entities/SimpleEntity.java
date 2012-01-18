package com.caribe.stone.jpa.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SIMPLE_ENTITY")
public class SimpleEntity {

	private Long id;
	private String name;

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@Column(length=10)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
