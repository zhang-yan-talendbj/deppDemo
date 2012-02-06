package com.depp.stone.spring.bean.instantiation;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;

public class CreateIOCProvide {

	@Test
	public void createIOC() throws Exception {
		// create IOC provide with code
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();

		bindViaCode(bf);

		吕布 bean = (吕布) bf.getBean("knight");
		Assert.assertTrue(bean instanceof 吕布);
		Assert.assertTrue(bean.getHorse() instanceof 赤免);
		Assert.assertTrue(bean.getWeapon() instanceof 方天画戟);
	}

	@SuppressWarnings("deprecation")
	private void bindViaCode(DefaultListableBeanFactory bf) {
		AbstractBeanDefinition weapon = new RootBeanDefinition(方天画戟.class, true);
		AbstractBeanDefinition knight = new RootBeanDefinition(吕布.class, true);
		AbstractBeanDefinition horse = new RootBeanDefinition(赤免.class, true);
		bf.registerBeanDefinition("weapon", weapon);
		bf.registerBeanDefinition("knight", knight);
		bf.registerBeanDefinition("horse", horse);

		ConstructorArgumentValues constr = new ConstructorArgumentValues();
		constr.addIndexedArgumentValue(0, weapon);
		knight.setConstructorArgumentValues(constr);

		MutablePropertyValues property = new MutablePropertyValues();
		property.addPropertyValue("horse", horse);
		knight.setPropertyValues(property);
	}
}
