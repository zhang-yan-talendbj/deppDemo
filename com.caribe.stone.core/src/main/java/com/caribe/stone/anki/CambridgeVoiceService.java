package com.caribe.stone.anki;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

/**
 * Created by thinkdeeply on 7/13/14.
 */
public class CambridgeVoiceService {
    public File getUKVoice(Note note, String mediaPath) {
       File mp3File = getWordFromCambridge(note.getWord(), "pron-uk", mediaPath, "-uk");
        return mp3File;
    }

    private File getWordFromCambridge(String word, String selecter, String path, String suffix) {
        String url = "http://dictionary.cambridge.org/dictionary/british/" + word.toLowerCase();
        Elements links = null;
        try {
            links = Jsoup.connect(url).userAgent("Mozilla").timeout(5000).get().getElementsByClass(selecter);

            // //*[@id="entryContent"]/div[1]/div[1]/span/a[1]
        } catch (IOException e) {
            e.printStackTrace();
        }
        String saveFile = path + word + suffix + ".mp3";
        if (links != null) {
            String mp3File = links.attr("data-src-mp3");
            if (mp3File != null && mp3File.length() > 0) {
                DownLoadService.httpDownload(mp3File, saveFile);
                return new File(saveFile);
            }
        }
        return null;
    }

    public File getVoiceFile(Note note, String mediaPath) {
       File mp3File = getWordFromCambridge(note.getWord(), "pron-us", mediaPath, "-us");
        return mp3File;
    }
}
