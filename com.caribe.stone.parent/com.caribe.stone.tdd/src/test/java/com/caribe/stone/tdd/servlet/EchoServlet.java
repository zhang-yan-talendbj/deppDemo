package com.caribe.stone.tdd.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EchoServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1114215979441836798L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setHeader("Content-Type", "text/plain");
		PrintWriter w = resp.getWriter();
		Enumeration<?> e = req.getParameterNames();
		while (e.hasMoreElements()) {
			String p = String.valueOf(e.nextElement());
			String[] v = req.getParameterValues(p);
			for (int i = 0; i < v.length; i++) {
				w.write(p + "=" + v[i]);
				w.write("\n");
			}
		}
		w.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
