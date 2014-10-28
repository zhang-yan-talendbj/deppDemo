package com.caribe.stone.anki;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class Dao {

	private Connection conn;
	private DBUtils db;

	public Dao(DBUtils db) {
		this.db = db;
		this.conn = db.getDBConnection();
	}

	public Note getNoteById(long id) {
		String sql = "select id,sfld,flds from notes where id =?";
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setLong(1, id);
			psmt.execute();
			ResultSet rs = psmt.getResultSet();
			while (rs.next()) {
				Note note = new Note();
				note.setId(rs.getLong("id"));
				note.setContent(rs.getString("flds"));
				note.setWord(rs.getString("sfld"));
				return note;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void update(Note note) {
		if (note.isChange()&&note.getFieldCount()>2) {
			PreparedStatement psmt = null;

			String sql = "update notes set flds= ?,sfld=? where id= ?";

			System.out.println("Update "+note.getWord());

//			System.out.println("updateCard SQL:" + sql);
//			System.out.println("param:" + note.getContent() + ",id:" + note.getId());
			try {
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, note.getContent());
				psmt.setString(2, note.getWord());
				psmt.setLong(3, note.getId());
				psmt.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	public List<Note> getAllNotesByCondition(QueryBean bean) {
		List<Note> result = new LinkedList<Note>();
		try {
			Statement stat = conn.createStatement();
			StringBuffer buf = new StringBuffer("select id,sfld,flds from notes ");
			if (bean.getDeckId() != null) {
				buf.append(" where id in (select nid from cards where did=").append(bean.getDeckId()).append(")");
			}
			System.out.println("Get all word SQL: " + buf);
			stat.execute(buf.toString());
			ResultSet rs = stat.getResultSet();
			while (rs.next()) {
				Note note = new Note();
				note.setId(rs.getLong("id"));
				note.setWord(rs.getString("sfld"));
				
				if(rs.getString("sfld").equals("gain")){
					
				}
				note.setContent(rs.getString("flds"));
                note.setDeckId(bean.getDeckId());
                note.setChange(false);
                result.add(note);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public void close() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
