package com.caribe.stone.anki.profile;


public class Office extends ConfigerFile {
	public Office() {
		path = "d:/english/AnkiWord/voice/";
		letterPath = "d:/english/AnkiWord/voice/letter/";
		voice = "d:/Program Files/Lingoes/Translator2/voice/";
		ignorPath = "d:/english/AnkiWord/voice/ignore.txt";
		ankiPath = "c:/Users/bsnpbag/Documents/Anki/User 1/";
		JDBC_URL = ankiPath + "collection.anki2";
		mediaPath=ankiPath+"collection.media/";
		updatePhonetic = true;
		deckId = 1;
		updateJiong=true;
		jiongPath="d:/english/AnkiWord/voice/jiong.txt";
		jiongWordPath="d:/english/AnkiWord/voice/jiongWord.txt";
	}
}
