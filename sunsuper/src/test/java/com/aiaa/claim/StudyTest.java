package com.aiaa.claim;

import java.io.File;
import java.io.IOException;

import net.sf.json.JSONArray;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class StudyTest {

	public static void main(String[] args) throws IOException {

		Document doc = Jsoup.parse(new File("d:/aa.txt"), "utf-8");

		Element head = doc.head();
		Elements elementsByTag = head.getElementsByTag("script");
		String data = elementsByTag.last().data();
		data = data.replace("//&lt;![CDATA[", "");
		data = data.replace("//]]&gt;", "");
		data = data.replace("var random_word_topics =", "");
		System.out.println(data.substring(3,data.length()-3));
		JSONArray fromObject = JSONArray.fromObject(data.substring(3,data.length()-3));

		// System.out.println(data.substring(data.indexOf("var random_word_topics ="),data.length()));
	}

}
