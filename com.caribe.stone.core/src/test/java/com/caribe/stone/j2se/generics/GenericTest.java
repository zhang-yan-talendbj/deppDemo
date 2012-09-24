package com.caribe.stone.j2se.generics;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class GenericTest {

	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	@Test(expected = ClassCastException.class)
	public void unusedGeneric() throws Exception {
		List list1 = new ArrayList();
		list1.add(8080); // 编译器不检查值
		String str = (String) list1.get(0);
		// 如转换类型与原数据类型不一致将抛出ClassCastException异常
	}

	@Test
	public void useGeneric() throws Exception {
		List<String> list2 = new ArrayList<String>();
		list2.add("value"); // [类型安全的写入数据] 编译器检查该值,该值必须是String类型才能通过编译
		assertEquals("value", list2.get(0)); // [类型安全的读取数据] 不需要手动转换
	}

	@Test
	public void typeErasure() throws Exception {
		List<String> list1 = new ArrayList<String>();
		List<Integer> list2 = new ArrayList<Integer>();

		assertEquals(list1.getClass(), list2.getClass());
		assertEquals("class java.util.ArrayList", list1.getClass().toString());
	}

	@Test
	public void wildcard() throws Exception {
		@SuppressWarnings("unused")
		List<?> list;
		List<Object> list1 = null;
		List<String> list2 = null;

		list = list1;
		list = list2;
	}

	@Test
	public void boundedWildcards() throws Exception {
		@SuppressWarnings("unused")
		ArrayList<? extends Number> collection = null;

		collection = new ArrayList<Number>();
		collection = new ArrayList<Short>();
		collection = new ArrayList<Integer>();
		collection = new ArrayList<Long>();
		collection = new ArrayList<Float>();
		collection = new ArrayList<Double>();

		@SuppressWarnings("unused")
		ArrayList<? super Integer> collection1 = null;

		collection1 = new ArrayList<Object>();
		collection1 = new ArrayList<Number>();
		collection1 = new ArrayList<Integer>();
	}
}
