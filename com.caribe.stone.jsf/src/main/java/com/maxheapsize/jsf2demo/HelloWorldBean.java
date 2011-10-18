package com.maxheapsize.jsf2demo;

import javax.faces.bean.*;
import java.io.Serializable;

@ManagedBean
@SessionScoped
public class HelloWorldBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4347410681071118015L;

	private String name = "hello";

	@ManagedProperty(value = "#{demoService}")
	private Service service;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public String getReverseName() {
		return service.reverse(name);
	}
}