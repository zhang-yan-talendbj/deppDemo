import static org.junit.Assert.*;
import org.junit.Test;

public class TestTemplate {

	@Test
	public void oneVariable() throws Exception {
		Template template = new Template("Hello,#{name}");
		template.set("name", "Bruce");
		assertEquals("Hello,Bruce", template.evaluate());
	}

	@Test
	public void differentValue() throws Exception {
		Template template = new Template("Hello,#{name}");
		template.set("name", "Lasse");
		assertEquals("Hello,Lasse", template.evaluate());
	}

	@Test
	public void testName() throws Exception {

	}
}
