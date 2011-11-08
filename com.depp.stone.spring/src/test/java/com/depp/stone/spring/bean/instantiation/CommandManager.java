package com.depp.stone.spring.bean.instantiation;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

public class CommandManager implements BeanFactoryAware {
	private BeanFactory factory;

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		// TODO Auto-generated method stub
		this.factory = beanFactory;

	}

	protected Commander createCommander() {
		return (Commander) factory.getBean("commander");//commander is prototype
	}

}

class Commander {

}
