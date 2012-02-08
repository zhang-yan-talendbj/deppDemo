package com.depp.stone.spring.bean.ioc.applicationContext;

public class SimpleMethodExecutionEventListener implements MethodExecutionEventListener {

	@Override
	public void onMethodBegin(MethodExecutionEvent event) {
		System.out.println("Method name:"+event.getMethodName());
	}

	@Override
	public void onMehtodAfter(MethodExecutionEvent event) {
		System.out.println("Finished to execute the method :"+event.getMethodName());
	}

}
