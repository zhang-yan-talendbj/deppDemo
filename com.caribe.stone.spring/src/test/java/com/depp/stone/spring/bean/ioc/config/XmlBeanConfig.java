package com.depp.stone.spring.bean.ioc.config;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.depp.stone.spring.bean.instantiation.CollectionBean;
import com.depp.stone.spring.bean.instantiation.ConstrtorBean;
import com.depp.stone.spring.bean.instantiation.LookupBean;
import com.depp.stone.spring.bean.ioc.ChildBean;
import com.depp.stone.spring.bean.ioc.DotaFactoryBean;

@ContextConfiguration
public class XmlBeanConfig extends AbstractJUnit4SpringContextTests {

	@Test
	public void alias() throws Exception {
		// wd ia pa's alias.
		assertSame(applicationContext.getBean("pa"), applicationContext.getBean("wd"));
	}

	@Test
	public void constrtor() throws Exception {
		// by type. should ignor type attribute. spring could find type.
		ConstrtorBean constructorBean = (ConstrtorBean) applicationContext.getBean("constructorBean");
		assertNotNull(constructorBean);
		// by index
		ConstrtorBean constructorBean2 = (ConstrtorBean) applicationContext.getBean("constructorBean2");
		assertNotNull(constructorBean2);
	}

	@Test
	public void scope() throws Exception {
		assertSame(applicationContext.getBean("singletonBean"),
				applicationContext.getBean("singletonBean"));
		assertNotSame(applicationContext.getBean("prototypeBean"),
				applicationContext.getBean("prototypeBean"));
	}

	@Test
	public void collection() throws Exception {
		assertEquals("admin", getCollectionBean().getProperties().getProperty("admin"));
		assertEquals("value", getCollectionBean().getMap().get("key"));
		assertEquals(3, getCollectionBean().getSet().size());
	}

	@Test
	public void nullObject() throws Exception {
		assertEquals(null, ((com.depp.stone.spring.bean.instantiation.Bean) applicationContext
				.getBean("objectNull")).getBar());
	}

	@Test
	public void dependsOn() throws Exception {
		// TODO
	}

	@Test
	public void autoWire() throws Exception {
		// TODO
	}

	@Test
	public void dependencyCheck() throws Exception {
		// TODO
	}

	@Test
	public void defaultLazyInit() throws Exception {
		// TODO
	}

	@Test
	public void customScope() throws Exception {
		// TODO
	}

	@Test
	public void methodReplace() throws Exception {
		// TODO
	}

	@Test
	public void factoryBean() throws Exception {
		assertEquals("我是中国DOTA的希望", applicationContext.getBean("hope"));
		assertTrue(applicationContext.getBean("&hope") instanceof DotaFactoryBean);
	}

	@Test
	public void methodInjection() throws Exception {
		LookupBean bean = (LookupBean) applicationContext.getBean("lookupBean");
		assertNotSame(bean.createCommander(), bean.createCommander());
	}

	@Test
	public void staticFactoryMethod() throws Exception {
		String bean = (String) applicationContext.getBean("instance");
		assertEquals("instance", bean);
	}

	@Test
	public void factoryMethod() throws Exception {
		String bean = (String) applicationContext.getBean("instance2");
		assertEquals("instance factory method", bean);
	}

	@Test
	public void parent() throws Exception {
		ChildBean child = (ChildBean) applicationContext.getBean("child");
		assertEquals("parent", child.getParent());
		assertEquals("child", child.getChild());
	}

	private CollectionBean getCollectionBean() {
		return (CollectionBean) applicationContext.getBean("collectionBean");
	}
}
