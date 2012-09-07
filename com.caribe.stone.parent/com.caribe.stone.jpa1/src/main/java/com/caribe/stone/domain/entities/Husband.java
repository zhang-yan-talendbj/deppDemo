package com.caribe.stone.domain.entities;

import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class Husband  {
	private String name;
	private Set<Wife> wifes;

	@Basic(fetch = FetchType.LAZY)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(mappedBy = "husband")
	public Set<Wife> getWifes() {
		return wifes;
	}

	public void setWifes(Set<Wife> wifes) {
		this.wifes = wifes;
	}
}
