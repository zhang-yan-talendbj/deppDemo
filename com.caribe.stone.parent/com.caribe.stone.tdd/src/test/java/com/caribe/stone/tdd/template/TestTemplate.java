package com.caribe.stone.tdd.template;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestTemplate {
	private Template t;
	private String expected;

	@Before
	public void setUp() {
		t = new Template("#{one},#{two},#{three}");
		t.set("one", "一");
		t.set("two", "二");
		t.set("three", "三");
	}

	@Test
	public void multipleVariables() throws Exception {
		assertTemplateEvaluatesTo("一,二,三");
	}

	public void assertTemplateEvaluatesTo(String expected) {
		assertEquals(expected, t.evaluate());
	}

	@Test
	public void unknownVariablesAreIgnored() throws Exception {
		t.set("doesnotexist", "whatever");
		expected = "一,二,三";
		assertTemplateEvaluatesTo(expected);
	}

	@Test
	public void missingValueRaisesException() throws Exception {
		try {
			new Template("#{foo}").evaluate();
			fail("evaluate() should throw an exception if a variable was left without a value!");
		} catch (MissingValueException expected) {
			assertEquals("No value for #{foo}", expected.getMessage());
		}
	}

	@Test
	public void variablesGetProcessedJustOnce() throws Exception {
		t.set("one", "#{一}");
		t.set("two", "#{二}");
		t.set("three", "#{三}");
		assertTemplateEvaluatesTo("#{一},#{二},#{三}");
	}

}
