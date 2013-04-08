package com.caribe.stone.jsoup;

import java.io.File;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.caribe.stone.anki.profile.ConfigerFile;
import com.caribe.stone.anki.profile.Office;

public class DeleteDumpFile {

	private static Map<String, File> fileList = new HashMap<String, File>();

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		ConfigerFile office = new Office();
		File path = new File(office.getPath());
		addFiles(path);
	}

	public static void addFiles(File file) {
		if (file.isDirectory()) {
			File[] listFiles = file.listFiles();
			for (File file2 : listFiles) {
				addFiles(file2);
			}
		} else {
			if (fileList.containsKey(file.getName())) {
				System.out.println(file.getAbsolutePath());
				fileList.get(file.getName()).delete();
			}
			fileList.put(file.getName(), file);
		}
	}
}
