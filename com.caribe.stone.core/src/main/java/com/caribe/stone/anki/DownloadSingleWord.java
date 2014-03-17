package com.caribe.stone.anki;

import java.io.IOException;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;

public class DownloadSingleWord {
	private static final String ANKI_PROPERTIES_PATH = "anki.properties";

	public static void main(String[] args) throws IOException {
		Properties pro = new Properties();
		pro.load(new ClassPathResource(ANKI_PROPERTIES_PATH).getInputStream());
		AnkiSettings settings = AnkiFactory.loadAnkiSettings(pro);

		String mediaPath = settings.getMediaPath();

		String word = "familiarize-yourself-with-sth?q=familiarise";
		AnkiHelp.getWordFromCambridge(word, "pron-us", mediaPath, "-us");
		AnkiHelp.getWordFromCambridge(word, "pron-uk", mediaPath, "-uk");
	}
}
