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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.caribe.stone.anki.Dao;
import com.caribe.stone.anki.profile.ConfigerFile;
import com.caribe.stone.anki.profile.Office;

public class WordDemo {
	private static final String US = "";
	public static String JDBC_URL;
	private static String newPath;
	private static String letterPath;
	private static String path;
	private static Map<String, File> MediaFileMap = new HashMap<String, File>();
	private static String voice;
	private static Map<String, File> letterMaps;

	private static List<String> ignorList;
	private static List<Card> spellingCards;
	private static String ignorePath;
	private static Date date = new Date(System.currentTimeMillis() - 1000L * 60 * 60 * 4);
	private static boolean updatePhonetic;
	private static int spellingMaxDay;
	private static Long deckId;
	private static String mediaPath;
	private static boolean updateJiong;
	private static List<String> jiongList;
	private static String jiongPath;
	private static List<String> jiongWordList;
	private static String jiongWordPath;
	private static String cardType;

	public static void main(String[] args) throws IOException {
		setPath(new Office());
		File ignoreFile = new File(ignorePath);
		ignorList = getIgnoreFile(ignoreFile);

		File jiongFile = new File(jiongPath);
		jiongList = getIgnoreFile(jiongFile);

		File jiongWordFile = new File(jiongWordPath);
		jiongWordList = getIgnoreFile(jiongWordFile);

		File[] listFiles = new File(letterPath).listFiles();
		letterMaps = new HashMap<String, File>();

		for (File file : listFiles) {
			letterMaps.put(file.getName().split("\\.")[0], file);
		}
		spellingCards = getSpellingCards(spellingMaxDay);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");
		newPath = path + sdf.format(date);
		File file2 = new File(newPath);
		file2.mkdirs();
		File f = new File(path);
		addFiles(f);

		System.out.println("spelling:" + spellingCards);
		execute();

		System.out.println("ignorList" + ignorList);
		FileUtils.writeLines(ignoreFile, ignorList);

		System.out.println("over~" + new Date());
	}

	private static List<String> getIgnoreFile(File file) throws IOException {
		if (!file.exists()) {
			file.createNewFile();
		}
		return FileUtils.readLines(file);
	}

