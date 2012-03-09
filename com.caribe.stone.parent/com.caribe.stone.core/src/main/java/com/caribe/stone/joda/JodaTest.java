package com.caribe.stone.joda;

import static org.junit.Assert.*;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.junit.Test;

import com.caribe.stone.utils.DateUtils;

public class JodaTest {

	@Test
	public void testName() throws Exception {
		assertEquals(-1, new Period(new DateTime(DateUtils.parse("2012/12/12")), new DateTime(DateUtils.parse("2011/12/11")), PeriodType.years()).getYears());
		assertEquals(1, new Period(new DateTime(DateUtils.parse("2011/12/12")), new DateTime(DateUtils.parse("2012/12/12")), PeriodType.years()).getYears());
		assertEquals(-366, new Period(new DateTime(DateUtils.parse("2012/12/11")), new DateTime(DateUtils.parse("2011/12/11")), PeriodType.days()).getDays());
	}
	
	@Test
	public void compareDate() throws Exception {
		DateTime now = new DateTime(DateUtils.parse("2012/12/12"));
		DateTime tmp = new DateTime(DateUtils.parse("2011/10/11"));
		System.out.println(now.getMonthOfYear());
		System.out.println(now.getDayOfMonth());
		
		System.out.println(tmp.getMonthOfYear());
	}
}
