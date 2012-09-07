package com.caribe.stone.jpa2.domain.entities;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.caribe.stone.domain.entities.IdEntity;

@Entity
@Access(AccessType.FIELD)
public class AccessEntity extends IdEntity {
	private static final String PHONE_PREFIX = "+86";
	private String field;
	@Transient
	private String phone;

	public String getField() {
		return field;
	}

	public AccessEntity() {
		super();
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
