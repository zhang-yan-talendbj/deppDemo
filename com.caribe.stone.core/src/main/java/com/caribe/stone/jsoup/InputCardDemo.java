package com.caribe.stone.jsoup;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class InputCardDemo {

	public static void main(String[] args) throws IOException {
		for (int i = 228; i < 800; i++) {
			converToTxt(String.valueOf(i), "d:/esl/anki/podcast" + i + ".txt");
		}
		// for (int i = 100; i < 800; i++) {
		// String textFromPDF = getTextFromPDF(String.valueOf(i));
		// if(textFromPDF!=null){
		//
		// File file = new File("d:/esl/podcast" + i + ".txt");
		// Writer output = new FileWriter(file);
		// IOUtils.write(textFromPDF, output);
		// }
		// }
	}

	private static void converToTxt(String eslNumber, String fileName) throws IOException {
		String cards = getCards(eslNumber);
		if (cards == null) {
			return;
		}
		File file = new File(fileName);
		if (!file.exists()) {
			file.createNewFile();
		}
		Writer output = new FileWriter(file);
		IOUtils.write(cards.getBytes(), output, "utf-8");
		IOUtils.closeQuietly(output);
	}

	private static String getCards(String eslNumber) {
		StringBuffer sb = new StringBuffer();
		String textFromPDF = getTextFromPDF(eslNumber);
		if (textFromPDF == null) {
			return null;
		}
		String[] split = textFromPDF.split("______________");
		String[] x = split[0].split("GLOSSARY");
		String[] split2 = x[1].split(" \r\n ");
		for (String string : split2) {
			if (string.length() > 0) {
				if (!string.equals("\r\n")) {
					string = string.replace("\r\n", "");
					string = string
							.replace(
									"These materials are copyrighted by the Center for Educational Development (2006).  Posting of these materials on another website or distributing them in any way is prohibited. 2",
									"");
					boolean b = !string.startsWith("These materials are copyrighted ") || string.indexOf("*") > 0;
					if (!string.equals("\r\n \r\n") && !string.startsWith(" English as a Second Language")
							&& !string.startsWith(" ESL Podcast") && b) {
						if (string.startsWith("These materials are copyrighted ") && string.indexOf("*") > 0) {
							System.out.println(string);
						}
						String[] split3 = string.split("\\*");
						if (split3.length >= 2) {

							String string2 = split3[0];
							String[] split4 = null;
							if (string2.indexOf("–") > 0) {
								split4 = string2.split("–");
							} else if (string2.indexOf("-") > 0) {
								split4 = string2.split("-");
							}
							if (split4 != null && split4.length == 2) {

								String front = split4[0];
								String back = split4[1];

								String example = split3[1];
								if (front != null && back != null && example != null) {
									String word = split4[0].trim();
									sb.append(word + "\t");
									String phonetic = getPhonetic(word);
									if (phonetic == null) {
										phonetic = "";
									}
									sb.append(phonetic + "\t");
									sb.append(split4[1] + "\t");
									sb.append(split3[1] + "\r\n");
								}
							}
						}
					}
				}
			}
		}
		return sb.toString();
	}

	public static String getPhonetic(String word) {
		if (word.indexOf('(') > 0) {
			System.out.println(word);
			word = word.substring(0, word.indexOf('(') );
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

	public static String getTextFromPDF(String eslNumber) {
		String result = null;
		FileInputStream is = null;
		PDDocument document = null;

		File eslFile = getFile(eslNumber);
		if (eslFile == null)
			return null;
		System.out.println(eslFile);
		try {
			is = new FileInputStream(eslFile);
			PDFParser parser = new PDFParser(is);
			parser.parse();
			document = parser.getPDDocument();
			PDFTextStripper stripper = new PDFTextStripper();
			// PDFText2HTML h=new PDFText2HTML("utf-8");
			// System.out.println(h.getText(document));
			result = stripper.getText(document);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (document != null) {
				try {
					document.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	private static File getFile(String eslNumber) {
		String path = "d:/english/ESL播客课程2006年度合集/";
		File dir = new File(path);
		File[] listFiles = dir.listFiles();
		for (File file : listFiles) {
			if (file.getName().indexOf(eslNumber) > 0 && file.getName().endsWith("pdf")) {
				return file;
			}
		}
		return null;
	}
}