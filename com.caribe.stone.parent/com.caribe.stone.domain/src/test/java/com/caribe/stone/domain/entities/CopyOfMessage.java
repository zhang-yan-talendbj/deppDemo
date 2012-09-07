package com.caribe.stone.domain.entities;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
// comment this @entity or  spring will load this class Message why???
public class CopyOfMessage {
	@Id
	@GeneratedValue
	private Long id ;

	@Basic
	private String message;

	@Basic
	private Date created = new Date();

	public CopyOfMessage() {
	}

	public CopyOfMessage(String msg) {
		message = msg;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
