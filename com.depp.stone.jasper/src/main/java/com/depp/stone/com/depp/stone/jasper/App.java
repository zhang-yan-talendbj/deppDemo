package com.depp.stone.com.depp.stone.jasper;

import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws NamingException
    {
    	InitialContext context=new InitialContext();
//    	context.lookup("");
    	System.out.println(context.getEnvironment());
    }
}
