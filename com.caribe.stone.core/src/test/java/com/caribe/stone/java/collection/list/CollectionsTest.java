package com.caribe.stone.java.collection.list;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

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
		System.out.println(list);
	}
}
