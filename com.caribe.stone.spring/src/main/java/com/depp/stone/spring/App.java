package com.depp.stone.spring;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.beans.propertyeditors.PropertiesEditor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 * Hello world!
 * 
 */
public class App {
	public static void main(String[] args) throws IOException {
		PropertyPlaceholderConfigurer s = new PropertyPlaceholderConfigurer();
		s.setValueSeparator(":");

		Properties ppp = PropertiesLoaderUtils
				.loadProperties(new FileSystemResource(
						new File(
								"e:/bruce/aia/depp/maven/caribe/caribe/com.caribe.store.javamail/attchments/test.properties")));

		System.out.println(ppp.getProperty("Analyzed"));;
	}
}
