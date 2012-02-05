package com.caribe.stone.com.caribe.stone.core;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

public class ListTest {

	@Test
	public void testSubList() throws Exception {

		List l = new ArrayList();
		for (int i = 0; i < 100; i++) {
			l.add(i);
		}
		Assert.assertEquals(100, l.size());
		List<String> subList = l.subList(0, 5);
		Assert.assertEquals(5, subList.size());
		Assert.assertEquals(0, l.get(0));
		Assert.assertEquals(5, l.get(5));
		
	}
}
