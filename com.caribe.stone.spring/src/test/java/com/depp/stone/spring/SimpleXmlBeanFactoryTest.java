<<<<<<< HEAD
package com.depp.stone.spring;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

public class SimpleXmlBeanFactoryTest {
@Test
public void testName() throws Exception {
	ClassPathResource ac = new ClassPathResource("applicationContext.xml");
	DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
	XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
	reader.loadBeanDefinitions(ac);
	Object u = factory.getBean("user");
	System.out.println(u);
	assertNotNull(u);
}
}
=======
package com.depp.stone.spring;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

public class SimpleXmlBeanFactoryTest {
@Test
public void testName() throws Exception {
	ClassPathResource ac = new ClassPathResource("applicationContext.xml");
	DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
	XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
	reader.loadBeanDefinitions(ac);
	Object u = factory.getBean("user");
	System.out.println(u);
	assertNotNull(u);
}
}
>>>>>>> d64a92cd44c1cd1279e7c9921940f7cb9d860b60
