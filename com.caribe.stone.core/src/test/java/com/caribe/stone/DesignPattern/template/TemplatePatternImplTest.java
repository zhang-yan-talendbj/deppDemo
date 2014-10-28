package com.caribe.stone.DesignPattern.template;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class TemplatePatternImplTest {

	@Test
	public void testMethod3() {
		TemplatePattern template = new TemplatePatternImpl();
		StatementCallback callback = new StatementCallback() {

			@Override
			public Object doInCalback(List list) {
				// TODO Auto-generated method stub
				list.add("123");
				list.add("456");
				list.add("xyz");
				return list;
			}
		};
		template.templateMethod(callback);
	}

}
