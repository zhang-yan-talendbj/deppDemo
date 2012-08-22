package com.caribe.stone.string;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringTest {

	@Test
	public void testSubstring() throws Exception {
		assertEquals("123", "0123".substring(1));
		assertEquals("123", "01234".substring(1, 4));
		assertEquals("01234".substring(1, 4), "01234".subSequence(1, 4));
	}
	
	@Test
	public void testConcat() throws Exception {
		assertEquals("12345", "1234".concat("5"));
	}
}
