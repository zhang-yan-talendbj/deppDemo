package com.aiaa.claim;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ParseHtmlDemo {
	public List getInputList() throws IOException {

		String string = "c:/Documents and Settings/bsnpbag/My Documents/Downloads/app.htm";
		Document parse = Jsoup.parse(new File(string), "utf-8");
		Elements select = parse.select("input[type=text]");

		LinkedList inputList = new LinkedList();
		for (Element element : select) {
			String value = element.attr("value");
			if (value != null && value.length() > 0) {
				System.out.println(element);
				String id = element.attr("id");
				String name = element.attr("name");
				Map map = new HashMap();
				map.put("id", id);
				map.put("name", name);
				map.put("value", value);
				inputList.add(map);
			}
		}
		return inputList;
	}
}
