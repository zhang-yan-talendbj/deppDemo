package com.depp.stone.spring.bean.instantiation;

public class InitBean {
	private String initMsg;

	public String getInitMsg() {
		return initMsg;
	}

	public void setInitMsg(String initMsg) {
		this.initMsg = initMsg;
	}

	public void init() {
		setInitMsg("have been initialed");
	}
}
