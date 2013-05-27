package z.java.util;

import static org.junit.Assert.*;

import java.util.StringTokenizer;

import org.junit.Test;

public class StringTokenizerTest {

	@Test
	public void testTokenizerWithoutDelims() {
		StringTokenizer tokenizer = new StringTokenizer("this is a test", " ");
		assertEquals(4, tokenizer.countTokens());
		StringBuffer sb = new StringBuffer();
		while (tokenizer.hasMoreElements()) {
			sb.append(tokenizer.nextToken());
		}
		assertEquals("thisisatest", sb.toString());
	}

	@Test
	public void testTokenizerWithDelims() {
		StringTokenizer tokenizer = new StringTokenizer("this is a test", " ", true);
		assertEquals(7, tokenizer.countTokens());
		StringBuffer sb = new StringBuffer();
		while (tokenizer.hasMoreElements()) {
			sb.append(tokenizer.nextToken());
		}
		assertEquals("this is a test", sb.toString());
	}

}
