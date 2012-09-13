package com.caribe.stone.DesignPattern.template;

import java.util.ArrayList;
import java.util.List;

/**
 * From: http://www.iteye.com/topic/713770
 * 
 * @author jakoes
 * 
 */
public abstract class TemplatePattern {
	// 模板方法
	public final void templateMethod(StatementCallback callback) {

		method1();
		method2();// 勾子方法
		method3();// 抽象方法
		method4();// 抽象方法
		method5();// 抽象方法
		method6();// 抽象方法
		// 回调方法
		Object callbackMethod = callbackMethod(callback);
		// System.out.println(callbackMethod);
	}

	private Object callbackMethod(StatementCallback callback) {
		List list = new ArrayList();
		return callback.doInCalback(list);
	}

	private void method1() {
		// System.out.println("父类实现业务逻辑");
	}

	protected void method2() {
		// System.out.println("父类默认实现，子类可覆盖");
	}

	protected abstract void method3();// 子类负责实现业务逻辑

	protected abstract void method4();// 子类负责实现业务逻辑

	protected abstract void method5();// 子类负责实现业务逻辑

	protected abstract void method6();// 子类负责实现业务逻辑
}
