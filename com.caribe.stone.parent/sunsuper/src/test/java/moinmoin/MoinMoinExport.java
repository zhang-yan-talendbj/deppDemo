package moinmoin;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MoinMoinExport {
	private static final String LOCALHOST = "http://localhost:8080";
	private static String token = "";
	private static Map<String, String> oldUrl = new HashMap<String, String>(500);

	public static Map<String, String> getWiki() throws IOException {
		String url = LOCALHOST + "/index";
		parseHtml(url, "/index");
		System.out.println(oldUrl.size());
		return oldUrl;
	}

	private static void parseHtml(String url, String title) throws IOException {
//		System.out.println(URLDecoder.decode(url));
		// if (token .equals(url)) {
		// return;
		// }
		if (oldUrl.containsKey(title)) {
			return;
		}
		token = url;
		Document doc = null;
		String text = null;
		try {
			doc = Jsoup.connect(url).get();

		} catch (Exception e1) {
			return;
		}

		try {
			String string = url + "?action=edit&editor=guipossible";
			Document content = Jsoup.connect(string).get();
			Element result = content.getElementById("editor-textarea");
			text = result.text().replaceAll("<<TableOfContents>>", "");
			text = text.replaceAll("]]", "]]\r\n");
		} catch (Exception e) {

		} finally {
			oldUrl.put(title, text);
		}

		Element content = doc.getElementById("content");
		Elements elements = content.select("a[href]");
		for (Element e : elements) {
			String href = e.attr("href");
			if (href.startsWith("/")) {
				String url2 = LOCALHOST + href;
				parseHtml(url2, href);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		System.out.println(getWiki().keySet());
	}
}
