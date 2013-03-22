package com.caribe.stone.anki.profile;

public class ConfigerFile {

	protected String path = null;
	protected String voice = null;
	protected String letterPath = null;
	protected String ignorPath = null;
	protected String JDBC_URL = null;

	protected int deckId = 0;

	protected int spellingMaxDay = 30;

	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getVoice() {
		return voice;
	}
	public void setVoice(String voice) {
		this.voice = voice;
	}
	public String getLetterPath() {
		return letterPath;
	}
	public void setLetterPath(String letterPath) {
		this.letterPath = letterPath;
	}
	public String getIgnorPath() {
		return ignorPath;
	}
	public void setIgnorPath(String ignorPath) {
		this.ignorPath = ignorPath;
	}
	public String getJDBC_URL() {
		return JDBC_URL;
	}
	public void setJDBC_URL(String jDBC_URL) {
		JDBC_URL = jDBC_URL;
	}
	public int getDeckId() {
		return deckId;
	}
	public void setDeckId(int deckId) {
		this.deckId = deckId;
	}
	public int getSpellingMaxDay() {
		return spellingMaxDay;
	}
	public void setSpellingMaxDay(int spellingMaxDay) {
		this.spellingMaxDay = spellingMaxDay;
	}
	public boolean isUpdatePhonetic() {
		return updatePhonetic;
	}
	public void setUpdatePhonetic(boolean updatePhonetic) {
		this.updatePhonetic = updatePhonetic;
	}
	public String getMediaPath() {
		return mediaPath;
	}
	public void setMediaPath(String mediaPath) {
		this.mediaPath = mediaPath;
	}
	public String getAnkiPath() {
		return ankiPath;
	}
	public void setAnkiPath(String ankiPath) {
		this.ankiPath = ankiPath;
	}
	boolean updatePhonetic;
	protected String mediaPath;
	protected String ankiPath;
}