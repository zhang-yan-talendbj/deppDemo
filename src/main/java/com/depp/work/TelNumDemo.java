package com.depp.work;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import com.depp.zip.ZipCompressor;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;

public class TelNumDemo {
	public static void main(String[] args) throws Exception {

		long long1 = System.currentTimeMillis();
		final String path = "g:/My Documents/Tencent Files/65286983/FileRecv/手机报-数据报告/";
		final String pathNew = path + "newFile/";
		if (new File(pathNew).mkdir()) {
			new File(pathNew).mkdir();
		}
		final File[] listFiles = new File(path).listFiles();
		for (File file : listFiles) {
			final String name = file.getName();
			if (name.endsWith(".log")) {
				pringFileToPdf(file, pathNew);
			}
		}
		long long2 = System.currentTimeMillis();
		ZipCompressor zip = new ZipCompressor(path + "手机报-数据报告.zip");
		zip.compress(pathNew);
		System.out.println(long2 - long1);
	}

	public static void pringFileToPdf(File file, String pathNew) throws UnsupportedEncodingException,
			FileNotFoundException, IOException, DocumentException {

		Document doc = getDocument(pathNew + file.getName() + ".pdf");
		doc.open();
		//		c:\WINDOWS\Fonts\MSYH.TTF
		Font font = getFont();
		//replace("\t", "            ");

		InputStreamReader read = new InputStreamReader(new FileInputStream(file), "gbk");
		BufferedReader br = new BufferedReader(read);
		String str = br.readLine();
		int i = 0;
		while (str != null) {
			i++;
			StringBuffer sb = new StringBuffer();

			if (i == 5) {
				str = "\n";
			}
			if (i >= 8) {
				if (str.length() > 0) {
					sb.setLength(0);
					//itext无法输出\t
					str = str.replace("\t", "            ");
					final String substring = str.substring(0, 5);
					final String string = "******";
					final String substring2 = str.substring(11);
					sb.append(substring).append(string).append(substring2);
				}
			} else {
				sb.append(str);
			}
			Paragraph titleP = new Paragraph(sb.toString(), font);
			//		titleP.setAlignment(titleP.ALIGN_CENTER);
			doc.add(titleP);
			str = br.readLine();
		}
		doc.close();
	}

	private static Document getDocument(String file) throws DocumentException, FileNotFoundException {
		Document doc = new Document(PageSize.A4);
		PdfWriter.getInstance(doc, new FileOutputStream(file));
		return doc;
	}

	private static Font getFont() throws DocumentException, IOException {
		final String fontPath = "c:/WINDOWS/Fonts/MSYH.TTF";
		final String string2 = "UniGB-UCS2-H";

		//内容字体  
		BaseFont bfComic = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
		Font font = new Font(bfComic, 9, Font.NORMAL);
		return font;
	}
}
