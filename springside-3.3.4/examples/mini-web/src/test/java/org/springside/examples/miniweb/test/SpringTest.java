package org.springside.examples.miniweb.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class SpringTest {
	@Test
	public void testXmlBeanFactory() throws Exception {

		ClassPathResource res = new ClassPathResource("beans.xml");
		DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
		reader.loadBeanDefinitions(res);
		
		assertEquals("bruce",factory.getBean("str"));
	}
	
	@Test
	public void testFileSystemXmlApplicationContext() throws Exception {
		
	}
}
