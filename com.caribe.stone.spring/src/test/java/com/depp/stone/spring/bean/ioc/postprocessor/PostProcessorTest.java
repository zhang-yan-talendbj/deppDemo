package com.depp.stone.spring.bean.ioc.postprocessor;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class PostProcessorTest {
	@Test
	public void propertyPostProcessor() throws Exception {
		ClassPathResource resource = new ClassPathResource(
				"com/depp/stone/spring/bean/ioc/postprocessor/PostProcessorTest-context.xml");
		XmlBeanFactory xmlBeanFactory = new XmlBeanFactory(resource);
		PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
		ppc.setLocation(new ClassPathResource(
				"com/depp/stone/spring/bean/ioc/postprocessor/bean.properties"));
		ppc.postProcessBeanFactory(xmlBeanFactory);
		assertEquals("pa+Battlefury", xmlBeanFactory.getBean("pa"));
	}

	@Test
	public void propertyOverridePostProcessor() throws Exception {
		// TODO
	}
	@Test
	public void customPropertyEditorPostProcessor() throws Exception {
		//TODO
	}
}
