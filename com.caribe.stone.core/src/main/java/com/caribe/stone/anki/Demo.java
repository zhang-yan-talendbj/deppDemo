package com.caribe.stone.anki;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Demo {
	public static void main(String[] args) throws IOException {
//		System.setProperty("https.proxyHost", "cnbj-proxysv");
//		System.setProperty("https.proxyPort", "80");

		// Next connection will be through proxy.
		URL url = new URL("http://www.baidu.com/");
		InputStream in = url.openStream();
	}
}
