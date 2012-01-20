package com.caribe.stone.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SECOND_ENTITY")
public class SecondEntity {

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
