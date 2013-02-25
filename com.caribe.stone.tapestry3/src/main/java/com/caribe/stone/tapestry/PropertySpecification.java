package com.caribe.stone.tapestry;

import org.apache.tapestry.html.BasePage;

public abstract class PropertySpecification extends BasePage {
	public abstract PropertyBean getPropertyBean();
	
	public String getName(){
		return getPropertyBean().getName();
	}
}

class PropertyBean {
	private String name="";

	public String getName() {
		return name+"property name";
	}

	public void setName(String name) {
		this.name = name+"property name";
	}

	public String toString() {
		System.out.println(123);
		return "PropertyBean [name=" + name + "]";
	}
}
