package com.caribe.stone.anki;

import java.io.File;

public class AnkiSettings {

    static final String ANKI_PROPERTIES_PATH = "anki.properties";
    static final String ANKI_PATH = "anki.path";
    private String ankiPath;
	private String mediaPath;
	private String JDBC_URL;

    public AnkiSettings(String property) {
		this.ankiPath=property;
        this.JDBC_URL=property + "/collection.anki2";
        this.mediaPath=property + "/collection.media/";
    }

    public String getAnkiPath() {
		return ankiPath;
	}


	public String getMediaPath() {
		return mediaPath;
	}


	public String getJDBC_URL() {
		return JDBC_URL;
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

    public File getUS(Note note) {
        File file = new File(getMediaPath() + note.getWord() + "-us.mp3");
        return file;
    }

    public File getUK(Note note) {
        File file = new File(getMediaPath() + note.getWord() + "-uk.mp3");
        return file;
    }
}
