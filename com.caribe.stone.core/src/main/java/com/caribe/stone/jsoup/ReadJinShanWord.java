package com.caribe.stone.jsoup;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.ibm.icu.text.SimpleDateFormat;

public class ReadJinShanWord {

	public static void main(String[] args) throws IOException {
		File file = new File("c:/word.txt");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		File path = new File("d:/english/AnkiWord/fiona/"+sdf.format(new Date()));
		path.mkdir();
		System.out.println(file);
		List<String> list = FileUtils.readLines(file, "utf-8");
		for (String str : list) {
			if (str.startsWith("+")) {
				String word = str.substring(1, str.length());
				String voice = "d:/Program Files/Lingoes/Translator2/voice/";

				File mediaFileWAV = new File(voice + word.charAt(0) + "/" + word + ".wav");
				if (mediaFileWAV.exists()) {
					FileUtils.copyFileToDirectory(mediaFileWAV, path);
				}
				String b = "d:/english/voice/British/";
				String a = "d:/english/voice/American/";
				copyMp3(path, word, b);
				copyMp3(path, word, a);
			}

		}

		for (String str : list) {
			if (str.startsWith("+")) {
				String word = str.substring(1, str.length());
				File wFile = WordDemo.getRPFromICB(word);
				File wFile2 = WordDemo.getGAFromICB(word);
				if (wFile != null) {
					File dest = new File(wFile.getName().replace("null", ""));
					wFile.renameTo(dest);
					FileUtils.moveFileToDirectory(dest, path, true);
				}
				if (wFile2 != null) {
					File dest2 = new File(wFile2.getName().replace("null", ""));
					wFile2.renameTo(dest2);
					FileUtils.moveFileToDirectory(dest2, path, true);
				}
			}

		}
	}

	private static void copyMp3(File path, String word, String a) throws IOException {
		File aFile = new File(a + word.charAt(0) + "/" + word + ".mp3");
		if (aFile.exists()) {
			FileUtils.copyFileToDirectory(aFile, path);
		}
	}

}