	public static void setPath(ConfigerFile office) {
		path = office.getPath();
		voice = office.getVoice();

		letterPath = office.getLetterPath();
		ignorePath = office.getIgnorPath();
		JDBC_URL = office.getJDBC_URL();

		updatePhonetic = office.isUpdatePhonetic();
		spellingMaxDay = office.getSpellingMaxDay();
		deckId = office.getDeckId();
		mediaPath = office.getMediaPath();
		updateJiong = office.isUpdateJiong();
		jiongPath = office.getJiongPath();
		jiongWordPath = office.getJiongWordPath();
		cardType = office.getCardType();
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
				} else {
					addFiles(file2);
				}
			}
		} else {
			MediaFileMap.put(file.getName(), file);
		}
	}

	private static void execute() throws IOException {
		// List<Card> allWord = getAllCard();
		List<Card> allWord = getNDaysReviewedCards(1);
		List<Card> newWords = getNDaysNewCards(1);
		allWord.addAll(newWords);
		// List<Card> allWord = new ArrayList();
		// allWord.add(new Card(1358235414897L, "complete (v-link ADJ)"));
		for (Card card : allWord) {
			if (card != null) {
				updateCard(card);
				downLoadVoice(card);
				if (spellingCards.contains(card)) {
					if (card.getWord().trim().indexOf(" ") < 0 && card.getWord().trim().indexOf("-") < 0) {
						if (MediaFileMap.get(card + ".wav") != null) {
							spellWord(card.getWord(), MediaFileMap.get(card + ".wav"));
						}
					}
				}

				if (updatePhonetic) {
					String content = getCardContent(card);
					if (content != null) {
						updateWord(content, card);
					}
				}

			}

		}
		List<Card> todayCards = getNDaysReviewedCards(1);
		System.out.println("Today:" + todayCards.size() + "  " + todayCards);

		for (Card card : todayCards) {
			updateMap(card);
		}
	}

	public static String getCardJiongContent(Card card) {

		String word = card.getWord();
		if (jiongList.contains(word)) {
			return null;
		}
		jiongList.add(word);
		if (word.length() != word.getBytes().length) {
			return null;
		}
		if (word.length() == 0) {
			return null;
		}
		Connection con = null;
		try {
			con = getSqlConnection();
			PreparedStatement stmt = con.prepareStatement("select flds from notes where id =?");
			stmt.setLong(1, card.getId());
			stmt.execute();
			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {
				String string = rs.getString(1);

				StringBuffer buf = new StringBuffer();
				String[] array = string.split("");
				if (array.length >= 1) {
					if (card != null && word != null) {
						Map<Integer, String> map = new HashMap<Integer, String>();

						if (cardType.equals("bruce")) {
							if (array.length >= 4) {
								if (array[3] != null && array[3].endsWith(JiongCi.flag)) {
									return null;
								}
								map.put(3, array[3] + getJiongContentFromFile(card.getWord()));
							} else {
								map.put(3, getJiongContentFromFile(card.getWord()));
							}

							map.put(0, array[0]);

							map.put(1, array[1]);
							map.put(2, array[2]);

							// StringBuffer buf = new StringBuffer();
							for (int i = 0; i < 4; i++) {
								if (null != map.get(i)) {
									buf.append(map.get(i));
								} else {
									buf.append("");
								}
								if (i != 3) {
									buf.append(US);
								}
							}
						} else if (cardType.equals("fiona")) {
							buf.append(array[0]).append(US);
							if (array.length >= 2) {
								buf.append(array[1]).append(US);
							} else {
								buf.append(US);
							}
							if (array.length == 3) {
								if (array[2] != null && array[2].endsWith(JiongCi.flag)) {
									return null;
								}
								buf.append(array[2] + getJiongContentFromFile(card.getWord()));
							} else {
								buf.append(getJiongContentFromFile(card.getWord()));
							}

						}

					}
				}
				return buf.toString();
			}
		} catch (ClassNotFoundException e) {
			System.out.println(word);
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println(word);
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	private static String getJiongContentFromFile(String word) {
		File file = new File("word/" + word.charAt(0) + "/" + word + ".html");
		if (file.exists()) {
			try {
				Document doc = Jsoup.parse(file, "utf-8");
				Elements select = doc.getElementsByAttributeValue("style",
						"margin-right:5px;font-size:14px/30px;padding:10px 0px;");
				JiongCi ci = new JiongCi();
				Elements eles = doc.getElementsByAttributeValue("style", "max-width:100%;");
				if (eles.size() > 1 && eles.get(0) != null) {
					Element e = eles.get(0);
					String src = e.attr("src");
					if (src.endsWith("png")) {
						String imgName = src.substring(src.lastIndexOf("/") + 1, src.length());
						WordDemo.httpDownload(src, mediaPath + "/" + imgName);
						ci.setPng(imgName);
						eles.remove(e);
					}
				}

				if (eles.size() != select.size()) {
					System.out.println("eles.size()!=select.size()");
				}

				for (int i = 0; i < select.size(); i++) {
					JiongCiExplain explain = new JiongCiExplain();
					explain.setExplain(select.get(i).text());
					String src = eles.get(i).attr("src");
					String imgName = src.substring(src.lastIndexOf("/") + 1, src.length());
					WordDemo.httpDownload(src, mediaPath + "/" + imgName);
					explain.setImg(imgName);
					ci.getExplainList().add(explain);
				}
				return ci.getContent();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println(word + " not exists.");
		}
		return "";
	}

	private static void updateWord(String content, Card card) {

		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = getSqlConnection();
			// String sql = "update notes set flds='" + content +
			// "' where sfld='" + card + "'";
			String sql = "update notes set flds= ? where id= ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, content);
			stmt.setLong(2, card.getId());
			boolean result = stmt.execute();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public static String getCardContent(Card card) {

		String word = card.getWord();
		if (word.length() != word.getBytes().length) {
			return null;
		}
		// if (card.getWord().indexOf(" ") > 0) {
		// return null;
		// }
		Connection con = null;
		try {
			con = getSqlConnection();
			PreparedStatement stmt = con.prepareStatement("select flds from notes where id =?");
			stmt.setLong(1, card.getId());
			stmt.execute();
			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {
				String string = rs.getString(1);

				String[] s = string.split("");
				if (s.length >= 1) {
					// String word = getCard(s[0]);
					if (card != null) {
						// String audio = getAudioField(card.getWord());
						Map<Integer, String> map = new HashMap<Integer, String>();
						for (int i = 0; i < s.length; i++) {
							map.put(i, s[i]);
						}
						if (map.get(1) != null && map.get(1).trim().length() > 0) {
							return null;
						}
						map.put(0, s[0]);
						if (null == map.get(1) || map.get(1).trim().length() == 0) {
							String phonetic = InputCardDemo.getPhonetic(word);
							if (!isDownloadAudio(phonetic)) {
								return null;
							}
							map.put(1, phonetic);
						}
						StringBuffer buf = new StringBuffer();
						for (int i = 0; i < 4; i++) {
							if (null != map.get(i)) {
								buf.append(map.get(i));
							} else {
								buf.append("");
							}
							if (i != 3) {
								buf.append(US);
							}
						}
						// System.out.println(buf);
						return buf.toString();
					}
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	private static String getAudioField(String word) {
		String audio = "[sound:" + getAudioFilePath(word, "mp3") + "]";
		return audio;
	}

	private static String getAudioFilePath(String word, String suffix) {
		String getAudioFilePath = word + "." + suffix;
		return getAudioFilePath;
	}

	private static Card getCard(Long id, String oldWord) {
		Card card = new Card(id, "");
		card.setOldWord(new String(oldWord));

		if (id == null) {
			return null;
		}
		if (oldWord == null) {
			return null;
		}
		oldWord = oldWord.replaceAll("&nbsp;", "");
		oldWord = oldWord.replaceAll("\r", "");
		oldWord = oldWord.replaceAll("\n", "");
		oldWord = oldWord.replaceAll(" ", "");
		if (oldWord.indexOf("/") > 0) {
			card.setUpdate(true);
			oldWord = oldWord.replaceAll("/", " or ");
		}
		oldWord = oldWord.trim();
		if (!isDownloadAudio(oldWord)) {
			return null;
		}
		while (oldWord.indexOf("<") >= 0) {
			oldWord = oldWord.replace(oldWord.substring(oldWord.indexOf("<"), oldWord.indexOf(">") + 1), "");
		}

		if (oldWord.getBytes().length != oldWord.length()) {
			return null;
		}

		card.setWord(new String(oldWord));
		return card;
	}

	private static void updateMap(Card card) throws IOException {
		if (card == null) {
			return;
		}
		String word = card.getWord();

		File file = new File(mediaPath + "/" + word + ".mp3");
		if (file.exists()) {
			File destFile = new File(newPath + file.getName());
			if (!destFile.exists()) {
				FileUtils.copyFile(file, destFile);
			}
		}
	}

	private static void getFromDirectionary(String word) throws IOException {
		if (MediaFileMap.get(word + "-d.mp3") != null) {
			File oldFile = MediaFileMap.get(word + "-d.mp3");
			File destFile = new File(newPath + oldFile.getName());
			if (!destFile.exists()) {
				FileUtils.moveFile(oldFile, destFile);
			}
		}
	}

	private static void getFromGA(String word) throws IOException {
		if (MediaFileMap.get(word + "-ga.mp3") != null) {
			File oldFile = MediaFileMap.get(word + "-ga.mp3");
			File destFile = new File(newPath + oldFile.getName());
			if (!destFile.exists()) {
				FileUtils.moveFile(oldFile, destFile);
			}
		}
	}

	private static void getFromA(String word) throws IOException {
		if (MediaFileMap.get(word + "-A.mp3") != null) {
			File oldFile = MediaFileMap.get(word + "-A.mp3");
			File destFile = new File(newPath + oldFile.getName());
			if (!destFile.exists()) {
				FileUtils.moveFile(oldFile, destFile);
			}
		}
	}

	private static void getFromB(String word) throws IOException {
		if (MediaFileMap.get(word + "-B.mp3") != null) {
			File oldFile = MediaFileMap.get(word + "-B.mp3");
			File destFile = new File(newPath + oldFile.getName());
			if (!destFile.exists()) {
				FileUtils.moveFile(oldFile, destFile);
			}
		}
	}

	private static List<Card> getAllCard() throws IOException {
		List<Card> readLines = new LinkedList<Card>();
		Connection conn = null;
		Statement stat;
		ResultSet rs;
		try {
			conn = getSqlConnection();

			stat = conn.createStatement();
			String sql = "select id,sfld from notes ";
			if (deckId != 0) {
				sql += " where id in (select nid from cards where did=1)";
			}
			System.out.println("Get all word SQL: " + sql);
			stat.execute(sql);
			rs = stat.getResultSet();
			while (rs.next()) {
				Card word = getCard(rs.getLong(1), rs.getString(2));
				if (word != null) {
					readLines.add(word);
				}
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

	public static List<Card> getNDaysNewCards(int days) {
		List<Card> list = new LinkedList<Card>();
		Connection con = null;
		try {
			con = WordDemo.getSqlConnection();
			String sql = "select n.id,n.sfld from  notes n, cards c where ";
			if (deckId != 0) {
				sql = sql + " did=" + deckId + " and ";
			}
			sql = sql + " c.nid=n.id and n.id> " + (System.currentTimeMillis() - 1000L * 60 * 60 * 24 * days);
			System.out.println("getNDaysNewCards SQL:" + sql);
			Statement stmt = con.createStatement();
			stmt.execute(sql);
			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {
				list.add(getCard(rs.getLong(1), rs.getString(2)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) { // TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	public static void updateCard(Card card) {
		if (card.isUpdate()) {
			Connection con = null;
			try {
				con = WordDemo.getSqlConnection();

				PreparedStatement psmt = null;
				psmt = con.prepareStatement("select flds from notes where id = ?");
				psmt.setLong(1, card.getId());

				psmt.execute();
				String flds = null;
				ResultSet rs = psmt.getResultSet();
				while (rs.next()) {
					flds = rs.getString(1);
				}
				StringBuffer buf = new StringBuffer();
				if (flds != null) {
					String[] s = flds.split("");
					s[0] = card.getWord();
					for (String str : s) {
						buf.append(str).append("");
					}
				}
				String sql = "update notes set flds= ?,sfld=? where id= ?";
				System.out.println("updateCard SQL:" + sql);
				System.out.println(buf);
				psmt = con.prepareStatement(sql);

				psmt.setString(1, buf.toString());
				psmt.setString(2, card.getWord());
				psmt.setLong(3, card.getId());
				psmt.execute();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (con != null) {
					try {
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	public static List<Card> getNDaysReviewedCards(int days) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmdd");
		String format = sdf.format(new Date(date.getTime() - 1000L * 60 * 60 * 24 * days));
		long time = 0;
		try {
			time = sdf.parse(format).getTime() - 1000L * 60 * 60 * 16;
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		List<Card> list = new LinkedList<Card>();
		Connection con = null;
		try {
			// select n.id,n.sfld from notes n,cards c where c.did=1 and
			// n.id=c.id and exists
			// (select * from revlog r where r.id>1369722600000 and r.cid=c.id)
			con = WordDemo.getSqlConnection();
			String sql = "select n.id,n.sfld from notes n,cards c where c.did=" + deckId
					+ " and n.id=c.nid and exists ";
			sql = sql + " (select * from revlog r where  r.cid=c.id and " + " did=" + deckId + " and " + "r.id>" + time
					+ " ) ";
			System.out.println("getNDaysReviewedCards SQL:" + sql);
			Statement stmt = con.createStatement();
			stmt.execute(sql);
			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {
				list.add(getCard(rs.getLong(1), rs.getString(2)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) { // TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	public static Connection getSqlConnection() throws ClassNotFoundException, SQLException {
		Connection conn = null;
		return conn;
	}

	private static List<Card> getSpellingCards(int day) {
		List<Card> list = new LinkedList<Card>();
		Connection conn = null;
		ResultSet rs;
		try {
			conn = WordDemo.getSqlConnection();
			String sql = "select id,sfld from notes where tags like '%marked%' " + "union all"
					+ " select n.id, n.sfld from notes n, cards c where c.nid=n.id ";
			if (deckId != 0) {
				sql = sql + " and c.did=" + deckId;
			}
			System.out.println("Get spelling card SQL:" + sql);
			Statement stmt = conn.createStatement();
			stmt.execute(sql);
			rs = stmt.getResultSet();
			while (rs.next()) {
				list.add(getCard(rs.getLong(1), rs.getString(2)));
			}

			List<Card> newList = new LinkedList<Card>();
			for (Card card : list) {
				if (card != null) {
					Long obj = Long.valueOf(card.getId()) + 1000L * 60 * 60 * 24 * day;
					if (obj > date.getTime()) {
						newList.add(card);
					}
				}
			}
			return newList;

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
		return null;
	}

	private static void spellWord(String word, File file) throws FileNotFoundException, IOException {
		List<File> letterList = trimWord(word);
		String name = file.getParent() + "/" + word + "-sp.mp3";
		File file1 = new File(name);
		// file1.delete();
		if (!file1.exists()) {
			FileOutputStream out = new FileOutputStream(file1);
			// if (file.getName().endsWith("-B.mp3") ||
			// file.getName().endsWith("-A.mp3")) {
			// letterList.add(0, file);
			// }
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

	public static void downLoadVoice(Card card) {
		String word = card.getWord();

		if (isDownloadAudio(word)) {

			File mediaFile = getMediaFilePath(word);

			if (mediaFile.exists()) {
				System.out.println(mediaFile + " exist.");
				return;
			}

			String string = word + "-rp.mp3";
			String string2 = word + "-ga.mp3";
			File srcFile = MediaFileMap.get(string);
			if (MediaFileMap.get(string) == null) {
				String searchWord = getSearchWord(word);
				File wordKing = getRPFromICB(searchWord);
				MediaFileMap.put(string, wordKing);
				srcFile = wordKing;
			}

			if (MediaFileMap.get(string2) == null) {
				String searchWord = getSearchWord(word);
				File wordKing = getGAFromICB(searchWord);
				MediaFileMap.put(string2, wordKing);
			}

			if (MediaFileMap.get(string) != null && MediaFileMap.get(string2) != null) {
				if (MediaFileMap.get(string).length() > MediaFileMap.get(string2).length()) {
					srcFile = MediaFileMap.get(string2);
				}
			} else {
				srcFile = MediaFileMap.get(string) == null ? MediaFileMap.get(string2) : MediaFileMap.get(string);
			}

			String string5 = word + ".wav";
			if (MediaFileMap.get(string5) == null) {
				String searchWord = getSearchWord(word);
				File mediaFileWAV = new File(voice + word.charAt(0) + "/" + searchWord);
				File destFile = new File(newPath + string5);
				copyFile(mediaFileWAV, destFile);
			}

			if (srcFile != null && !mediaFile.exists()) {
				try {
					FileUtils.copyFile(srcFile, mediaFile);
					System.out.println(mediaFile);
					System.out.println(mediaFile);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public static boolean isDownloadAudio(String word) {

		if (word == null || word.length() == 0) {
			return false;
		}
		if (word.indexOf("(") > 0 && !word.endsWith(")")) {
			return false;
		}
		if (word.startsWith("to ")) {
			return false;
		}
		return true;
	}

	private static String getSearchWord(String word) {
		int indexOf = word.indexOf('(');
		String searchWord = word;
		if (indexOf > 0) {
			searchWord = word.substring(0, indexOf - 1);
		}
		return searchWord;
	}

	public static File getGAFromICB(String word) {
		File wordKing = getWordKing(word, "a.vCri_laba", "-ga");
		return wordKing;
	}

	public static File getRPFromICB(String word) {
		File wordKing = getWordKing(word, "a.ico_sound[title=真人发音]", "-rp");
		return wordKing;
	}

	private static File getMediaFilePath(String word) {
		File mediaFile = new File(mediaPath + getAudioFilePath(word, "mp3"));
		return mediaFile;
	}

	public static void copyFile(File srcFile, File destFile) {
		if (srcFile.exists()) {
			try {
				FileUtils.copyFile(srcFile, destFile);
				MediaFileMap.put(destFile.getName(), destFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static File getWordKing(String word, String position, String suffix) {
		if (ignorList != null && ignorList.contains(word + suffix)) {
			return null;
		}
		String url = "http://www.iciba.com/search?s=" + word;
		Elements links = null;
		try {
			links = Jsoup.connect(url).userAgent("Mozilla").timeout(5000).get().select(position);
		} catch (IOException e) {
			if (ignorList != null) {
				ignorList.add(word + suffix);
				System.out.println("get voice from iciba error : " + word + suffix);
				System.out.println(e.getMessage());
			}
		}
		String saveFile = newPath + word + suffix + ".mp3";
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

class Card {
	private Long id;
	private String word;
	private String oldWord;

	public String getOldWord() {
		return oldWord;
	}

	public void setOldWord(String oldWord) {
		this.oldWord = oldWord;
	}

	private boolean update = false;

	public boolean isUpdate() {
		return update;
	}

	public void setUpdate(boolean isUpdate) {
		this.update = isUpdate;
	}

	public Card(Long id, String word) {
		this(id, word, false);
	}

	public Card(Long id, String word, boolean update) {
		this.id = id;
		this.word = word;
		this.update = update;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Card [id=" + id + ", word=" + word + ", oldWord=" + oldWord + ", update=" + update + "]";
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}
}