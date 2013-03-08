package com.caribe.stone.jsoup;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class WordDemo {
	private static final String JDBC_URL = "c:/Users/bsnpbag/Documents/Anki/User 1/collection.anki2";
	private static String newPath;
	private static String letterPath;
	private static String path;
	private static Map<String, File> fileList = new HashMap<String, File>();
	private static String voice;
	private static String britishVoice;
	private static String americanVoice;
	private static Map<String, File> letterMaps;

	private static List ignorList;
	private static List<String> spellingCards;
	private static String pathname;

	public static void main(String[] args) throws IOException {

		path = "d:/english/AnkiWord/voice/";
		voice = "d:/Program Files/Lingoes/Translator2/voice/";
		britishVoice = "d:/english/voice/British/";
		americanVoice = "d:/english/voice/American/";
		letterPath = "d:/english/AnkiWord/voice/letter/";

		pathname = "d:/english/AnkiWord/voice/ignore";
		
		
		File ignoreFile = new File(pathname);
		ignorList = FileUtils.readLines(ignoreFile);
		File letterFiles = new File(letterPath);
		File[] listFiles = letterFiles.listFiles();
		letterMaps = new HashMap<String, File>();

		for (File file : listFiles) {
			letterMaps.put(file.getName().split("\\.")[0], file);
		}
		// if(true){
		// return ;
		// }
		spellingCards = getSpellingCards();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");
		newPath = path + sdf.format(new Date());
		File file2 = new File(newPath);
		file2.mkdirs();
		File f = new File(path);
		addFiles(f);
		
		System.out.println("spelling:"+spellingCards);
		execute();

		FileUtils.writeLines(ignoreFile, ignorList);

		System.out.println("over~" + new Date());
	}

	public static void addFiles(File file) {
		if (file.isDirectory()) {
			File[] listFiles = file.listFiles();
			for (File file2 : listFiles) {
				String name = file2.getName();
				if (name.endsWith("-sp.mp3")) {
					if (!spellingCards.contains(name.replace(".-sp.mp3", ""))) {
						file2.delete();
					}
				}else{
					addFiles(file2);
				}
			}
		} else {
			fileList.put(file.getName(), file);
		}
	}

	private static void execute() throws IOException {
		List<String> readLines = getCardFromDB();
		for (String word : readLines) {
			word = getWord(word);
			while (word.indexOf("<") >= 0) {
				word = word.replace(word.substring(word.indexOf("<"), word.indexOf(">") + 1), "");
			}
			if (word != null) {
				downLoadVoice(word);
				if (spellingCards.contains(word)) {
					System.out.println(word);
					if (word.trim().indexOf(" ") < 0 && word.trim().indexOf("-") < 0 && word.trim().indexOf("(") < 0) {
						if (fileList.get(word + "-B.mp3") != null) {
							spellWord(word, fileList.get(word + "-B.mp3"));
						} else if (fileList.get(word + "-A.mp3") != null) {
							spellWord(word, fileList.get(word + "-A.mp3"));
						} else if (fileList.get(word + "-rp.mp3") != null) {
							spellWord(word, fileList.get(word + "-rp.mp3"));
						} else if (fileList.get(word + "-ga.mp3") != null) {
							spellWord(word, fileList.get(word + "-ga.mp3"));
						} else if (fileList.get(word + "-d.mp3") != null) {
							spellWord(word, fileList.get(word + "-d.mp3"));
						}
					}
				}

			}
		}

		List<String> todayCards = getTodayCards();
		System.out.println("Today:" + todayCards);
		for (String string : todayCards) {
			bb(string);
		}
	}

	private static String getWord(String word) {
		word = word.replaceAll("&nbsp;", "");
		word = word.replaceAll("\r", "");
		word = word.replaceAll("\n", "");
		word = word.trim();
		return word;
	}

	private static void bb(String word) throws IOException {
//		 getFromB(word);
//		getFromA(word);
		if (fileList.get(word + "-rp.mp3") != null) {
			File oldFile = fileList.get(word + "-rp.mp3");
			File destFile = new File(newPath + oldFile.getName());
			if (!destFile.exists()) {
				FileUtils.moveFile(oldFile, destFile);
			}
		}
		// getFromGA(word);
		// getFromDirectionary(word);
		if (fileList.get(word + ".wav") != null) {
			File oldFile = fileList.get(word + ".wav");
			File destFile = new File(newPath + oldFile.getName());
			if (!destFile.exists()) {
				FileUtils.moveFile(oldFile, destFile);
			}
		}
	}

	private static void getFromDirectionary(String word) throws IOException {
		if (fileList.get(word + "-d.mp3") != null) {
			File oldFile = fileList.get(word + "-d.mp3");
			File destFile = new File(newPath + oldFile.getName());
			if (!destFile.exists()) {
				FileUtils.moveFile(oldFile, destFile);
			}
		}
	}

	private static void getFromGA(String word) throws IOException {
		if (fileList.get(word + "-ga.mp3") != null) {
			File oldFile = fileList.get(word + "-ga.mp3");
			File destFile = new File(newPath + oldFile.getName());
			if (!destFile.exists()) {
				FileUtils.moveFile(oldFile, destFile);
			}
		}
	}

	private static void getFromA(String word) throws IOException {
		if (fileList.get(word + "-A.mp3") != null) {
			File oldFile = fileList.get(word + "-A.mp3");
			File destFile = new File(newPath + oldFile.getName());
			if (!destFile.exists()) {
				FileUtils.moveFile(oldFile, destFile);
			}
		}
	}

	private static void getFromB(String word) throws IOException {
		if (fileList.get(word + "-B.mp3") != null) {
			File oldFile = fileList.get(word + "-B.mp3");
			File destFile = new File(newPath + oldFile.getName());
			if (!destFile.exists()) {
				FileUtils.moveFile(oldFile, destFile);
			}
		}
	}

	private static List<String> getCardFromDB() throws IOException {
		List<String> readLines = new LinkedList<String>();
		Connection conn = null;
		Statement stat;
		ResultSet rs;
		try {
			conn = getSqlConnection();

			stat = conn.createStatement();
			stat.execute("select sfld from notes where id in (select nid from cards where did=1)");
			rs = stat.getResultSet();
			while (rs.next()) {
				readLines.add(getWord(rs.getString(1)));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return readLines;
	}

	private static List<String> getTodayCards() throws IOException {
		List<String> readLines = new LinkedList<String>();
		Connection conn = null;
		Statement stat;
		ResultSet rs;
		try {
			conn = getSqlConnection();

			stat = conn.createStatement();
			stat.execute("select sfld from notes where tags like '%today%'");
			rs = stat.getResultSet();
			while (rs.next()) {
				readLines.add(rs.getString(1).trim());
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return readLines;
	}

	private static Connection getSqlConnection() throws ClassNotFoundException, SQLException {
		Connection conn;
		Class.forName("org.sqlite.JDBC");
		conn = DriverManager.getConnection("jdbc:sqlite:" +
				JDBC_URL);
		return conn;
	}

	private static List<String> getSpellingCards() {
		List<String> readLines = new LinkedList<String>();
		Connection conn = null;
		Statement stat;
		ResultSet rs;
		try {
			conn = getSqlConnection();

			stat = conn.createStatement();
			stat.execute("select sfld from notes where tags like '%marked%'");
			rs = stat.getResultSet();
			while (rs.next()) {
				readLines.add(rs.getString(1).trim());
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return readLines;
	}

	private static void spellWord(String word, File file) throws FileNotFoundException, IOException {
		System.out.println(file);
		List<File> letterList = trimWord(word);
		String name = file.getParent() + "/" + word + "-sp.mp3";
		File file1 = new File(name);
		// file1.delete();
		if (!file1.exists()) {
			FileOutputStream out = new FileOutputStream(file1);
//			if (file.getName().endsWith("-B.mp3") || file.getName().endsWith("-A.mp3")) {
//				letterList.add(0, file);
//			}
			merge(letterList, out);
		}
	}

	public static List<File> trimWord(String word) {
		String word1 = word;
		word1 = word1.trim();
		word1 = word1.toLowerCase();
		word1 = word1.replace("-", " ");
		String[] words = word1.split(" ");

		List<File> lists = new LinkedList<File>();
		for (String string : words) {
			if (letterMaps.get(string) != null) {
				lists.add(letterMaps.get(string));
			} else {
				// lists.add(letterMaps.get("blank"));
				for (int i = 0; i < string.length(); i++) {
					String letter = String.valueOf(string.charAt(i));
					if ("(".equals(letter)) {
						lists.add(letterMaps.get("bracket"));
					} else if (")".equals(letter)) {
						lists.add(letterMaps.get("bracket"));
					} else {
						lists.add(letterMaps.get(letter));
					}
				}
			}
		}
		return lists;
	}

	public static void downLoadVoice(String word) {

		if (word.trim().indexOf(" ") < 0 && word.trim().indexOf("-") < 0 && word.trim().indexOf("(") < 0) {
//			String fileName = word + "-d.mp3";
//			if (fileList.get(fileName) == null) {
//				getWordDictionary(word, "span.speaker", "-d");
//			}
			String string = word + "-rp.mp3";
			if (fileList.get(string) == null) {
				getWordKing(word, "a.ico_sound[title=真人发音]", "-rp");
			}
//			String string2 = word + "-ga.mp3";
//			if (fileList.get(string2) == null) {
//				getWordKing(word, "a.vCri_laba", "-ga");
//			}
		}
//		String string3 = word + "-A.mp3";
//		if (fileList.get(string3) == null) {
//			File srcFile = new File(americanVoice + word.charAt(0) + "/" + word + ".mp3");
//			File destFile = new File(newPath + string3);
//			copyFile(srcFile, destFile);
//
//		}
//		String string4 = word + "-B.mp3";
//		if (fileList.get(string4) == null) {
//			File srcFile = new File(britishVoice + word.charAt(0) + "/" + word + ".mp3");
//			File destFile = new File(newPath + string4);
//			copyFile(srcFile, destFile);
//		}

		String string5 = word + ".wav";
		if (fileList.get(string5) == null) {
			File srcFile = new File(voice + word.charAt(0) + "/" + string5);
			File destFile = new File(newPath + string5);
			copyFile(srcFile, destFile);
		}

		// getWordYouDao(word, "span.speaker", "-yd");
		// links = Jsoup.connect(url).get().select("a.vCri_laba");
	}

	public static void copyFile(File srcFile, File destFile) {
		if (srcFile.exists()) {
			try {
				FileUtils.copyFile(srcFile, destFile);
				fileList.put(destFile.getName(), destFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void getWordKing(String word, String position, String suffix) {
		String url = "http://www.iciba.com/search?s=" + word;
		Elements links = null;
		try {
			links = Jsoup.connect(url).userAgent("Mozilla").timeout(5000).get().select(position);
		} catch (IOException e) {
			System.out.println(word);
			System.out.println(e.getMessage());
		}
		String saveFile = newPath + word + suffix + ".mp3";
		if (links != null) {
			String attr = links.attr("onclick");
			if (attr != null && attr.indexOf("'") >= 0) {
				String[] split = attr.split("'");
				System.out.println(saveFile);
				httpDownload(split[1], saveFile);
			}
		}
	}

	public static void getWordDictionary(String word, String position, String suffix) {
		if (ignorList.contains(word)) {
			return;
		}
		if (word.trim().indexOf(" ") > 0) {
			return;
		}
		String url = "http://dictionary.reference.com/browse/" + word;
		Elements links = null;
		try {
			links = Jsoup.connect(url).userAgent("Mozilla").timeout(5000).get().select(position);
		} catch (IOException e) {
			System.out.println(word);
			ignorList.add(word);
			System.out.println(e.getMessage());
		}
		String saveFile = newPath + word + suffix + ".mp3";
		if (links != null) {
			String attr = links.attr("audio");
			httpDownload(attr, saveFile);
		}
	}

	public static void getWordYouDao(String word, String position, String suffix) throws IOException {
		String url = "http://dict.youdao.com/search?le=eng&q=" + word;
		Document document = Jsoup.connect(url).userAgent("Mozilla").timeout(5000).get();
		Elements links = document.select(position);
		String attr = links.attr("audio");
		httpDownload(attr, word + suffix + ".mp3");
	}

	public static void httpDownload(String httpUrl, String saveFile) {
		long start = System.currentTimeMillis();
		// 下载网络文件
		int bytesum = 0;
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
			String name = System.currentTimeMillis()+"tmp.file";
			File file = new File(name);
			try {
				URLConnection conn = url.openConnection();
				InputStream inStream = conn.getInputStream();
				fs = new FileOutputStream(file);

				byte[] buffer = new byte[1204];
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread;
					fs.write(buffer, 0, byteread);
				}
				long end = System.currentTimeMillis();
				long length = file.length();
				System.out.print(length*1d/(end-start));
				System.out.println("K/s.");
				FileUtils.copyFile(file, new File(saveFile));
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					fs.flush();
					fs.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				file.delete();
			}
			
		}
	}

	public static void merge(List<File> list, FileOutputStream fos) throws IOException {
		byte b[] = new byte[10240];
		int len = 0;

		// 将f1这个mp3的内容copy到f3中
		BufferedOutputStream out = new BufferedOutputStream(fos);
		for (File f : list) {
			if (f != null) {
				FileInputStream fis = new FileInputStream(f);
				BufferedInputStream bis = new BufferedInputStream(fis);
				while ((len = bis.read(b)) != -1) {
					out.write(b, 0, len);
				}
				fis.close();
			}
		}
		out.close();
		fos.close();
	}
}