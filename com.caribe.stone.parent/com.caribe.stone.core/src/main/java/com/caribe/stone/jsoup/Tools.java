package com.caribe.stone.jsoup;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Tools {
	private static List<String> list = new ArrayList<String>();
	private static List<String> writeList=new ArrayList<String>();

	public static void main(String[] args) {
		// Validate.isTrue(args.length == 1, "usage: supply url to fetch");
		String url = "http://localhost:8091/wiki/index";
		writeWiki(url);
	}

	private static void writeWiki(String url) {
		Elements links = null;
		try {
			links = Jsoup.connect(url).get().select("a[href]");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		if (links != null) {

			for (Element link : links) {
				String linkUrl = link.attr("abs:href");
				if (linkUrl.startsWith("http://localhost:8091/wiki")
						&& !list.contains(linkUrl)
						&& !linkUrl.contains("action=") &&!linkUrl.contains("#")) {
					System.out.println(linkUrl);
					if (!writeList.contains(linkUrl)) {
						writeFile(linkUrl);
					}
				}
			}
		}
	}

	private static void writeFile(String url) {
		System.out.println(url);
		list.add(url);
		writeWiki(url);
		try {
			FileUtils.writeStringToFile(getFile(url), getContent(url), "utf-8");
			writeList.add(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static File getFile(String url) {
		return new File(getFileName(url));
	}

	private static String getContent(String url) throws IOException {
		Document document = Jsoup.connect(url).get();
		return document.select("div#content").outerHtml();
	}

	private static String getFileName(String url) {
		try {
			url = java.net.URLDecoder.decode(url, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String fileName = "moinmoin/"
				+ url.substring(url.lastIndexOf("/"), url.length()) + ".html";
		return fileName;
	}

}
