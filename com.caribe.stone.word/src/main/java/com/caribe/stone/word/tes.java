package com.caribe.stone.word;

import java.io.IOException;
import java.util.Properties;

public class tes {
	public static void main(String[] args) throws IOException {
		Properties properties = new Properties();
		try {
			properties.load(H2DB.class.getResourceAsStream("db.properties"));
			String jdbcUrl = properties.getProperty("jdbcUrl");
			System.out.println(jdbcUrl);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
