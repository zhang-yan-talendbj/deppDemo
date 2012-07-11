package org.com.caribe.stone.datastructure;

import static junit.framework.Assert.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.com.caribe.stone.datastructure.ArrayListdefs.ListElement;
import org.com.caribe.stone.datastructure.list.DArrayList;

import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;

public class ArrayListdefs {
	private DArrayList list;

	@Given("^a new empty list$")
	public void a_new_empty_list() throws Throwable {
		list = new DArrayList();
	}

	@When("^add a element \"([^\"]*)\"$")
	public void add_a_element(String arg1) throws Throwable {
		list.insert(1, arg1);
	}

	@Then("^the list has (\\d+) element$")
	public void the_list_has_element(int arg1) throws Throwable {
		assertEquals(arg1, list.getSize());
	}

	@When("^add a element \"([^\"]*)\" at index (\\d+)$")
	public void add_a_element_at_index(String arg1, int arg2) throws Throwable {
		list.insert(arg2, arg1);
	}

	@Then("^get element from list index (\\d+) is \"([^\"]*)\"$")
	public void get_element_from_list_index_is(int arg1, String arg2) throws Throwable {
		Object object = list.get(arg1);
		assertEquals(arg2, object);
	}

	@Given("^a new list initialize following element:$")
	public void a_new_list_initialize_following_element(List<ListElement> listElements) throws Throwable {
		list=new DArrayList();
		Collections.sort(listElements,new Comparator<ListElement>() {
			@Override
			public int compare(ListElement o1, ListElement o2) {
				return o1.index-o2.index;
			}
		});
		for (ListElement e : listElements) {
			list.insert(e.index, e.element);
		}
	}

	static class ListElement{
		public String element;
		public int index;
		@Override
		public String toString() {
			return "ListElement [element=" + element + ", index=" + index + "]";
		}
	}
}
