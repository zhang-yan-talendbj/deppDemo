package com.caribe.stone.jsoup;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.caribe.stone.anki.profile.Office;
import com.ibm.icu.text.SimpleDateFormat;

public class test {

	public static void main(String[] args) throws SQLException, ClassNotFoundException, ParseException, IOException {
		File file = WordDemo.getRPFromICB("California");
		System.out.println();
		FileUtils.copyFile(file, new File("d:/California.mp3"));
//		WordDemo.setPath(new Office());
//		getTodayCards();
	}

	private static void getTodayCards() throws ClassNotFoundException, SQLException, ParseException {
		// 1363068176029
		// 1363068176000
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-hhmmss");
		System.out.println(sdf.parse("20130318-040000").getTime());
//		WordDemo.setPath(new Home());
//		Connection con = WordDemo.getSqlConnection();
//		String sql = "select r.id,cid,n.sfld from revlog r, notes n, cards c where r.cid=c.id and c.nid=n.id";
//		PreparedStatement stmt = con.prepareStatement(sql);
//		boolean result = stmt.execute();
//		ResultSet rs = stmt.getResultSet();
//		int i=0;
//		ArrayList list = new ArrayList();
//		while (rs.next()) {
//			Long valueOf = Long.valueOf(rs.getString(1));
//			long time = sdf.parse("20130318-040000").getTime();
//			System.out.println(time);
//			if (valueOf > time) {
//				System.out.println(new Date(valueOf));
//				System.out.println(new Date(Long.valueOf(rs.getString(2))));
//				i++;
//				list.add(rs.getString(1));
//			}
//			
//		}
//		System.out.println(i);
//		System.out.println(list.size());
	}
}
