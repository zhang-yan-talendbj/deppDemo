package com.caribe.stone.jsoup;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;


public class CopyOfLetterDemo {
	public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
		Class.forName("org.sqlite.JDBC");
		Connection conn = DriverManager.getConnection("jdbc:sqlite:c:/Users/bsnpbag/Documents/Anki/User 1/collection.anki2");

		Statement stat = conn.createStatement();
		stat.execute("select sfld from notes where tags=' today ';");
		ResultSet rs = stat.getResultSet();
		while(rs.next()){
			System.out.println(rs.getString(1));
		}
	}
}
