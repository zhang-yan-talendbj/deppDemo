package org.jboss.jsfunit.staticanalysis;

import java.io.IOException;
import java.util.Properties;

public class Demo {
	public static void main(String[] args) throws IOException {
		Properties props = new Properties();
		props.load(Demo.class.getResourceAsStream("/project.properties"));
		String basedir = (String) props.get("project.basedir");
		System.out.println(basedir);
	}
}
