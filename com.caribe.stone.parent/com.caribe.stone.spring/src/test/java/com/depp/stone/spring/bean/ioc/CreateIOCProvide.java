package com.depp.stone.spring.bean.ioc;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

public class CreateIOCProvide {

	/**
	 * create IOC provide with code
	 * 
	 * @throws Exception
	 */
	@Test
	public void createIOC() throws Exception {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		bindViaCode(beanFactory);

		checkBean(beanFactory);
	}

	private void checkBean(DefaultListableBeanFactory bf) {
		PhantomAssassin bean = (PhantomAssassin) bf.getBean("pa");
		assertNotNull(bean);
		assertNotNull(bean.getShoes());
		assertNotNull(bean.getWeapon());
	}

	@SuppressWarnings("deprecation")
	private void bindViaCode(DefaultListableBeanFactory bf) {
		AbstractBeanDefinition weapon = new RootBeanDefinition(Battlefury.class, true);
		AbstractBeanDefinition pa = new RootBeanDefinition(PhantomAssassin.class, true);
		AbstractBeanDefinition shoes = new RootBeanDefinition(FlyingShoes.class, true);
		// register BeanDefinition
		bf.registerBeanDefinition("weapon", weapon);
		bf.registerBeanDefinition("pa", pa);
		bf.registerBeanDefinition("shoes", shoes);
		// via constructor
		ConstructorArgumentValues constr = new ConstructorArgumentValues();
		constr.addIndexedArgumentValue(0, weapon);
		pa.setConstructorArgumentValues(constr);
		// via setter method
		MutablePropertyValues property = new MutablePropertyValues();
		property.addPropertyValue("shoes", shoes);
		pa.setPropertyValues(property);
	}

	@Test
	public void testIocProvider() throws Exception {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(bf);
		reader.loadBeanDefinitions("classpath:com/depp/stone/spring/bean/ioc/bean.xml");

		checkBean(bf);
	}

	@Test
	public void annotation() throws Exception {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(bf);
		reader.loadBeanDefinitions("classpath:com/depp/stone/spring/bean/ioc/bean-annotation.xml");

		checkBean(bf);
	}

	@Test
	public void beanWrapper() throws Exception {
		Object pa = Class.forName("com.depp.stone.spring.bean.ioc.PhantomAssassin").newInstance();
		Object battlefury = Class.forName("com.depp.stone.spring.bean.ioc.Battlefury").newInstance();
		Object shoes = Class.forName("com.depp.stone.spring.bean.ioc.FlyingShoes").newInstance();
		// use BeanWrapper is very simple to set property
		BeanWrapperImpl bean = new BeanWrapperImpl(pa);
		bean.setPropertyValue("weapon", battlefury);
		bean.setPropertyValue("shoes", shoes);
		assertTrue(bean.getWrappedInstance() instanceof PhantomAssassin);
		assertSame(battlefury, bean.getPropertyValue("weapon"));
	}

	@Test
	public void beanPostProcessor() throws Exception {
		// TODO

	}

	@Test
	public void testXmlBeanFactory() throws Exception {
		ClassPathResource res = new ClassPathResource("com/depp/stone/spring/bean/ioc/beans.xml");
		DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
		reader.loadBeanDefinitions(res);

		assertEquals("bruce", factory.getBean("str"));
	}

}
