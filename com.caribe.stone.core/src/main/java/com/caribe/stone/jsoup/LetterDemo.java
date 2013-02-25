package com.caribe.stone.jsoup;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.io.FileUtils;

public class LetterDemo {
	private static String newPath = null;
	private static Map<String, File> fileList = new HashMap<String, File>();

	public static void main(String[] args) throws IOException {
		String path = "d:/english/AnkiWord/voice/";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");
		newPath = path + sdf.format(new Date());

		File file2 = new File(newPath);
		file2.mkdirs();
		File f = new File(path);
		addFiles(f);

		String pathname = "d:/english/AnkiWord/anki.txt";
		File file = new File(pathname);
		List<String> readLines = FileUtils.readLines(file);
		for (String object : readLines) {
			String word = object.substring(0, object.indexOf("	"));
			// System.out.println(word);2013-02-21 10:56:32
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			String suffix =sdf1.format(new Date());
			if (object.endsWith(suffix)) {
				Set<Entry<String, File>> entrySet = fileList.entrySet();
				for (Entry<String, File> entry : entrySet) {
					aaa(word, entry);
				}

			}
		}
	}

	private static void aaa(String word, Entry<String, File> entry) throws IOException {
		String key = entry.getKey();
		// System.out.println(key);
		if (key.startsWith(word)) {
			File value = entry.getValue();
			System.out.println(value);
			String pathname2 = newPath + value.getName();
			File destFile = new File(pathname2);
			if (!destFile.exists()) {
				if (value.exists()) {
					FileUtils.moveFile(value, destFile);
				} else {
					System.out.println(value);
				}
			}
		}
	}

	public static void addFiles(File file) {
		if (file.isDirectory()) {
			File[] listFiles = file.listFiles();
			for (File file2 : listFiles) {
				addFiles(file2);
			}
		} else {
			fileList.put(file.getName(), file);
		}
	}
}
