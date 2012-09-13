package com.caribe.stone.domain.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MessageNote {
	private Long id;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private String message;

	private Date created = new Date();

	public MessageNote() {
	}

	public MessageNote(String msg) {
		message = msg;
	}

	public void setMessage(String msg) {
		message = msg;
	}

	public String getMessage() {
		return message;
	}

	public void setCreated(Date date) {
		created = date;
	}

	public Date getCreated() {
		return created;
	}
}
