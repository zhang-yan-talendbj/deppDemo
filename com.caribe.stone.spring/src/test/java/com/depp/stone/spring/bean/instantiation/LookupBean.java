package com.depp.stone.spring.bean.instantiation;

public abstract class LookupBean {
	public Object process() {
		return createCommander();
	}

	public abstract Commander createCommander();
}
