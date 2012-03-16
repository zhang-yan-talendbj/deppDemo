package com.caribe.stone.joda;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

import com.caribe.stone.utils.DateUtils;

public class JodaTest {

	@Test
	public void testName() throws Exception {
		assertEquals(
				-1,
				new Period(new DateTime(DateUtils.parse("2012/12/12")), new DateTime(DateUtils
						.parse("2011/12/11")), PeriodType.years()).getYears());
		assertEquals(
				1,
				new Period(new DateTime(DateUtils.parse("2011/12/12")), new DateTime(DateUtils
						.parse("2012/12/12")), PeriodType.years()).getYears());
		assertEquals(
				-366,
				new Period(new DateTime(DateUtils.parse("2012/12/11")), new DateTime(DateUtils
						.parse("2011/12/11")), PeriodType.days()).getDays());
	}

	@Test
	public void compareDate() throws Exception {
		DateTime now = new DateTime(DateUtils.parse("2012/12/12"));
		DateTime tmp = new DateTime(DateUtils.parse("2011/10/11"));
		System.out.println(now.getMonthOfYear());
		System.out.println(now.getDayOfMonth());

		System.out.println(tmp.getMonthOfYear());
	}

	@Test
	public void format() throws Exception {
		DateTime dateTime = new DateTime(2012, 12, 25, 0, 0, 0, 0);
		assertEquals("/25-Dec", dateTime.toString("/dd-MMM"));
	}
	
	@Test
	public void plusAndMinus() throws Exception {
		DateTime d = new DateTime(2012,12,25,0,0,0,0);
		DateTimeFormatter pattern = DateTimeFormat.forPattern("yyyy/MM/dd");
		assertEquals("2012/12/25", d.toString(pattern));
		assertEquals("2013/12/25", d.plusYears(1).toString(pattern));
		assertEquals("2012/12/25", d.toString(pattern));
		assertEquals("2012/12/24", d.minusDays(1).toString(pattern));
		
	}

	@Test
	public void betwenMonth() throws Exception {
		// assertTrue(isAcross(DateUtils.parse("2011/10/11"),
		// DateUtils.parse("2011/10/12"), ""));
		assertTrue(isAcross(DateUtils.parse("2011/10/11", "yyyy/MM/dd"),
				DateUtils.parse("2012/10/11", "yyyy/MM/dd"), ""));
		assertTrue(isAcross(DateUtils.parse("2011/10/11", "yyyy/MM/dd"),
				DateUtils.parse("2014/10/11", "yyyy/MM/dd"), ""));
		assertFalse(isAcross(DateUtils.parse("2011/10/11", "yyyy/MM/dd"),
				DateUtils.parse("2012/10/1", "yyyy/MM/dd"), null));
		assertTrue(isAcross(DateUtils.parse("2011/10/12", "yyyy/MM/dd"),
				DateUtils.parse("2012/05/10", "yyyy/MM/dd"), "(10/5)"));
		assertTrue(isAcross(DateUtils.parse("2011/10/12", "yyyy/MM/dd"),
				DateUtils.parse("2012/05/10", "yyyy/MM/dd"), "(12/10)"));
		assertFalse(isAcross(DateUtils.parse("2011/10/12", "yyyy/MM/dd"),
				DateUtils.parse("2012/10/10", "yyyy/MM/dd"), "(11/10)"));
		assertFalse(isAcross(DateUtils.parse("2012/10/01", "yyyy/MM/dd"),
				DateUtils.parse("2012/10/10", "yyyy/MM/dd"), "(30/9)"));
	}

	@Test
	public void testFormatDate() throws Exception {
		Date date = new Date();
		System.out.println(date.getTime());
		System.out.println(new DateTime(date).getMillis());
	}

	public boolean isAcross(Date from, Date to, String claimEscalationDateString) throws ParseException {
		DateTime dateTimeFrom = new DateTime(from);
		DateTime dateTimeTo = new DateTime(to);
		Period period = new Period(dateTimeFrom, dateTimeTo, PeriodType.years());
		if (period.getYears() >= 1) {
			return true;
		}

		if (claimEscalationDateString == null) {
			return false;
		}
		Date claimEscalationDate = DateUtils.parse(claimEscalationDateString, "(d/M)");
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(claimEscalationDate);
		calendar.set(Calendar.YEAR, dateTimeFrom.getYear());
		if (calendar.getTime().getTime() >= from.getTime()
				&& calendar.getTime().getTime() <= to.getTime()) {
			return true;
		}
		calendar.set(Calendar.YEAR, dateTimeTo.getYear());
		if (calendar.getTime().getTime() >= from.getTime()
				&& calendar.getTime().getTime() <= to.getTime()) {
			return true;
		}
		return false;
	}
}
