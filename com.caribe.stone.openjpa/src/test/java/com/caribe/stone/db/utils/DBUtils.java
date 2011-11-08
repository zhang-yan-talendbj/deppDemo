package com.caribe.stone.db.utils;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.PropertyResourceBundle;

import org.springframework.core.io.ClassPathResource;

public class DBUtils {
	private static InputStream inputStream = null;
	private static PropertyResourceBundle p = null;
	static {
		try {
			inputStream = new ClassPathResource("db.properties").getInputStream();
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
		Class.forName(p.getString("db2.jdbc.driverClassName"));
		Connection con = DriverManager.getConnection(p.getString("db2.jdbc.url"), p.getString("db2.jdbc.username"),
				p.getString("db2.jdbc.password"));
		return con;
	}
	@Deprecated
	public static Connection getH2Connection() throws Exception {
		Class.forName(p.getString("db2.jdbc.driverClassName"));
		Connection con = DriverManager.getConnection(p.getString("db2.jdbc.url"), p.getString("db2.jdbc.username"),
				p.getString("db2.jdbc.password"));
		return null;
	}

}
