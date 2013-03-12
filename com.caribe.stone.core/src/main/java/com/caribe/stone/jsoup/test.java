package com.caribe.stone.jsoup;

import java.sql.Connection;
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
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String today = sdf.format(new Date());
		List<String> list = new LinkedList<String>();
		Connection con = WordDemo.getSqlConnection();
		String string = "select 'marked',sfld from notes where tags like '%marked%' union all select n.id, n.sfld from notes n, cards c where c.nid=n.id ";
		Statement stmt = con.createStatement();
		stmt.execute(string);
		ResultSet rs = stmt.getResultSet();
		while (rs.next()) {
			String string2 = rs.getString(1);
			if(string2.equals("marked")){
				list.add(rs.getString(2));
			}else{
			String x = string2 ;
			Date obj = new Date(Long.valueOf(x));
			if (today.equals(sdf.format(obj))) {
				list.add(rs.getString(2));
			}
			}
		}
	}
}
