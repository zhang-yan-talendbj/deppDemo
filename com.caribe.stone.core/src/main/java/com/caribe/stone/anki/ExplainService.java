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

    private Elements getYouDaoElement(String word) {
		String url = "http://dict.youdao.com/search?le=eng&q="
				+ word
				+ "&keyfrom=dict.top";
        return getElementsByURL("div.collinsToggle", url);
	}

	private Elements getElementsByURL(String selecter, String url) {
		Elements links = null;
        try {
            links = Jsoup.connect(url).userAgent("Mozilla").timeout(5000).get().select(selecter);
        } catch (IOException e) {
           System.out.println("get element error:"+url);
        }
		return links;
	}

	public String getCollinsExplain(String word) {
		Elements element = getIcibaELement(word);
		if (element == null || element.size() == 0) {
			element = getYouDaoElement(word);
		}

		if (element != null && element.size() > 0) {
			return element.html();
		}
		return null;
	}

	private Elements getIcibaELement(String word) {
		String url = HTTP_WWW_ICIBA_COM_SEARCH_S + word;
		String selecter = "div.collins_en_cn";
		Elements eles = getElementsByURL(selecter, url);
		return eles;
	}
}
