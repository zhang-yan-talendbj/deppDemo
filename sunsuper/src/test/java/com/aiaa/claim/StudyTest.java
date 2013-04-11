package com.aiaa.claim;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import net.sf.json.JSONArray;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class StudyTest {

	public static void main(String[] args) throws IOException {

		Document doc = Jsoup.parse(new File("c:/slash.htm"), "utf-8");

		Elements select = doc.select("span[class^=highlight]");
		for (Element e : select) {
			System.out.println("---------------------------------------------------" + e.text());
			System.out.println(e.html());
		}
	}

}
