package com.caribe.stone.j2se.clazz;

public class MyApp {
	public static void main(String[] args) throws Exception {
		FileClassLoader loader = new FileClassLoader();
		Class objClass = loader.findClass("com.caribe.stone.j2se.clazz.MyApp");
		Object obj = objClass.newInstance();
		System.out.println(objClass.getName());
		System.out.println(objClass.getClassLoader());
		System.out.println(obj);
		
		System.out.println(MyApp.class.getClassLoader());
		
		ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
		Class<?> loadClass = systemClassLoader.loadClass("com.caribe.stone.j2se.clazz.MyApp");
		System.out.println(loadClass.getClassLoader());
		System.out.println(loadClass.getName());
		
		System.out.println(MyApp.class.getClassLoader());
	}
}