<<<<<<< HEAD
package com.depp.stone.spring.resource;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.depp.stone.spring.bean.instantiation.ResourceBean;

@ContextConfiguration
public class ResourceTest extends AbstractJUnit4SpringContextTests {
	@Autowired
	private ResourceBean resourceBean;

	public ResourceBean getResourceBean() {
		return resourceBean;
	}

	public void setResourceBean(ResourceBean resource) {
		this.resourceBean = resource;
	}

	@Test
	public void testSimpleResource() throws Exception {
		assertEquals(
				"com.depp.stone.spring.bean.resource.ResourceTest-context.xml",
				getResourceBean().getResource().getFilename());
	}
	@Test
	public void testClasspathApplicationContext() throws Exception {
		ApplicationContext ac=new ClassPathXmlApplicationContext(new String[]{"ResourceTest-context.xml"},ResourceTest.class);
	}
}
=======
package com.depp.stone.spring.resource;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.depp.stone.spring.bean.instantiation.ResourceBean;

@ContextConfiguration
public class ResourceTest extends AbstractJUnit4SpringContextTests {
	@Autowired
	private ResourceBean resourceBean;

	public ResourceBean getResourceBean() {
		return resourceBean;
	}

	public void setResourceBean(ResourceBean resource) {
		this.resourceBean = resource;
	}

	@Test
	public void testSimpleResource() throws Exception {
		assertEquals(
				"com.depp.stone.spring.bean.resource.ResourceTest-context.xml",
				getResourceBean().getResource().getFilename());
	}
	@Test
	public void testClasspathApplicationContext() throws Exception {
		ApplicationContext ac=new ClassPathXmlApplicationContext(new String[]{"ResourceTest-context.xml"},ResourceTest.class);
	}
}
>>>>>>> d64a92cd44c1cd1279e7c9921940f7cb9d860b60
