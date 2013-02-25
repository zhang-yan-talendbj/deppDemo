package com.caribe.stone.j2se.collection.list;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.junit.Test;

public class CollectionsTest {

	@Test
	public void sort() throws Exception {
		List<Date> list = new ArrayList<Date>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		list.add(sdf.parse("20121216"));
		list.add(sdf.parse("20121212"));
		list.add(sdf.parse("20121213"));
		list.add(sdf.parse("20121215"));
		list.add(sdf.parse("20121214"));
		Comparator<Date> com = new Comparator<Date>() {

			@Override
			public int compare(Date o1, Date o2) {
				return o1.compareTo(o2);
			}
		};
		Collections.sort(list, com);
		assertTrue(list.get(0).compareTo(list.get(1))==-1);
	}
	
	@Test
	public void filter() throws Exception {
		List<String> list = initData();
		
		CollectionUtils.filter(list, new Predicate() {
			
			@Override
			public boolean evaluate(Object object) {
				String str=(String) object;
				return str.length()!=3;
			}
		});
		assertEquals(4, list.size());
	}
	
	@Test(expected=ConcurrentModificationException.class)
	public void removeFromIterator() throws Exception {
		List<String> list = initData();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			if(string.length()==3){
				list.remove(string);
			}
		}
	}

	public List<String> initData() {
		List<String> list=new ArrayList<String>();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("100");
		list.add("101");
		list.add("102");
		list.add("103");
		return list;
	}
	
}
