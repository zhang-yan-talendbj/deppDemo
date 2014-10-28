package com.caribe.stone.anki;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

/**
 * Created by thinkdeeply on 7/13/14.
 */
public class YouDaoVoiceService {
    public void downloadYoudaoVoice1(Note note, String saveFile) {
        DownLoadService.httpDownload("http://dict.youdao.com/dictvoice?audio=" + note.getWord() + "&type=1", saveFile);
    }

    public void downloadYoudaoVoice2(Note note, String saveFile) {
        DownLoadService.httpDownload("http://dict.youdao.com/dictvoice?audio=" + note.getWord() + "&type=2", saveFile);
    }
    
}
