package com.caribe.stone.domain.entities;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class MessageNote extends IdEntity {
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
