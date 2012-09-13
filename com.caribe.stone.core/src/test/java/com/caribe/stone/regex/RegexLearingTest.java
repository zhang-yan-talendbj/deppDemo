package com.caribe.stone.regex;

import static org.junit.Assert.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class RegexLearingTest {

	@Test
	public void testEscapeCharacter() {
		String esc = "\\$";
		System.out.println(esc);// \$
	}

	@Test
	public void testHowGroupCountWorks() throws Exception {
		String haystack = "The needle shop sells needles.";
		String regex = "(needle)";
		Matcher matcher = Pattern.compile(regex).matcher(haystack);
		assertEquals(1, matcher.groupCount());
	}

	@Test
	public void testMatches() throws Exception {
		assertTrue(Pattern.compile("\\d").matcher("1abcdefg").find());
		assertTrue(Pattern.compile("\\w").matcher("1abcdefg").find());
		assertTrue(Pattern.compile("\\W").matcher(" ").find());
		assertTrue(Pattern.compile("\\s").matcher(" \t\n\f\r").find());
		assertTrue(Pattern.compile("\\S").matcher("1abcdefg").find());
	}

	@Test
	public void testFindStartAndEnd() throws Exception {
		String haystack = "The needle shop sells needles.";
		String regex = "(needle)";
		Matcher matcher = Pattern.compile(regex).matcher(haystack);
		assertTrue(matcher.find());
		assertEquals(4, matcher.start());
		assertEquals(10, matcher.end());
		
		assertTrue(matcher.find());
		assertEquals(22, matcher.start());
		assertEquals(28, matcher.end());
}
}
