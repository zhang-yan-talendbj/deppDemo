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
		List<String> subList = l.subList(0, 1);
		Assert.assertEquals(1, subList.size());
		Assert.assertEquals(0, subList.get(0));
		
	}
}
