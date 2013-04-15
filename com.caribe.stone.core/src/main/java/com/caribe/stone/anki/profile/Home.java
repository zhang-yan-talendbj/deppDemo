package com.caribe.stone.anki.profile;

public class Home extends ConfigerFile {
	public Home() {

		letterPath = "d:/English/anki/voice/letter";
		path = "d:/English/anki/voice/";
		voice = "d:/English/voice/";
		ignorPath = "d:/English/anki/voice/ignore.txt";
		ankiPath = "f:/depp/Documents and Settings/Administrator/My Documents/Anki/User 1/";

		JDBC_URL = ankiPath + "collection.anki2";
		mediaPath = ankiPath + "collection.media/";
		updatePhonetic = true;
		deckId = 1;
		updateJiong=true;
		
		cardType="bruce";
	}
}
