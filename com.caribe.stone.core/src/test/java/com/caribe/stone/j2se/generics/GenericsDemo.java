package com.caribe.stone.j2se.generics;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;

public class GenericsDemo {
	
	public void inspect(List<Father> list) {
		for (Object obj : list) {
			System.out.println(obj);
		}
		Son son = null;
		list.add(son); // 这个操作在当前方法的上下文是合法的。
	}

	void printCollection(Collection<?> c) {
		for (Object e : c) {
			System.out.println(e);
		}
	}

	public void test() {
		List<Object> strs = new ArrayList<Object>();
		// inspect(strs); // 编译错误
	}

	public void wildcard(List<? extends Number> list) {
		list.get(1).intValue();
	}
	
	public static void main(String[] args) {
		List<String> arrayList = new ArrayList<String>();
		unsafeAdd(arrayList,new Integer(44));
		String s = arrayList.get(0);
	}

	private static void unsafeAdd(List arrayList, Integer integer) {
		arrayList.add(integer);
	}
}

class Father {

}

class Son extends Father {

}