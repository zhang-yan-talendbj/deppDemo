package com.caribe.stone.anki;

import java.io.File;
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

//		String word = "familiarize-yourself-with-sth?q=familiarise";
//		AnkiHelp.getWordFromCambridge(word, "pron-us", mediaPath, "-us");
//		AnkiHelp.getWordFromCambridge(word, "pron-uk", mediaPath, "-uk");
		
		String word2 = "match up";
		String saveFile = mediaPath + word2 + "-y-ga.mp3";
		// http://dict.youdao.com/dictvoice?audio=soon&type=1
		AnkiHelp.httpDownload("http://dict.youdao.com/dictvoice?audio=" + word2 + "&type=1", saveFile);
		String saveFile1 = mediaPath + word2 + "-y-rp.mp3";
		AnkiHelp.httpDownload("http://dict.youdao.com/dictvoice?audio=" + word2 + "&type=2", saveFile1);
	
		File gaFromICB = AnkiHelp.getGAFromICB(word2, mediaPath);
		File getRPFromICB =AnkiHelp. getRPFromICB(word2, mediaPath);
	
	}
}
