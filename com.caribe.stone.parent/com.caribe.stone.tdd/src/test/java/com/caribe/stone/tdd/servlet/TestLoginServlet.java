package com.caribe.stone.tdd.servlet;

import static org.junit.Assert.*;

import javax.servlet.http.HttpServlet;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

public class TestLoginServlet {

	@Test
	public void wrongPasswordShouldredirectToErrorPage() throws Exception {
		HttpServlet s = new LoginServlet();
		MockHttpServletRequest req = new MockHttpServletRequest("GET", "/login");
		MockHttpServletResponse resp = new MockHttpServletResponse();
		req.addParameter("j_username", "errorname");
		req.addParameter("j_password", "errorpw");

		s.service(req, resp);
		assertEquals("/invalidlogin", resp.getRedirectedUrl());
	}

	@Test
	public void validLoginForwardsToFrontPageAndStoresUsername() throws Exception {
		String validusername = "validuser";
		String validPassword = "correctpw";
		final FakeAuthenticationService as = new FakeAuthenticationService();
		as.addUser(validusername, validPassword);

		LoginServlet servlet = new LoginServlet() {
			protected AuthenticationSerivce getAuthenticationSerivce() {
				return as;
			}
		};
		HttpServlet s = new LoginServlet();
		MockHttpServletRequest req = new MockHttpServletRequest("GET", "/login");
		MockHttpServletResponse resp = new MockHttpServletResponse();
		req.addParameter("j_username", validusername);
		req.addParameter("j_password", validPassword);

		s.service(req, resp);
		assertEquals("/frontpage", resp.getRedirectedUrl());
		assertEquals(validusername, req.getSession().getAttribute("name"));
	}
}
