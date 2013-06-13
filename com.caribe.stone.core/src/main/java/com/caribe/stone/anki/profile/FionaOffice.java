package com.caribe.stone.anki.profile;

public class FionaOffice extends ConfigerFile {

	public FionaOffice() {
		path = "d:/english/AnkiWord/fiona/";
		letterPath = "d:/english/AnkiWord/voice/letter/";
		voice = "d:/Program Files/Lingoes/Translator2/voice/";
		ignorPath = "d:/english/AnkiWord/fiona/ignore.txt";
		ankiPath = "c:/Users/bsnpbag/Documents/Anki/fiona/";
		updatePhonetic = false;
		spellingMaxDay = 365;

		JDBC_URL = ankiPath + "collection.anki2";
		mediaPath = ankiPath + "collection.media/";

		updateJiong = true;
		jiongPath = "d:/english/AnkiWord/voice/jiong_fiona.txt";
		jiongWordPath = "d:/english/AnkiWord/voice/jiongWord_fiona.txt";
		cardType = "fiona";
	}
}
