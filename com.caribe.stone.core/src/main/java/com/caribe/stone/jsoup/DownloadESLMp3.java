package com.caribe.stone.jsoup;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.aspectj.util.FileUtil;

public class DownloadESLMp3 {
	public static void main(String[] args) throws IOException {
		String path = "D:/english/ESL播客课程2006年度合集";
		File folder = new File(path);
		File[] list = folder.listFiles();
		for (File f : list) {
			String name = f.getName();
			if (name.indexOf("MP3") > 0) {
				List url = IOUtils.readLines(new FileInputStream(f));
				String x = (String) url.get(1);
				String[] split = x.split("=");
				String x2 = split[1];
				System.out.println(x2);
				String saveFile = f.getParent()+x2.substring(x2.lastIndexOf("/"));
				System.out.println(saveFile);
				File file = new File(saveFile);
				if(!file.exists()){
//				System.out.println(file.createNewFile());
				WordDemo.httpDownload(x2, saveFile);
				}
			}
		}
	}
}
