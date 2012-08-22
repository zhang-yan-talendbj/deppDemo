package com.depp.stone.spring.bean.ioc.applicationContext;

import java.util.EventListener;

public interface MethodExecutionEventListener extends EventListener {

	void onMethodBegin(MethodExecutionEvent event);

	void onMehtodAfter(MethodExecutionEvent event);
}
