package foo;

import static org.junit.Assert.*;
import java.io.File;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PDFReaderTest {
	private static Logger log = LoggerFactory.getLogger(PDFReaderTest.class);
	@Test
	public void testReadPdfFolder() throws FileNotFoundException {
		File pdfFile=new File("src/test/resource/email.pdf");
		InputStream io=new FileInputStream(pdfFile);
		List<Map<String, String>> list = PDFReader.readPdfFolder(io);
		assertEquals(6, list.size());
		System.out.println(list.get(0).get("ID"));
		System.out.println(list.get(1).get("ID"));
		System.out.println(list.get(1).get("ID"));
	}

}
