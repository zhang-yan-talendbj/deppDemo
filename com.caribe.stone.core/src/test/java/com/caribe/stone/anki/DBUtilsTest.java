package com.caribe.stone.anki;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

public class DBUtilsTest {
	private static final String ANKI_PROPERTIES_PATH = "anki.properties";

	@Test
	public void test() throws IOException, ClassNotFoundException, SQLException {
		Properties pro = new Properties();
		pro.load(new ClassPathResource(ANKI_PROPERTIES_PATH).getInputStream());
		AnkiSettings settings = AnkiFactory.loadAnkiSettings(pro);

		DBUtils db = new DBUtils(settings);
		Dao dao = new Dao(db);

		Note note = dao.getNoteById(1363686584375L);
		assertEquals(4, note.getFields());
		assertEquals("front", note.getFront());
		assertEquals("frontphoneticbackexample", note.getContent());

		note.setFront("A");
		note.setPhonetic("B");
		note.setBack("C");
		note.setExample("D");
		dao.update(note);

		Note tmpNote = dao.getNoteById(1363686584375L);
		assertEquals("A\u001fB\u001fC\u001fD", tmpNote.getContent());
		tmpNote.setContent("frontphoneticbackexample");
		dao.update(tmpNote);

	}

}
