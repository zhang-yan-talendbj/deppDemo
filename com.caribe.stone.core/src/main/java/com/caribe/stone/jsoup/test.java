package com.caribe.stone.jsoup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.ibm.icu.text.SimpleDateFormat;

public class test {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		getTodayCards();
	}

	private static void getTodayCards() throws ClassNotFoundException, SQLException {
		// 1363068176029
		// 1363068176000
		WordDemo.setPath(new Home());
		Connection con = WordDemo.getSqlConnection();
		String sql = "select n.mod, n.sfld from notes n, cards c where c.nid=n.id and n.sfld='bother' order by n.mod";
		PreparedStatement stmt = con.prepareStatement(sql);
		boolean result = stmt.execute();
		ResultSet rs = stmt.getResultSet();
		while (rs.next()) {
			System.out.println(rs.getString(1));
			System.out.println(rs.getString(2));
		}
		System.out.println(new Date(1363450476000L));
	}
}
