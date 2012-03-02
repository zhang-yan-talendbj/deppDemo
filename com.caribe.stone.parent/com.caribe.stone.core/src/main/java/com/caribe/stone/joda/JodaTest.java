package com.caribe.stone.joda;

import static org.junit.Assert.*;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.junit.Test;

public class JodaTest {

	@Test
	public void testName() throws Exception {
		assertTrue(true);
		
		DateTime startInstant = new DateTime(2011, 1, 1, 0, 0, 0, 0);
		Period p = new Period(startInstant,new DateTime(2012, 1, 1, 0, 0, 0, 0),PeriodType.days());
		
		System.out.println(startInstant);
		System.out.println(p.getDays());
	}
	
}
