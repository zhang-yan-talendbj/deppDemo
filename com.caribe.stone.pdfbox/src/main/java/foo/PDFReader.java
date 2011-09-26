package foo;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;

import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.util.PDFHighlighter;
import org.apache.pdfbox.util.PDFText2HTML;
import org.apache.pdfbox.util.PDFTextStripper;

public class PDFReader {
	public static void main(String[] args) throws Exception {
		FileInputStream fis = new FileInputStream(
				"e:/bruce/aia/depp/maven/caribe/caribe/com.caribe.store.javamail/attchments/email.pdf");
		BufferedWriter writer = new BufferedWriter(new FileWriter(
				"e:/bruce/aia/depp/maven/caribe/caribe/com.caribe.store.javamail/attchments/email.txt"));
		PDFParser p = new PDFParser(fis);
		p.parse();
//		PDFText2HTML htmlParser=new PDFText2HTML("utf-8");
//		String html = htmlParser.getText(p.getPDDocument());
//		System.out.println(html);
		
//		PDFHighlighter hl=new PDFHighlighter();
//		String hstring = hl.getText(p.getPDDocument());
//		System.out.println(hstring);
		
		
		PDFTextStripper ts = new PDFTextStripper();
		String s = ts.getText(p.getPDDocument());
		writer.write(s);
		System.out.println(s);
		fis.close();
		writer.close();

	}

}
