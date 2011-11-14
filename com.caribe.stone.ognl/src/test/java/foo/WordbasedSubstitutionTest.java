package foo;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import junit.framework.Assert;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

public class WordbasedSubstitutionTest {

	@Test
	public void testName() throws Exception {
		Pattern keywordPattern = Pattern.compile("##" + "wtf" + "##");
		Matcher matcher = keywordPattern.matcher("miss##wtf##miss");
		String replaceAll = matcher.replaceAll("wtf");
		Assert.assertEquals("misswtfmiss", replaceAll);
	}

	@Test
	public void testWordbasedSubstitution() throws Exception {
		String expression = "<html>My name is ##firstName## ##lastName##!</html>";
		WordbasedSubstitution firstName = new WordbasedSubstitution("firstName", "firstName");
		WordbasedSubstitution lastName = new WordbasedSubstitution("lastName", "lastName");
		Map map = new HashMap();
		map.put("firstName", "Bruce");
		map.put("lastName", "Zhang");
		String result = firstName.subsitute(expression, map);
		result = lastName.subsitute(result, map);
		Assert.assertEquals("<html>My name is Bruce Zhang!</html>", result);
	}

	@Test
	public void testTemplateForWord() throws Exception {
		String content = FileUtils.readFileToString(new File("d:/tmp/Sunsuper Pre Lodgement Follow up letter.xml"));
		Map<String, String> map = new HashMap<String, String>();
		map.put("First_Name", "Bruce");
		map.put("Surname", "Zhang");
		map.put("AddressLine1", "Beijing");
		map.put("AddressLine2", "Changchun");
		WordbasedSubstitution firstName = new WordbasedSubstitution("First_Name", "First_Name");
		WordbasedSubstitution lastName = new WordbasedSubstitution("Surname", "Surname");
		WordbasedSubstitution address1 = new WordbasedSubstitution("AddressLine1", "AddressLine1");
		WordbasedSubstitution address2 = new WordbasedSubstitution("AddressLine2", "AddressLine2");
		content=firstName.subsitute(content, map);
		content=lastName.subsitute(content, map);
		content=address1.subsitute(content, map);
		content=address2.subsitute(content, map);
		
		File file=new File("d:/tmp/result.xml");
		FileUtils.writeStringToFile(file, content);
		

	}
}
