package com.depp.stone.spring.bean.ioc;

import org.springframework.beans.factory.FactoryBean;

public class DotaFactoryBean implements FactoryBean{
	

	@Override
	public Object getObject() throws Exception {
		// TODO Auto-generated method stub
		return "我是中国DOTA的希望";
	}

	@Override
	public Class getObjectType() {
		// TODO Auto-generated method stub
		return String.class;
	}

	@Override
	public boolean isSingleton() {
		// TODO Auto-generated method stub
		return false;
	}

}
