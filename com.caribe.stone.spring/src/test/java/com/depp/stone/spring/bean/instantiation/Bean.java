<<<<<<< HEAD
package com.depp.stone.spring.bean.instantiation;



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
}

class Foo {
}
=======
package com.depp.stone.spring.bean.instantiation;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


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

}

class Foo {
}
>>>>>>> d64a92cd44c1cd1279e7c9921940f7cb9d860b60
