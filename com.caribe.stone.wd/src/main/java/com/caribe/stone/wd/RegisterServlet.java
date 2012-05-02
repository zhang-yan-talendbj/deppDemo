package com.caribe.stone.wd;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet {

	public static void registerUser(RegisterServlet registerServlet, String username, String password) {
		registerServlet.registerUser(username, password);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
			IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
			IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");

		registerUser(username, password);
	}

	private void registerUser(final String username, final String password) {

		SimpleJDBC jdbc = new SimpleJDBC();
		jdbc.query(new StatementCallback() {
			@Override
			public Object execute(Statement stmt) throws SQLException {
				int id=0;
				String sql = "insert into t_user(USERNAME,PASSWORD) values('" + username + "','" + password
						+ "') ";
				System.out.println("Log:RegisterServlet.doPost " + sql);
				stmt.execute(sql, Statement.RETURN_GENERATED_KEYS);
				ResultSet generatedKeys = stmt.getGeneratedKeys();
				if (generatedKeys.next()) {
					 id = generatedKeys.getInt(1);
				} else {
				}
				return id;
			}
		});
	}

}
