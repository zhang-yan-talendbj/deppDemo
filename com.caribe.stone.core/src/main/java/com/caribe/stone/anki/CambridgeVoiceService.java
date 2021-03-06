package com.caribe.stone.anki;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

/**
 * Created by thinkdeeply on 7/13/14.
 */
public class CambridgeVoiceService {
    public File getUKVoice(Note note, String mediaPath) {
        File mp3File = getWordFromCambridge(note.getWord(), "uk", mediaPath, "-uk");
        return mp3File;
    }

    public static void main(String[] args) {
        CambridgeVoiceService service = new CambridgeVoiceService();
        service.getPhonetic(null);
    }

    public String getPhonetic(String word) {
        StringBuffer buffer = new StringBuffer();
        String url = "http://dictionary.cambridge.org/dictionary/british/" + word;
        Elements elements = getElement("pron", url);
        if (elements != null) {
            for (Element ele : elements) {
                if (ele != null)
                    buffer.append(ele.text());
            }
        } else {
            return null;
        }

        return buffer.toString();
    }

    private File getWordFromCambridge(String word, String selecter, String path, String suffix) {
        String url = "http://dictionary.cambridge.org/dictionary/british/" + word.toLowerCase();

        Elements links = getElement(selecter, url);
        String saveFile = path + word + suffix + ".mp3";
        if (links != null) {
            String mp3File = links.attr("data-src-mp3");
            if (mp3File != null && mp3File.length() > 0) {
                System.out.println(mp3File);
                DownLoadService.httpDownload(mp3File, saveFile);
                return new File(saveFile);
            }
        }
        return null;
    }

    private Elements getElement(String selecter, String url) {
        Elements links = null;
        try {
            links = Jsoup.connect(url).userAgent("Mozilla").timeout(5000).get().getElementsByClass(selecter);

            // //*[@id="entryContent"]/div[1]/div[1]/span/a[1]
        } catch (IOException e) {
            e.printStackTrace();
        }
        return links;
    }

    public File getVoiceFile(Note note, String mediaPath) {
        File mp3File = getWordFromCambridge(note.getWord(), "us", mediaPath, "-us");
        return mp3File;
    }
}
