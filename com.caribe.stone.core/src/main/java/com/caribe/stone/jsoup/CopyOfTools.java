package com.caribe.stone.jsoup;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CopyOfTools {

	public static void main(String[] args) throws IOException {
		File file = new File("E:/study/program/JavaFramework/workspace/deppDemo/com.caribe.stone.core/word/i/inspector.html");
		System.out.println(file.exists());

		Document doc = Jsoup.parse(file, "utf-8");

		Elements elementsByClass = doc.getElementsByClass("play_voice");
		for (Element element : elementsByClass) {
			System.out.println(element.text());
		}
		Elements elementsByTag = doc.getElementsByTag("img");
		for (Element element : elementsByTag) {
			System.out.println(element.attr("src"));
		}
	}

}
