package com.caribe.stone.tdd.servlet;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

public class TestEchoServlet {

	@Test
	public void testEchoingParametersWithMultipleValues() throws Exception {
		final MockHttpServletRequest req=new MockHttpServletRequest();
		MockHttpServletResponse resp=new MockHttpServletResponse();
		req.setParameter("param1", "value1");
		req.setParameter("param2", "value2");
		req.setParameter("param2", "value22");
		req.setParameter("param3", "value3");
		
		new EchoServlet().doGet(req,resp);
		
		String[] lines=resp.getContentAsString().split("\n");
		assertEquals(3, lines.length);
		assertEquals("param1=value1", lines[0]);
		assertEquals("param2=value22", lines[1]);
	}
}
