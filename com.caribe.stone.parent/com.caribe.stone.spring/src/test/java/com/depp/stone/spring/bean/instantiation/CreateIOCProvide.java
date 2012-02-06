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

		PhantomAssassin bean = (PhantomAssassin) bf.getBean("knight");
		Assert.assertTrue(bean instanceof PhantomAssassin);
		Assert.assertTrue(bean.getShoes() instanceof FlyingShoes);
		Assert.assertTrue(bean.getWeapon() instanceof Battlefury);
	}

	@SuppressWarnings("deprecation")
	private void bindViaCode(DefaultListableBeanFactory bf) {
		AbstractBeanDefinition weapon = new RootBeanDefinition(Battlefury.class, true);
		AbstractBeanDefinition knight = new RootBeanDefinition(PhantomAssassin.class, true);
		AbstractBeanDefinition horse = new RootBeanDefinition(FlyingShoes.class, true);
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
