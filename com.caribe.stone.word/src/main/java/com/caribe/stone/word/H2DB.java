package com.caribe.stone.word;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class H2DB {

	public final Object execute(StatementCallback callback) {
		Connection con = null;
		Statement stmt = null;
		Object obj = null;
		String jdbcUrl = null;
		try {
			Class.forName("org.h2.Driver");
			Properties properties = new Properties();
			try {
				properties.load(H2DB.class.getResourceAsStream("db.properties"));
				jdbcUrl = properties.getProperty("jdbcUrl");
			} catch (IOException e) {
				e.printStackTrace();
			}
			con = DriverManager.getConnection(jdbcUrl, "sa", "");

			stmt = con.createStatement();
			obj = callback.execute(stmt);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				stmt.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			try {
				con.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return obj;
	}
}