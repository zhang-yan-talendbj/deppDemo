package com.depp.stone.spring.bean.instantiation;

<<<<<<< HEAD
=======
import static org.junit.Assert.assertEquals;

import org.junit.Test;


>>>>>>> 5233cf07bab044b6b2d6fda7befca71d17204def
public class Bean {
	public Bean() {
	}

	private Foo foo;
	private Bar bar;
	private String property;

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public Bean(Foo foo, Bar bar) {
		super();
		this.foo = foo;
		this.bar = bar;
	}

	public Foo getFoo() {
		return foo;
	}

	public void setFoo(Foo foo) {
		this.foo = foo;
	}

	public Bar getBar() {
		return bar;
	}

	public void setBar(Bar bar) {
		this.bar = bar;
	}
<<<<<<< HEAD
=======

>>>>>>> 5233cf07bab044b6b2d6fda7befca71d17204def
}

class Foo {
}
