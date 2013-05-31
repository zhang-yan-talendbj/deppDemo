package com.caribe.stone.jsoup;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.caribe.stone.anki.profile.Office;

public class CopyOftest {

	public static void main(String[] args) throws IOException {
		WordDemo.setPath(new Office());

		int deckId = 1;
		long days = 4;

		List<Card> list = new LinkedList<Card>();
		Connection con = null;
		StringBuffer sb = new StringBuffer();
		sb.append("<html><head> <meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\"></head>");
	
		try {
			con = WordDemo.getSqlConnection();
			String sql = "select n.flds from  notes n, cards c where ";
			if (deckId != 0) {
				sql = sql + " did=" + deckId + " and ";
			}
			sql = sql + " c.nid=n.id and n.id> " + (System.currentTimeMillis() - 1000L * 60 * 60 * 24 * days);
			sql= sql+ " order by n.id";
			System.out.println("getNDaysNewCards SQL:" + sql);
			Statement stmt = con.createStatement();
			stmt.execute(sql);
			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {
				sb.append(rs.getString(1));
			}

			sb.append(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) { // TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		sb.append("</html>");
		FileUtils.writeStringToFile(new File("abc.html"), sb.toString(), "UTF-8");
	}

}
