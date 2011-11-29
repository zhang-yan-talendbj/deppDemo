import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;


public class TestTemplateParse {

	@Test
	public void emptyTemplateRendersAsEmptyString() throws Exception {
		List<String> l=parse("");
		assertSegments(l, "");
	}
	
	@Test
	public void templateWithOnlyPlainText() throws Exception {
		List<String> l = parse("plain text only");
		assertSegments(l,"plain text only");
	}
	
	@Test
	public void parsingMultipleVariables() throws Exception {
		List<String> l = parse("#{a}:#{b}:#{c}");
		assertSegments(l,"#{a}",":","#{b}",":","#{c}");
	}

	public void assertSegments(List<String> l,Object... expected) {
		assertEquals(expected.length, l.size());
		assertEquals(Arrays.asList(expected),l);
	}

	public List<String> parse(String template) {
		TemplateParse p=new TemplateParse();
		List<String> l=p.parse(template);
		return l;
	}
}
