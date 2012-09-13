package com.caribe.stone.domain.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Husband {
	private Long id;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;

	}

	public void setId(Long id) {
		this.id = id;
	}

	private String name;
	private Set<Wife> wifes;

	// @Basic(fetch = FetchType.LAZY)
	public String getName() {
		return name;
	}

	public Husband() {
	}

	public Husband(String name) {
		this.name = name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "husband", fetch = FetchType.EAGER)
	public Set<Wife> getWifes() {
		return wifes;
	}

	public void setWifes(Set<Wife> wifes) {
		this.wifes = wifes;
	}
}
