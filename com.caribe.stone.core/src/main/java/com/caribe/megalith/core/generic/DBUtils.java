package com.caribe.megalith.core.generic;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.PropertyResourceBundle;

import org.springframework.core.io.ClassPathResource;

public final class DBUtils {
	private DBUtils() {
	}

	private static InputStream inputStream = null;
	private static PropertyResourceBundle p = null;

	public static Connection getDB2Connection() throws Exception {
		inputStream = new ClassPathResource("db.properties").getInputStream();
		// openjpa.jdbc.Schema:jpa
		Class.forName(p.getString("openjpa.ConnectionDriverName"));
		Connection con = DriverManager.getConnection(p.getString("openjpa.ConnectionURL"),
				p.getString("openjpa.ConnectionUserName"), p.getString("openjpa.ConnectionPassword"));
		return con;
	}

	public static Connection getH2Connection() throws Exception {
		inputStream = new ClassPathResource("h2.properties").getInputStream();
		p = new PropertyResourceBundle(inputStream);
		// openjpa.jdbc.Schema:jpa
		Class.forName(p.getString("openjpa.ConnectionDriverName"));
		Connection con = DriverManager.getConnection(p.getString("openjpa.ConnectionURL"),
				p.getString("openjpa.ConnectionUserName"), p.getString("openjpa.ConnectionPassword"));
		return con;
	}

	public static void main(String[] args) throws Exception {

		System.out.println(getH2Connection());
	}
}
