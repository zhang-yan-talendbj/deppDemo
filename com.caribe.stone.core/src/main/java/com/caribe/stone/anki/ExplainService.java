package com.caribe.stone.anki;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Iterator;

/**
 * Created by thinkdeeply on 7/13/14.
 */
public class ExplainService {

    public static final String HTTP_WWW_ICIBA_COM_SEARCH_S = "http://www.iciba.com/search?s=";

    public String getExplain(String word) {
        String url = HTTP_WWW_ICIBA_COM_SEARCH_S + word;
        try {
            Document document = Jsoup.connect(url).userAgent("Mozilla").timeout(5000).get();
            Elements eles = document.getElementsByClass("collins_en_cn");
            StringBuffer buffer = new StringBuffer();
            for (Element e : eles) {
            	String html = e.html();
            	buffer.append(html);
			}
            
            return buffer.toString();
        } catch (IOException e) {
            System.out.println(word);
            System.out.println(e.getMessage());
        }
        return "";
    }
}
