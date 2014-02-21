package com.caribe.stone.anki;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

public class AnkiSettingsTest {

	private static final String ANKI_PROPERTIES_PATH = "anki.properties";

	@Test
	public void test() throws IOException, ClassNotFoundException, SQLException {
		Properties pro = new Properties();
		pro.load(new ClassPathResource(ANKI_PROPERTIES_PATH).getInputStream());
		AnkiSettings settings = AnkiFactory.loadAnkiSettings(pro);

		Note note = new Note();
		note.setWord("aa");
		settings.contain(note);
		
		assertTrue(settings.contain(note));
		assertFalse(settings.contain(new Note("zzzzzzzzzzzz")));

	}

}
