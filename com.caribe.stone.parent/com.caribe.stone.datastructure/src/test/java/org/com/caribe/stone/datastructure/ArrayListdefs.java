package org.com.caribe.stone.datastructure;

import static junit.framework.Assert.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.com.caribe.stone.datastructure.ArrayListdefs.ListElement;
import org.com.caribe.stone.datastructure.list.DListArray;

import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;

public class ArrayListdefs {
	private DListArray list;
	private Object removeElement;

	@Given("^a new empty list$")
	public void a_new_empty_list() throws Throwable {
		list = new DListArray();
	}
	
	@Given("^a new list initialize following element:$")
	public void a_new_list_initialize_following_element(List<ListElement> listElements) throws Throwable {
		list=new DListArray();
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

	@When("^add a element \"([^\"]*)\" at index (\\d+)$")
	public void add_a_element_at_index(String element, int index) throws Throwable {
		list.insert(index, element);
	}
	
	@When("^remove a element at index (\\d+)$")
	public void remove_a_element_at_index(int index) throws Throwable {
		removeElement=list.remove(index);
	}
	
	@Then("^we get remove element \"([^\"]*)\"$")
	public void we_get_remove_element(String expeElement) throws Throwable {
		assertEquals(removeElement, expeElement);
	}

	@Then("^get element from list index (\\d+) is \"([^\"]*)\"$")
	public void get_element_from_list_index_is(int index, String expeElement) throws Throwable {
		Object object = list.get(index);
		assertEquals(expeElement, object);
	}


	
	@Then("^the list has (\\d+) element$")
	public void the_list_has_element(int size) throws Throwable {
		assertEquals(size, list.size());
	}
	
	@Then("^index of element \"([^\"]*)\" is (\\d+)$")
	public void index_of_element_is(String element, int index) throws Throwable {
		assertEquals(index, list.indexOf(element));;
	}
	
	@Given("^new empty list$")
	public void new_empty_list() throws Throwable {
		list=new DListArray();
	}

	@Then("^has a error$")
	public void has_a_error() throws Throwable {
	    // Express the Regexp above with the code you wish you had
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
