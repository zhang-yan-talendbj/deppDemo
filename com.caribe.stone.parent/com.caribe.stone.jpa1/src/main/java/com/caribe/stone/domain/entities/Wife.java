package com.caribe.stone.domain.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * 我是谁
 * 
 * @author bsnpbag
 * 
 */
@Entity
public class Wife  {
	private String name;

	private Husband husband;

	@ManyToOne()
	@JoinColumn(name="husb_id")
	public Husband getHusband() {
		return husband;
	}

	public void setHusband(Husband husband) {
		this.husband = husband;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
