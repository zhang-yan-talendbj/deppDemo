package com.caribe.stone.jsoup;

import org.junit.Test;

import static org.junit.Assert.*;


public class WordDemoTest {

	@Test
	public void testIsDownloadAudio() {
		assertFalse(WordDemo.isDownloadAudio("to air (something) out"));
		assertTrue(WordDemo.isDownloadAudio("band (N-COUNT-COLL,one-man band)"));
	}

}
