package org.com.caribe.stone;

import static junit.framework.Assert.*;

import java.util.ArrayList;
import java.util.List;

import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;

public class MultNumbersdefs {

	private List list;

	@Given("^a new List$")
	public void a_new_List() throws Throwable {
		list = new ArrayList();
	}

	@When("^add (\\d+), (\\d+)$")
	public void add_(int arg1, int arg2) throws Throwable {
		list.add(arg1);
		list.add(arg2);
	}

	@Then("^this list has (\\d+) elements$")
	public void this_list_has_elements(int arg1) throws Throwable {
		assertEquals(arg1, list.size());
	}

}
