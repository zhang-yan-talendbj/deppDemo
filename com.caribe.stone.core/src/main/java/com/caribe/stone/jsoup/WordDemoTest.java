package com.caribe.stone.jsoup;

import static org.junit.Assert.*;

import org.junit.Test;

public class WordDemoTest {

	@Test
	public void testIsDownloadAudio() {
		assertFalse(WordDemo.isDownloadAudio("to air (something) out"));
		assertTrue(WordDemo.isDownloadAudio("band (N-COUNT-COLL,one-man band)"));
	}

}
