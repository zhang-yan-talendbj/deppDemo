package foo;

import static junit.framework.Assert.*;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

import org.junit.BeforeClass;
import org.junit.Test;

public class AllTest {
	@BeforeClass
	public static void setUp() {
		PropertiesUtils
				.loadProperties("src/test/resource/test-config.properties");
	}

	@Test
	public void testReadAttchmentPST() {
		PstUtils.readAttchmentPST(PropertiesUtils.getProperty("pstPath"));
		File testFile = new File(PropertiesUtils.getProperty("attchmentFolder"));
		File file = testFile.listFiles()[0];
		assertEquals(115696754688L, file.getTotalSpace());
		
//		List<Map<String, String>> list = PDFReader
//				.readPdfFolder(PropertiesUtils.getProperty("attchmentFolder"));
//		assertEquals(6, list.size());
//		assertTrue(list.get(1).containsKey("content"));
//		assertEquals("dssdemo@gmail.com", list.get(1).get("Email address"));
	}

	@Test
	public void testSend() {
		EmailUtil.send(PropertiesUtils.getProperty("mail.smtp.host"),
				PropertiesUtils.getProperty("mail.from"),
				PropertiesUtils.getProperty("mail.username"),
				"This is s subject.", "Hello~!");
	}

	@Test
	public void testSendWithAuth() {
		Authenticator mailauth = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication(
						PropertiesUtils.getProperty("mail.username"),
						PropertiesUtils.getProperty("mail.password"));
			}
		};
		EmailUtil.sendWithAuth(mailauth, PropertiesUtils.getProperties(),
				PropertiesUtils.getProperty("mail.from"),
				PropertiesUtils.getProperty("mail.username"),
				"This is s auth subject.", "Hello auth!");
	}

	public static void main(String[] args) {
		org.junit.runner.JUnitCore.runClasses(AllTest.class);
	}
}
