package org.com.caribe.stone.jwebunit;

import static net.sourceforge.jwebunit.junit.JWebUnit.*;

import org.junit.Before;
import org.junit.Test;

public class ExampleWebTestCase {

	@Before
	public void prepare() {
		setBaseUrl("http://localhost:8080/com.caribe.stone.jwebunit");
	}

	@Test
	public void test1() {
		beginAt("index.jsp"); // Open the browser on
								// http://localhost:8080/test/home.xhtml
		clickLink("login");
		assertTitleEquals("Login");
		setTextField("username", "test");
		setTextField("password", "test123");
		submit();
		assertTitleEquals("Welcome, test!");
		// run fail
	}
}