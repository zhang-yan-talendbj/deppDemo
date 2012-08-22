package foo.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

import foo.domain.entity.base.IdEntity;

@Entity
public class Address extends IdEntity implements Serializable {

	public Address() {
	}

	@Column
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
