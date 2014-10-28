package com.caribe.stone.anki;

import static org.junit.Assert.*;

import org.junit.Test;

public class NoteTest {

	@Test
	public void test() {
		Note n = new Note();
		n.setContent("renovate['renəˌveit]<div>to improve a room or building by rebuilding, expanding, and/or&nbsp;</div><div>redecorating it&nbsp;</div>&nbsp;They’re going to renovate their house by adding another bathroom.&nbsp;");
		n.setWord("renovate");
		n.setId(1357566999625L);
		
		assertEquals(4, n.getFields());
		assertEquals("renovate", n.getFront());
		assertEquals("['renəˌveit]", n.getPhonetic());
		assertEquals("<div>to improve a room or building by rebuilding, expanding, and/or&nbsp;</div><div>redecorating it&nbsp;</div>", n.getBack());
		assertEquals("&nbsp;They’re going to renovate their house by adding another bathroom.&nbsp;", n.getExample());
		
		n.setFront("front");
		n.setPhonetic("phonetic");
		n.setBack("back");
		n.setExample("example");
		assertEquals("frontphoneticbackexample",n.getContent());
	}
	
	@Test
	public void fields() throws Exception {
		Note n = new Note();
		assertEquals(0, n.getFields());
	}

    @Test
    public void testPhonetic() {
        Note note = new Note();
        assertTrue(note.hasntPhonetic());
        note.setPhonetic("");
        assertTrue(note.hasntPhonetic());
        note.setPhonetic(" ");
        assertTrue(note.hasntPhonetic());

        note.setPhonetic("əu'netik");
        assertFalse(note.hasntPhonetic());
    }


}
