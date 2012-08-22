package com.caribe.stone.word;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBTest {

	public static void main(String[] args) {
		H2DB h2db = new H2DB("jdbc:h2:tcp://localhost/~/word");
		
		h2db.execute(new StatementCallback() {
			
			@Override
			public Object execute(Statement stmt) {
				try {
					
					stmt.execute("select w.word, select Max(cycle_level)  from word_cycle as wc where wc.word=w.word as cl ,select Max(wc.alarm_time) as at from word_cycle as wc where wc.word=w.word as at   from word as w  where w.is_over=0");
					ResultSet rs = stmt.getResultSet();
					while(rs.next()){
						System.out.println(rs.getString(1));
						System.out.println(rs.getInt(2));
						System.out.println(rs.getString(3)==null);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}
		});
	}
}
