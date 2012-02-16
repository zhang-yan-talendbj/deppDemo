<%@page import="org.springframework.context.ApplicationContext" %>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<div id='msg'>test</div>
<%
ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext()); 
%>

<%=context.getBean("dataSource") %>