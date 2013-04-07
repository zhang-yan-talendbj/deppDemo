package com.caribe.stone.anki.profile;


public class FionaHome extends ConfigerFile {
	public FionaHome() {

		letterPath = "d:/English/anki/voice/letter";
		path = "d:/English/anki/Fiona/";
		voice = "d:/English/voice/";
		ignorPath = "d:/English/anki/voice/ignore.txt";
		ankiPath = "f:/depp/Documents and Settings/Administrator/My Documents/Anki/fiona/";

		JDBC_URL = ankiPath + "collection.anki2";
		mediaPath = ankiPath + "collection.media/";
		updatePhonetic=false;
		spellingMaxDay=365;
	}
}
