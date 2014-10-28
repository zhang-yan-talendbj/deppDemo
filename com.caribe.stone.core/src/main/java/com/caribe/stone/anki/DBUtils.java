package com.caribe.stone.anki;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
	private Connection conn;

	public DBUtils(AnkiSettings settings) {
		Connection newConnection = null;
		try {
			Class.forName("org.sqlite.JDBC");
			newConnection = DriverManager.getConnection("jdbc:sqlite:" + settings.getJDBC_URL());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.conn = newConnection;
	}

	public Connection getDBConnection() {
		return this.conn;
	}

    public void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
