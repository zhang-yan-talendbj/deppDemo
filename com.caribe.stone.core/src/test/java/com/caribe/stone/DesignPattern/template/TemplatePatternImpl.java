package com.caribe.stone.DesignPattern.template;

import java.util.List;

public class TemplatePatternImpl extends TemplatePattern {

	@Override
	protected void method3() {
		// System.out.println("method3()在子类TemplatePatternImpl中实现了！！");
	}
	 @Override
	protected void method2() {
//		 System.out.println("子类TemplatePatternImpl2覆盖了父类的method2()方法！！");
	}
	 
	 //to many abstract methods... :<
	@Override
	protected void method4() {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected void method5() {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected void method6() {
		// TODO Auto-generated method stub
	}

}