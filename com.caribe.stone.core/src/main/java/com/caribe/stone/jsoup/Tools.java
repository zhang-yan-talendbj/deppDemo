package com.caribe.stone.jsoup;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Tools {

	public static void main(String[] args) {
		for (int i = 11300; i < 20000; i++) {
			System.out.println(i);
			getContent(i);
		}
	}

	private static void getContent(int number) {
		String url = "http://jiongji.com/service/weixin/show/" + number;
		Document document = null;
		try {
			document = Jsoup.connect(url).timeout(3000).get();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		if (document != null) {
			String title = document.title();
			if (title != null && title.length() > 0) {

				try {
					File file = new File("word/" + title.charAt(0) + "/" + title + ".html");
					if (!file.exists()) {
						FileUtils.writeStringToFile(file, document.html());
					} else {

						File file2 = new File(String.valueOf(System.currentTimeMillis()));
						FileUtils.writeStringToFile(file2, document.html());
						if (file.length() != file2.length()) {

							FileUtils.writeStringToFile(new File("word/" + title.charAt(0) + "/" + title + System.currentTimeMillis()
									+ ".html"), document.html());
						} else {
//							System.out.println("same file.");
						}
						file2.delete();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
