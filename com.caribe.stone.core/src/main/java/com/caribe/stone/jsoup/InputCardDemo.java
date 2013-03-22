package com.caribe.stone.jsoup;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.apache.commons.io.IOUtils;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class InputCardDemo {

	public static void main(String[] args) throws IOException {
		String eslNumber = "168";
		String cards = getCards(eslNumber);
		Writer output = new FileWriter("d:/test.txt");
		IOUtils.write(cards.getBytes(), output, "utf-8");
		IOUtils.closeQuietly(output);
	}

	private static String getCards(String eslNumber) {
		StringBuffer sb = new StringBuffer();
		String textFromPDF = getTextFromPDF(eslNumber);
		String[] split = textFromPDF.split("______________");
		String[] x = split[0].split("GLOSSARY");
		String[] split2 = x[1].split(" \r\n ");
		for (String string : split2) {
			if (string.length() > 0) {
				if (!string.equals("\r\n")) {
					string = string.replace("\r\n", "");
					boolean b = !string.startsWith("These materials are copyrighted ") || string.indexOf("*") > 0;
					if (!string.equals("\r\n \r\n") && !string.startsWith(" English as a Second Language")
							&& !string.startsWith(" ESL Podcast") && b) {
						if( string.startsWith("These materials are copyrighted ")&& string.indexOf("*")>0){
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
									String word = split4[0].trim().replace("to ", "");
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
		// if(true)
		// return "ˈkɔkteil";
		if (word.indexOf(" ") > 0) {
			return null;
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