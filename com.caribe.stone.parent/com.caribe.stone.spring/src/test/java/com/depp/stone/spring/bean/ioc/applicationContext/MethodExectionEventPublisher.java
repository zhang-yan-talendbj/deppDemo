package com.depp.stone.spring.bean.ioc.applicationContext;

import java.util.ArrayList;
import java.util.List;

public class MethodExectionEventPublisher {

	private List<MethodExecutionEventListener> listeners = new ArrayList<MethodExecutionEventListener>();

	public void methodToMonitor() {
		MethodExecutionEvent event2Publish = new MethodExecutionEvent(this, "methodToMonitor");
		publishEvent(event2Publish, MethodEvextuionStatus.BENGIN);
		//do something special
		publishEvent(event2Publish, MethodEvextuionStatus.END);
	}

	private void publishEvent(MethodExecutionEvent event2Publish, String anObject) {
		ArrayList<MethodExecutionEventListener> copyListeners = new ArrayList<MethodExecutionEventListener>(
				listeners);
		for (MethodExecutionEventListener listener : copyListeners) {
			if (MethodEvextuionStatus.BENGIN.equals(anObject)) {
				listener.onMethodBegin(event2Publish);
			} else {
				listener.onMehtodAfter(event2Publish);
			}
		}
	}

	public void addMethodExecutionEventListener(MethodExecutionEventListener lis) {
		this.listeners.add(lis);
	}

	public void removeMethodExecutionEventListener(MethodExecutionEventListener lis) {
		if (listeners.contains(lis)) {
			this.listeners.remove(lis);
		}
	}

	public static void main(String[] args) {
		//A simple EventPublish for java Event.
		MethodExectionEventPublisher publisher = new MethodExectionEventPublisher();
		publisher.addMethodExecutionEventListener(new SimpleMethodExecutionEventListener());
		publisher.methodToMonitor();
	}
}
