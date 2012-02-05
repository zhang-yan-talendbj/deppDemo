package com.caribe.stone.tdd.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1114215979441836798L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pw = req.getParameter("j_password");
		String un = req.getParameter("j_username");
		if ("validuser".equals(un) && "correctpw".endsWith(pw)) {
			resp.sendRedirect("/frontpage");
			req.getSession().setAttribute("name", un);
		} else {
			resp.sendRedirect("/invalidlogin");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
