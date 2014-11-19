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
        Elements links = getElement(word, selecter);
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

	private Elements getElement(String word, String selecter) {
		String url = "http://www.iciba.com/search?s=" + word;
        Elements links = null;
        try {
            links = Jsoup.connect(url).userAgent("Mozilla").timeout(5000).get().select(selecter);
        } catch (IOException e) {
           System.out.println("get element error:"+word);
        }
		return links;
	}
    
    

    public  File getGAFromICB(String word, String mediaPath) {
        return getWordKing(word, "a.vCri_laba", mediaPath, "-ga");
    }

    public  File getRPFromICB(String word, String mediaPath) {
        return getWordKing(word, "a.ico_sound[title=真人发音]", mediaPath, "-rp");
    }

	public String getIndustry(String word) {
		Elements element = getElement(word, "div.vCigen");
		if(element!=null && element.size()>0){
			return element.get(0).html();
		}
		return " ";
	}

    public String downloadPhraseMp3(String word) {
        Elements element = getElement(word, "a.vCri_laba");
        if(element==null|| element.isEmpty()){
            element=getElement(word,"a.ico_sound");
        }

        String str = element.attr("onclick");
        if(str!=null && str.indexOf("'")>0&&str.lastIndexOf("'")>0)  {
            String mp3Url = str.substring(str.indexOf("'") + 1, str.lastIndexOf("'"));
            return mp3Url;
        }
        return null;
    }
}
