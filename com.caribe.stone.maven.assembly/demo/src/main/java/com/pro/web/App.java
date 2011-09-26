package com.pro.web;


import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        System.out.println(Long.MAX_VALUE);
        Properties properties = System.getProperties();
        Set set = properties.keySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
        	String s = (String)iterator.next();
        	System.out.println(s + "=" + properties.getProperty(s));
        }
        System.out.println(System.getProperty("os.name"));
    }
}
