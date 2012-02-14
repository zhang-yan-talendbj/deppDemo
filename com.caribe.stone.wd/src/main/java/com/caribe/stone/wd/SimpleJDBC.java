package com.caribe.stone.wd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SimpleJDBC {

	public final Object query(StatementCallback callback) {
		Connection connection = null;
		Object result = null;
		try {
			Class.forName("org.h2.Driver");
			// jdbc:h2:mem:mini-web;DB_CLOSE_DELAY=-1
			// jdbc:h2:tcp://localhost/~/mini-web
			connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/wd", "sa", "");
			Statement stmt = connection.createStatement();
			result = callback.execute(stmt);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (!connection.isClosed()) {
					connection.close();
					connection = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				connection = null;
			}
		}
		return result;
	}

}
