package com.caribe.stone.h2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class H2Demo {

	public static void main(String[] args) throws Exception {
		Class.forName("org.h2.Driver");
		Connection conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/h2demo", "sa",
				"");
		// add application code here
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM TEST ");
		ps.execute();
		ResultSet rs = ps.getResultSet();
//		while(rs.next()){
//			System.out.println(rs.getString(1));
//		}
	}
}
