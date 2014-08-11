package com.caribe.stone.anki;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

/**
 * Created by thinkdeeply on 7/13/14.
 */
public class IcibaVoiceService {
    private File getUKFromCambridge(String word, String mediaPath) {
        return getWordKing(word, "a.vCri_laba", mediaPath, "-ga");
    }

    private File getWordKing(String word, String selecter, String path, String suffix) {
        String url = "http://www.iciba.com/search?s=" + word;
        Elements links = null;
        try {
            links = Jsoup.connect(url).userAgent("Mozilla").timeout(5000).get().select(selecter);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String saveFile = path + word + suffix + ".mp3";
        if (links != null) {
            String attr = links.attr("onclick");
            if (attr != null && attr.indexOf("'") >= 0) {
                String[] split = attr.split("'");
                System.out.println(saveFile);
                DownLoadService.httpDownload(split[1], saveFile);
                return new File(saveFile);
            }
        }
        return null;
    }

    public  File getGAFromICB(String word, String mediaPath) {
        return getWordKing(word, "a.vCri_laba", mediaPath, "-ga");
    }

    public  File getRPFromICB(String word, String mediaPath) {
        return getWordKing(word, "a.ico_sound[title=真人发音]", mediaPath, "-rp");
    }
}
