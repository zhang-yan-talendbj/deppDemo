package com.caribe.stone.anki;

import java.io.IOException;

public class DownloadSingleWord {
	private static final String ANKI_PROPERTIES_PATH = "anki.properties";

	public static void main(String[] args) throws IOException {
		String property=null; 
		property= "c:/Users/yan.zhang/Documents/Anki/User 1";
//    	property = "/Users/thinkdeeply/Documents/Anki/User 1";
		AnkiSettings settings = new AnkiSettings(property);

		String mediaPath = settings.getMediaPath();

//		String word = "familiarize-yourself-with-sth?q=familiarise";
//		AnkiHelp.getWordFromCambridge(word, "pron-us", mediaPath, "-us");
//		AnkiHelp.getWordFromCambridge(word, "pron-uk", mediaPath, "-uk");
		
		String word2 = "match up";
		String saveFile = mediaPath + word2 + "-y-ga.mp3";
		// http://dict.youdao.com/dictvoice?audio=soon&type=1
		DownLoadService.httpDownload("http://dict.youdao.com/dictvoice?audio=" + word2 + "&type=1", saveFile);
		String saveFile1 = mediaPath + word2 + "-y-rp.mp3";
		DownLoadService.httpDownload("http://dict.youdao.com/dictvoice?audio=" + word2 + "&type=2", saveFile1);
	
//		File gaFromICB = IcibaVoiceService.getGAFromICB(word2, mediaPath);
//		File getRPFromICB = IcibaVoiceService. getRPFromICB(word2, mediaPath);
	
	}
}
