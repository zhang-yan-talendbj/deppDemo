<<<<<<< HEAD
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
=======
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
>>>>>>> d64a92cd44c1cd1279e7c9921940f7cb9d860b60
