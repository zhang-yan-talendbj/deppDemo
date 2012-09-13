package com.caribe.stone.jpa2.domain.entities;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
@Access(AccessType.FIELD)
public class AccessEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private static final String PHONE_PREFIX = "+86";
	private String field;
	@Transient
	private String phone;

	public String getField() {
		return field;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Access(AccessType.PROPERTY)
	@Column(name = "phone")
	public String getPhoneNumberForDb() {
		return PHONE_PREFIX + phone;
	}

	public void setField(String field) {
		this.field = field;
	}

	public void setPhoneNumberForDb(String string) {
		this.phone = string;
	}
}
