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
import org.apache.commons.io.IOUtils;
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
		bean.setDeckId(1394371108962L);
//		bean.setDeckId(1395672679482L);
//		bean.setDeckId(1L);//ESL
		// 1395672679482 tmp
		// 1394371108962L study
		//1392112698116 zzz
		//1395208211479 cloze
		List<Note> notes = dao.getAllNotesByCondition(bean);

		cc(settings, dao, notes);

		System.out.println("Over!");
	}

	private static void cc(AnkiSettings settings, Dao dao, List<Note> notes) {
		for (Note note : notes) {
			String mediaPath = settings.getMediaPath();
			if (note.getFields() >= 3 && note.getWord().indexOf(" ")<0 && note.getWord().indexOf("-")<0 && hasntPhonetic(note)) {
				String phonetic = getPhonetic(note.getWord());
				if (phonetic == null) {
					phonetic = "";
				}
				note.setPhonetic(phonetic);
				dao.update(note);
			}
			if (note.getFieldCount() >= 3 && note.getWord().indexOf(" ")<0 && note.getWord().indexOf("-")<0 ) {

				if (!settings.containYoudao(note)) {
					// note.getWord().indexOf(" ") < 0 &&
					if (!settings.containUS(note)) {
						File mp3File;
						String saveFile = mediaPath + note.getWord() + "-us" + ".mp3";
						mp3File = getWordFromCambridge(note.getWord(), "pron-us", mediaPath, "-us");
						if (mp3File == null) {

							File ga = getGAFromICB(note.getWord(), mediaPath);
							if (ga != null) {

								try {
									// String saveFile = mediaPath +
									// note.getWord()
									// + "-us" + ".mp3";
									File destFile = new File(saveFile);
									FileUtils.moveFile(ga, destFile);

								} catch (IOException e) {
									e.printStackTrace();
								}
							} else {
								httpDownload("http://dict.youdao.com/dictvoice?audio=" + note.getWord() + "&type=2", saveFile);
							}
						}
					}
					if (!settings.containUK(note)) {
						File mp3File;
						mp3File = getWordFromCambridge(note.getWord(), "pron-uk", mediaPath, "-uk");
						if (mp3File == null) {
							String saveFile = mediaPath + note.getWord() + "-uk" + ".mp3";
							File be = getRPFromICB(note.getWord(), mediaPath);
							if (be != null) {
								try {
									FileUtils.moveFile(be, new File(saveFile));
								} catch (IOException e) {
									e.printStackTrace();
								}
							} else {
								httpDownload("http://dict.youdao.com/dictvoice?audio=" + note.getWord() + "&type=1", saveFile);
							}
						}
					}
				}
			}
		}
	}

	private static void aa() throws IOException {
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

			if (note.getFields() >= 3 && hasntPhonetic(note)) {
				String phonetic = getPhonetic(note.getWord());
				if (phonetic == null) {
					phonetic = "";
				}
				note.setPhonetic(phonetic);
				dao.update(note);
			}

			if (!settings.containGA(note)) {
				File gaFromICB = getGAFromICB(note.getWord(), mediaPath);
			}
			if (!settings.containRP(note)) {
				File getRPFromICB = getRPFromICB(note.getWord(), mediaPath);
			}
			if (!settings.containYGA(note)) {
				String saveFile = mediaPath + note.getWord() + "-y-ga.mp3";
				// http://dict.youdao.com/dictvoice?audio=soon&type=1
				httpDownload("http://dict.youdao.com/dictvoice?audio=" + note.getWord() + "&type=1", saveFile);
			}
			if (!settings.containYRP(note)) {
				String saveFile = mediaPath + note.getWord() + "-y-rp.mp3";
				httpDownload("http://dict.youdao.com/dictvoice?audio=" + note.getWord() + "&type=2", saveFile);
			}
		}

		System.out.println("Over!");
	}

	private static File getUKFromCambridge(String word, String mediaPath) {
		return getWordKing(word, "a.vCri_laba", mediaPath, "-ga");
	}

	private static boolean hasntPhonetic(Note note) {
		// return true;
		return note.getPhonetic() == null || note.getPhonetic().length() == 0 || note.getPhonetic().replace(" ", "").length() == 0;
	}

	public static String getPhonetic(String word) {
//		if (word.indexOf('(') > 0) {
//			System.out.println(word);
//			word = word.substring(0, word.indexOf('('));
//			System.out.println(word);
//		}
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

	private static File getWordKing(String word, String selecter, String path, String suffix) {
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
				httpDownload(split[1], saveFile);
				return new File(saveFile);
			}
		}
		return null;
	}

	static File getWordFromCambridge(String word, String selecter, String path, String suffix) {
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
				httpDownload(mp3File, saveFile);
				return new File(saveFile);
			}
		}
		return null;
	}

	public static File getGAFromICB(String word, String mediaPath) {
		return getWordKing(word, "a.vCri_laba", mediaPath, "-ga");
	}

	public static File getRPFromICB(String word, String mediaPath) {
		return getWordKing(word, "a.ico_sound[title=真人发音]", mediaPath, "-rp");
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
					// System.out.print((int) (file.length() * 1d / (end -
					// start)));
					// System.out.println("K/s.");

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
