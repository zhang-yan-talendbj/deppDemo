package com.caribe.stone.anki;

import java.io.File;

public class AnkiSettings {

	private String ankiPath;
	private String mediaPath;
	private String JDBC_URL;

	public String getAnkiPath() {
		return ankiPath;
	}

	public void setAnkiPath(String ankiPath) {
		this.ankiPath = ankiPath;
	}

	public String getMediaPath() {
		return mediaPath;
	}

	public void setMediaPath(String mediaPath) {
		this.mediaPath = mediaPath;
	}

	public String getJDBC_URL() {
		return JDBC_URL;
	}

	public void setJDBC_URL(String jDBC_URL) {
		JDBC_URL = jDBC_URL;
	}

	public boolean contain(Note note) {
		File file = new File(getMediaPath() + note.getWord() + ".mp3");
		return file.exists();
	}

}
