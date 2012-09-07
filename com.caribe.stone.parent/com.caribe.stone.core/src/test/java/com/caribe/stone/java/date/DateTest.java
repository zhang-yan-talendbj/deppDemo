package com.caribe.stone.java.date;

import java.util.Date;

import org.junit.Test;

public class DateTest {

	@Test
	public void test() {
		Date x = new Date();
		Date x9=new Date(x.getTime()+(long)90 *24 * 60 * 60 * 1000);
		System.out.println(x);
		System.out.println(x9);
	}

}
