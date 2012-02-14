package com.caribe.stone.wd;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

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

		Object id = query(username, password);
		if (Integer.parseInt(String.valueOf(id)) > 0) {
			resp.sendRedirect("success.jsp");
		}else{
			resp.sendRedirect("error.jsp");
		}

	}

	private Object query(final String username, final String password) {

		SimpleJDBC jdbc = new SimpleJDBC();
		Object id = jdbc.query(new StatementCallback() {
			@Override
			public Integer execute(Statement stmt) throws SQLException {
				int userid = 0;
				String sql = "select count(*) from t_user where username ='aaacccc' and password ='bbb'";
				System.out.println("Log:LoginServlet.doPost" + sql);
				stmt.execute(sql);
				ResultSet rs = stmt.getResultSet();
				while (rs.next()) {
					userid = rs.getInt(1);
				}
				return userid;
			}
		});
		return id;

	}
}
