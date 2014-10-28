package com.caribe.stone.utils;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.junit.Test;

public class DateTest {

	@Test
	public void formatDate() throws Exception {
		SimpleDateFormat sdf1 = new SimpleDateFormat("(d/M)");
		GregorianCalendar calendar = new GregorianCalendar(2012, 11, 11);
		assertEquals("(11/12)", sdf1.format(calendar.getTime()));
	}

	@Test
	public void testName() throws Exception {
		SimpleDateFormat sdf1 = new SimpleDateFormat("/dd-MMM", Locale.UK);
		Date time = new GregorianCalendar(2012, 2, 15).getTime();
		assertEquals("/15-Mar", sdf1.format(time));
	}

	@Test
	public void compare() throws Exception {
		GregorianCalendar calendar = new GregorianCalendar();
		GregorianCalendar tmp = new GregorianCalendar();
		System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
		System.out.println(calendar.get(Calendar.MONTH));
		if (calendar.get(Calendar.DAY_OF_MONTH) >= tmp.get(Calendar.DAY_OF_MONTH)) {

		}
	}
}
