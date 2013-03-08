package com.caribe.stone.jsoup;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;


public class SQLiteDemo {
	public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
//		System.out.println(new Date(1357536246151L));
//		System.out.println(new Date(1357536246151L));
		Class.forName("org.sqlite.JDBC");
		Connection conn = DriverManager.getConnection("jdbc:sqlite:c:/Users/bsnpbag/Documents/Anki/User 1/collection.anki2");
		
		Statement stat = conn.createStatement();
		stat.execute("select n.mod from notes n,cards c where sfld='throughout' and n.id=c.nid");
		ResultSet rs = stat.getResultSet();
		while(rs.next()){
		System.out.println(new Date(rs.getLong((1))));
		}
		System.out.println(new Date(1358759951143L));
	}

	private static void ss() throws ClassNotFoundException, SQLException {
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
