package com.caribe.stone.anki;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import static junit.framework.TestCase.assertEquals;

public class AnkiSettingsTest {

	@Test
	public void testAnkiSettings() throws IOException, ClassNotFoundException, SQLException {
		Properties pro = new Properties();

        pro.setProperty( "anki.path","/Users/thinkdeeply/Documents/Anki/User 1");
//        anki.path=/Users/thinkdeeply/Documents/Anki/User 1

        String property=null; 
		property= "c:/Users/yan.zhang/Documents/Anki/User 1";
//    	property = "/Users/thinkdeeply/Documents/Anki/User 1";
		AnkiSettings settings = new AnkiSettings(property);

        assertEquals("/Users/thinkdeeply/Documents/Anki/User 1",settings.getAnkiPath());
        assertEquals("/Users/thinkdeeply/Documents/Anki/User 1/collection.anki2",settings.getJDBC_URL());
        assertEquals("/Users/thinkdeeply/Documents/Anki/User 1/collection.media/",settings.getMediaPath());

	}

}
