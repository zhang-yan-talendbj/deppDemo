package com.caribe.stone.anki;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.core.io.ClassPathResource;

public class AnkiHelp {

	private static final String ANKI_PROPERTIES_PATH = "anki.properties";

	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
		Properties pro = new Properties();
		pro.load(new ClassPathResource(ANKI_PROPERTIES_PATH).getInputStream());
		AnkiSettings settings = AnkiFactory.loadAnkiSettings(pro);

		DBUtils db = new DBUtils(settings);
		Dao dao = new Dao(db);
		QueryBean bean = new QueryBean();
		bean.setDeckId(1L);
		List<Note> notes = dao.getAllNotesByCondition(bean);

		for (Note note : notes) {
			
			String mediaPath = settings.getMediaPath();

			if (note.getFields() >=3 && hasntPhonetic(note)) {
				String phonetic = getPhonetic(note.getWord());
				if (phonetic == null) {
					phonetic = "";
				}
				note.setPhonetic(phonetic);
				dao.update(note);
			}

			if (!settings.contain(note)) {
//				File gaFromICB = getGAFromICB(note.getWord(), mediaPath);
//				if (gaFromICB == null) {
//					File getRPFromICB = getRPFromICB(note.getWord(), mediaPath);
//					if(getRPFromICB==null){
						String saveFile = mediaPath + note.getWord() + ".mp3";
						httpDownload("http://dict.youdao.com/dictvoice?audio="+note.getWord(), saveFile);
						System.out.println(note.getWord());
//					}
//				}
			}
		}
		
		System.out.println("Over!");

	}

	private static boolean hasntPhonetic(Note note) {
		return note.getPhonetic() == null || note.getPhonetic().length()==0 || note.getPhonetic().replace("", "").length()==0;
	}

	public static String getPhonetic(String word) {
		if (word.indexOf('(') > 0) {
			System.out.println(word);
			word = word.substring(0, word.indexOf('('));
			System.out.println(word);
		}
		String url = "http://www.iciba.com/search?s=" + word;
		Elements links = null;
		try {
			Document document = Jsoup.connect(url).userAgent("Mozilla").timeout(5000).get();
			links = document.select("span.fl");
			return links.text();
		} catch (IOException e) {
			System.out.println(word);
			System.out.println(e.getMessage());
		}
		return null;
	}

	private static File getWordKing(String word, String position, String newPath) {
		String url = "http://www.iciba.com/search?s=" + word;
		Elements links = null;
		try {
			links = Jsoup.connect(url).userAgent("Mozilla").timeout(5000).get().select(position);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String saveFile = newPath + word + ".mp3";
		if (links != null) {
			String attr = links.attr("onclick");
			if (attr != null && attr.indexOf("'") >= 0) {
				String[] split = attr.split("'");
				System.out.println(saveFile);
				httpDownload(split[1], saveFile);
				return new File(saveFile);
			}
		}
		return null;
	}

	public static File getGAFromICB(String word, String mediaPath) {
		return getWordKing(word, "a.vCri_laba", mediaPath);
	}

	public static File getRPFromICB(String word, String mediaPath) {
		return getWordKing(word, "a.ico_sound[title=真人发音]", mediaPath);
	}

	public static void httpDownload(String httpUrl, String saveFile) {
		File destFile = new File(saveFile);
		if (destFile.exists()) {
			return;
		}
		long start = System.currentTimeMillis();
		// 下载网络文件
		int byteread = 0;

		if (httpUrl != null && httpUrl.trim() != "") {
			URL url = null;
			try {
				url = new URL(httpUrl);
			} catch (MalformedURLException e1) {
				System.out.println("error:" + saveFile);
				System.out.println(e1.getMessage());
				return;
			}
			FileOutputStream fs = null;
			String name = System.currentTimeMillis() + "tmp.file";
			File file = new File(name);
			try {
				URLConnection conn = url.openConnection();
				if (conn != null) {

					InputStream inStream = conn.getInputStream();
					fs = new FileOutputStream(file);

					byte[] buffer = new byte[1204];
					while ((byteread = inStream.read(buffer)) != -1) {
						fs.write(buffer, 0, byteread);
					}
					long end = System.currentTimeMillis();
					System.out.print((int) (file.length() * 1d / (end - start)));
					System.out.println("K/s.");

					FileUtils.copyFile(file, destFile);
				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (fs != null) {
						fs.flush();
						fs.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (file != null) {
					file.delete();
				}
			}

		}
	}
}
