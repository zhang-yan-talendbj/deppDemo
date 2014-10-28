package com.caribe.stone.db.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.PropertyResourceBundle;

import org.springframework.core.io.ClassPathResource;

public class DBUtils {
	private static final String OPENJPA_CONNECTION_PASSWORD = "openjpa.ConnectionPassword";
	private static final String OPENJPA_CONNECTION_USER_NAME = "openjpa.ConnectionUserName";
	private static final String OPENJPA_CONNECTION_URL = "openjpa.ConnectionURL";
	private static final String OPENJPA_CONNECTION_DRIVER_NAME = "openjpa.ConnectionDriverName";
	private static InputStream inputStream = null;
	private static PropertyResourceBundle p = null;
	static {
		try {
			inputStream = new ClassPathResource("db2.properties")
					.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			p = new PropertyResourceBundle(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static Connection getDB2Connection() throws Exception {
		// openjpa.ConnectionDriverName:org.h2.Driver
		// openjpa.ConnectionURL:jdbc:h2:tcp://10.69.4.187:9092/test
		// openjpa.jdbc.DBDictionary:h2
		// openjpa.ConnectionUserName:sa
		// openjpa.ConnectionPassword:
		// openjpa.FetchBatchSize:20
		// openjpa.jdbc.Schema:jpa
		Class.forName(p.getString(OPENJPA_CONNECTION_DRIVER_NAME));
		Connection con = DriverManager.getConnection(
				p.getString(OPENJPA_CONNECTION_URL),
				p.getString(OPENJPA_CONNECTION_USER_NAME),
				p.getString(OPENJPA_CONNECTION_PASSWORD));
		return con;
	}

	public static Connection getH2Connection() throws Exception {
		// Class.forName(p.getString("db2.jdbc.driverClassName"));
		// Connection con =
		// DriverManager.getConnection(p.getString("db2.jdbc.url"),
		// p.getString("db2.jdbc.username"),
		// p.getString("db2.jdbc.password"));
		if (true)
			throw new Exception("error");

		return null;
	}

}
