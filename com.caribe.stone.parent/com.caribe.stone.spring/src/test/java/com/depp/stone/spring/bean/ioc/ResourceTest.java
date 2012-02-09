package com.depp.stone.spring.bean.ioc;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class ResourceTest {
	
	@Test
	public void classPathResource() throws Exception {
		assertTrue(new ClassPathResource("log4j.properties").exists());
		try {
			assertTrue(new ClassPathResource("classpath:log4j.properties").exists());
			fail();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
		}
	}
}
