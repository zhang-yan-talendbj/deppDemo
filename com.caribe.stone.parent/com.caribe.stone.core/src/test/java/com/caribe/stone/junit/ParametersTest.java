package com.caribe.stone.junit;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class ParametersTest {
	private static String zipRegEx = "^\\d{5}([\\-]\\d{4})?$";
	private static Pattern pattern;
	private String phrase;
	private boolean match;

	public ParametersTest(String phrase, boolean match) {
		this.phrase = phrase;
		this.match = match;
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		pattern = Pattern.compile(zipRegEx);
	}

	@Test
	public void testParameters() throws Exception {
		Matcher mtcher = this.pattern.matcher(phrase);
		boolean isValid = mtcher.matches();
		assertEquals("Pattern did not validate zip code", isValid, match);
	}

	@Parameters
	public static Collection regExValues() {
		return Arrays.asList(new Object[][] { { "22101", true }, { "221x1", false }, { "22101-5150", true }, { "221015150", false } });
	}
}
