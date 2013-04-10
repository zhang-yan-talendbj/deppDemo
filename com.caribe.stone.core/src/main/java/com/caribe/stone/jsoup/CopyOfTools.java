package com.caribe.stone.jsoup;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CopyOfTools {

	public static void main(String[] args) throws IOException {
		File file = new File("E:/study/program/JavaFramework/workspace/deppDemo/com.caribe.stone.core/word/i/ink.html");
		String attr="http://assets.baicizhan.com/cropped_images/20121016_03_23_25_766.jpg";
		System.out.println(attr.substring(attr.lastIndexOf("/")+1,attr.length()));
	}

}
