package com.caribe.stone.tapestry;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry.html.BasePage;

import com.caribe.stone.tapestry.pojo.Customer;

public abstract class ForeachDemo extends BasePage {
	/**
	 * foreach index attribute
	 */
	int foreachIndex = 100;

	public List getCustomerList() {
		List l = new ArrayList();
		l.add(new Customer(new Integer("1"), "Bruce", "*", true));
		l.add(new Customer(new Integer("2"), "sky", "*****", true));
		l.add(new Customer(new Integer("3"), "moon", "****", true));
		l.add(new Customer(new Integer("4"), "jack", "**", true));
		l.add(new Customer(new Integer("5"), "grubby", "****", false));
		return l;
	}

	public String[] getArray() {
		
		
		String str = ";";
		String[] split = str.split(";");
		System.out.println(split.length);
		return null;
	}

	public int getForeachIndex() {
		return foreachIndex;
	}

	public void setForeachIndex(int foreachIndex) {
		this.foreachIndex = foreachIndex;
	}
}
