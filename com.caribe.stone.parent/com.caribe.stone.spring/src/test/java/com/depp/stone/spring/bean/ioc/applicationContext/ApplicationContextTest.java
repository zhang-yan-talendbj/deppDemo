package com.depp.stone.spring.bean.ioc.applicationContext;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

public class ApplicationContextTest {

	@Test
	public void resourceLoader() {
		DefaultResourceLoader rl = new DefaultResourceLoader();
		Resource resource = rl.getResource("classpath:log4j.properties");
		System.out.println(resource.getFilename());

		Resource fakeResource = rl.getResource("d:/spring2site/redme");
		// The resource doesn't exist,so return a ClassPathResource
		assertTrue(fakeResource instanceof ClassPathResource);
		assertFalse(fakeResource.exists());
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void fileSystemResourceLoader() throws Exception {
		String str=new String("abc");
		// TODO
	}

	@Test
	public void resourcePatternResolver() throws Exception {
		// TODO
	}

}
