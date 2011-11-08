<<<<<<< HEAD
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
=======
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
>>>>>>> d64a92cd44c1cd1279e7c9921940f7cb9d860b60
}