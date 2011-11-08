package foo.domain;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

/**
 * Hello world!
 * 
 */
public class App {
	public static void main(String[] args) throws FileNotFoundException,
			IOException {
		Map<String, String> map = new HashMap<String, String>();
		FileInputStream input = new FileInputStream(
				new File(
						"e:/bruce/aia/depp/maven/caribe/caribe/com.caribe.store.javamail/attchments/email.txt"));
		List<String> list = IOUtils.readLines(input);
		StringBuffer sb = new StringBuffer();
		for (String str : list) {
			if (str != null) {
				String trim = str.trim();
				if (trim.startsWith("ID")) {
					if (map.containsKey("ID")) {
						sendMail(map.get("Email address"), sb.toString());
						sb.setLength(0);
					}
					map.put("ID", trim.substring(trim.indexOf(":") + 1).trim());
				} else if (trim.startsWith("Email address")) {
					map.put("Email address",
							trim.substring(trim.indexOf(":") + 1).trim());
				} else if (!trim.equals("space")) {
					sb.append(str).append("\r\n");
				}

			}
		}
	}

	private static void sendMail(String name, String string) {
		System.out.println("To: " + name);
		System.out.println("Info: " + string);
		// TODO send email
	}
}
