package com.caribe.stone;

import javax.naming.Context;
import javax.naming.InitialContext;

/**
 * Hello world!
 * 
 */
public class App {
	public static void main(String[] args) throws Exception {
//		InitialContext ctx = new InitialContext();
//		Context envCtx = (Context) ctx.lookup("java:comp/env");
//		envCtx.lookup("jdbc/db2");

		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		MyBean bean = (MyBean) envCtx.lookup("bean/MyBeanFactory");

//		writer.println("foo = " + bean.getFoo() + ", bar = " +
//		               bean.getBar());
	}
}
