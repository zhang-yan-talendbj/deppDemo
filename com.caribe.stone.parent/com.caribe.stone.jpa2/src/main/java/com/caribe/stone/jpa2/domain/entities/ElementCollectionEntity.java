package com.caribe.stone.jpa2.domain.entities;

import java.util.Collection;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;

import com.caribe.stone.domain.entities.IdEntity;

@Entity
public class ElementCollectionEntity extends IdEntity {
	private String name;

	public String getName() {
		return name;
	}

	public ElementCollectionEntity() {
	}

	public ElementCollectionEntity(String name) {
		this.name = name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private Collection elements;

	private Set<String> nickNames;

	@ElementCollection(targetClass = VacationEntry.class)
	public Collection getElements() {
		return elements;
	}

	public void setElements(Collection elements) {
		this.elements = elements;
	}

	@ElementCollection
	public Set<String> getNickNames() {
		return nickNames;
	}

	public void setNickNames(Set<String> nickNames) {
		this.nickNames = nickNames;
	}

}
