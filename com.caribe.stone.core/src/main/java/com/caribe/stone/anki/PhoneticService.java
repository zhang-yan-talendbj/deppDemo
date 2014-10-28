package com.caribe.stone.anki;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by thinkdeeply on 7/13/14.
 */
public class PhoneticService {

    public static final String HTTP_WWW_ICIBA_COM_SEARCH_S = "http://www.iciba.com/search?s=";

    public String getPhonetic(Note note) {
        String word=  note.getWord();
        String url = HTTP_WWW_ICIBA_COM_SEARCH_S + word;
        Elements links = null;
        try {
            Document document = Jsoup.connect(url).userAgent("Mozilla").timeout(5000).get();
            links = document.select("span.fl");
            return links.text();
        } catch (IOException e) {
            System.out.println(word);
            System.out.println(e.getMessage());
        }
        return "";
    }
}
