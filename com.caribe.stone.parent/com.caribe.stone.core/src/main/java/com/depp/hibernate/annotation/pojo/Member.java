package com.depp.hibernate.annotation.pojo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Member {

	private Integer id;
	private String title;
	private Date birthday;

	public Member() {
	}

	public Date getBirthday() {
		return birthday;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// @GeneratedValue(strategy = GenerationType.SEQUENCE)
	// @GeneratedValue(generator = "system-uuid")
	// @GenericGenerator(name = "system-uuid", strategy = "uuid")
	public Integer getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}