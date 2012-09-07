package org.com.caribe.stone;

import static junit.framework.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;

public class Hellodefs {

	private String name;

	@Given("^a new String \"([^\"]*)\"$")
	public void a_new_String(String name) throws Throwable {
		this.name = name;
	}

	@When("^I call hello method$")
	public void I_call_hello_method() throws Throwable {
		hello();
	}

	private void hello() {
		name = "Hello World, " + name;
	}

	@Then("^I get a String \"([^\"]*)\"$")
	public void I_get_a_String(String arg1) throws Throwable {
		assertEquals(arg1, name);
	}

}
