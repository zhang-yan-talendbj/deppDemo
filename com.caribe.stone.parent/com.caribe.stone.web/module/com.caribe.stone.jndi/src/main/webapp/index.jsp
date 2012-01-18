<html>
<body>
<h2>Hello World!</h2>

<%
javax.naming.InitialContext ctx = new javax.naming.InitialContext();
javax.naming.Context envCtx = (javax.naming.Context) ctx.lookup("java:comp/env");
	out.println(envCtx.lookup("jdbc/db2"));
	
	out.println(envCtx.lookup("bean/MyBeanFactory"));
%>

</body>
</html>
