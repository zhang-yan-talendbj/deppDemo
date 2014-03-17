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

	public boolean containUS(Note note) {
		File file = new File(getMediaPath() + note.getWord() + "-us.mp3");
		return file.exists();
	}

	public boolean containUK(Note note) {
		File file = new File(getMediaPath() + note.getWord() + "-uk.mp3");
		return file.exists();
	}

	public boolean containYoudao(Note note) {
		File file = new File(getMediaPath() + note.getWord() + "-yd.mp3");
		return file.exists();
	}
	
	public boolean containGA(Note note) {
		File file = new File(getMediaPath() + note.getWord() + "-ga.mp3");
		return file.exists();
	}

	public boolean containRP(Note note) {
		File file = new File(getMediaPath() + note.getWord() + "-rp.mp3");
		return file.exists();
	}

	public boolean containYGA(Note note) {
		File file = new File(getMediaPath() + note.getWord() + "-y-ga.mp3");
		return file.exists();
	}

	public boolean containYRP(Note note) {
		File file = new File(getMediaPath() + note.getWord() + "-y-rp.mp3");
		return file.exists();
	}
}
