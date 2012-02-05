package com.caribe.stone.h2;

import java.io.IOException;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;

public class MavenDemo {

	public static void main(String[] args) throws IOException {
		ClassPathResource classPathResource = new ClassPathResource("application.test.properties");
		Properties properties = new Properties();
		properties.load(classPathResource.getInputStream());
		System.out.println(properties.get("jdbc.url"));
		//propertie-maven-active maven profile
	}
}
