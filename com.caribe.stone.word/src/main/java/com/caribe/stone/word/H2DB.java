package com.caribe.stone.word;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class H2DB {

	public final Object execute(StatementCallback callback) {
		Connection con = null;
		Statement stmt = null;
		Object obj = null;
		try {
			Class.forName("org.h2.Driver");

			con = DriverManager.getConnection("jdbc:h2:file:D:/test", "sa", "");
			
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

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// insert("word");
	}
}