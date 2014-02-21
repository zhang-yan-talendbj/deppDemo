package com.caribe.stone.anki;

import java.util.Properties;

public class AnkiFactory {
	private static final String ANKI_PATH = "anki.path";

	public static AnkiSettings loadAnkiSettings(Properties properties) {
		AnkiSettings result = new AnkiSettings();
		result.setAnkiPath(properties.getProperty(ANKI_PATH));
		result.setJDBC_URL(properties.getProperty(ANKI_PATH) + "/collection.anki2");
		result.setMediaPath(properties.getProperty(ANKI_PATH) + "/collection.media/");
		return result;
	}

}
