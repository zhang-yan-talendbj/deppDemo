package foo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

public class PDFReader {
	private PDFReader() {
	}

	public static List<Map<String, String>> readPdfFolder(InputStream input) {
		List<Map<String, String>> emailList = new ArrayList<Map<String, String>>();
		PDDocument pdfDocument = null;
		try {
			PDFTextStripper ts = new PDFTextStripper();
			pdfDocument = PDDocument.load(input);
			String s = ts.getText(pdfDocument);

			String[] split = s.split("\r\n");
			Map<String, String> map = new HashMap<String, String>();
			StringBuffer content = new StringBuffer();
			for (String str : split) {
				if (str != null) {
					String trim = str.trim();
					if (trim.startsWith("ID")) {
						if (map.containsKey("ID")) {
							map.put("content", content.toString());
							emailList.add(map);
							content.setLength(0);
						}
						map.put("ID", trim.substring(trim.indexOf(":") + 1)
								.trim());
					} else if (trim.startsWith("Email address")) {
						map.put("Email address",
								trim.substring(trim.indexOf(":") + 1).trim());
					} else if (!trim.equals("space")) {
						content.append(str).append("\r\n");
					}
				}
			}

			if (map.containsKey("ID")) {
				map.put("content", content.toString());
				emailList.add(map);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pdfDocument != null) {
				COSDocument cos = pdfDocument.getDocument();
				try {
					cos.close();
					pdfDocument.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return emailList;

	}

}
