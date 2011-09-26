package foo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PDFReader {
	private PDFReader() {
	}

	private static Logger log = LoggerFactory.getLogger(PDFReader.class);

	public static List<Map<String, String>> readPdfFolder(InputStream input) {

		List<Map<String, String>> emailList = null;
		PDDocument pdfDocument = null;
		try {
			PDFTextStripper ts = new PDFTextStripper();
			pdfDocument = PDDocument.load(input);
			String s = ts.getText(pdfDocument);

			emailList = analyPdf(s);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (pdfDocument != null) {
				COSDocument cos = pdfDocument.getDocument();
				try {
					cos.close();
					pdfDocument.close();
				} catch (IOException e) {
					log.warn("IOException", e);
					e.printStackTrace();
				}
			}
		}
		return emailList;

	}

	private static List<Map<String, String>> analyPdf(String s) {
		List<Map<String, String>> emailList = new ArrayList<Map<String, String>>();
		String[] split = s.split("\r\n");
		StringBuffer content = new StringBuffer();
		Map<String, String> map = null;
		boolean isInfo=false;
		for (String str : split) {
			if (str != null) {
				String trim = str.trim();
				if (trim.startsWith("ID")) {
					isInfo=true;
					if (map != null && map.containsKey("ID")) {
						map.put("content", content.toString());
						content.setLength(0);
						emailList.add(map);
						map=null;
					}
					map=new HashMap<String, String>();
					map.put("ID", trim.substring(trim.indexOf(":") + 1).trim());
				} else if (trim.startsWith("Email address")) {
					map.put("Email address", trim.substring(trim.indexOf(":") + 1)
							.trim());
				} 
				if (!trim.equals("space")&&isInfo) {
					content.append(str).append("\r\n");
				}
			}
		}

		if (map!=null&&map.containsKey("ID")) {
			map.put("content", content.toString());
			emailList.add(map);
			map=null;
		}
		return emailList;
	}
}
