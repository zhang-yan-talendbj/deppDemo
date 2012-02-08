package com.depp.stone.spring.bean.ioc.applicationContext;

import java.util.EventObject;

public class MethodExecutionEvent extends EventObject {

	public MethodExecutionEvent(Object source) {
		super(source);
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 176932104851433981L;
	private String methodName;

	public MethodExecutionEvent(Object source, String methodName) {
		super(source);
		this.methodName = methodName;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
}
