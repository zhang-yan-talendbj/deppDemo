package com.caribe.stone.tapestry;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry.html.BasePage;

import com.caribe.stone.tapestry.pojo.Customer;

public abstract class ForeachDemo extends BasePage {
	/**
	 * foreach index attribute 
	 */
	int foreachIndex=100;
	public  List getCustomerList(){
		List l=new ArrayList();
		l.add(new Customer(new Integer("1"), "Bruce", "*"));
		l.add(new Customer(new Integer("2"), "sky", "*****"));
		l.add(new Customer(new Integer("3"), "moon", "****"));
		l.add(new Customer(new Integer("4"), "jack", "**"));
		l.add(new Customer(new Integer("5"), "grubby", "****"));
		return l;
		
	}
	public int getForeachIndex() {
		return foreachIndex;
	}
	public void setForeachIndex(int foreachIndex) {
		this.foreachIndex = foreachIndex;
	}
}
