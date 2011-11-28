import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestTemplate {

	private Template t;

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
		assertTemplateEvaluatesTo("一,二,三");
	}

	@Before
	public void setUp() {
		t = new Template("#{one},#{two},#{three}");
		t.set("one", "一");
		t.set("two", "二");
		t.set("three", "三");
	}
}
