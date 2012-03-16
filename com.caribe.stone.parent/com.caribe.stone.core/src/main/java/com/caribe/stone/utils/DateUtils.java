package com.caribe.stone.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;

public class DateUtils {
	public static final DateFormat DEFAULT_TIMESTAMP_FORMAT = new SimpleDateFormat(
			"dd-MM-yyyy HH:mm:ss (SSS) z");
	public static final DateFormat DEFAULT_REPORT_TIMESTAMP_FORMAT = new SimpleDateFormat(
			"dd/MM/yyyy HH:mm:ss (SSS)");
	public static final DateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd");
	public static final DateFormat DATE_AND_TIME_FORMAT = new SimpleDateFormat("d/M/yyyy HH:mm");
	public static final DateFormat SERVLET_DATE_FORMAT = new SimpleDateFormat("dd-MMM-yyyy");

	public static final DateFormat DATE_FORMAT_YYYY = new SimpleDateFormat("yyyy");
	public static final DateFormat DATE_FORMAT_MMMM = new SimpleDateFormat("MMMM");
	public static final long MS_PER_DAY = 24 * 60 * 60 * 1000;

	private static final int MONTH_OF_FINANCIAL_YEAR_END = 11;
	private static final int DAY_OF_MONTH_OF_FINANCIAL_YEAR_END = 30;

	protected DateUtils() {
	}

	/**
	 * Calculates the number of days between two calendar days in a manner which
	 * is independent of the Calendar type used.
	 * 
	 * @param d1
	 *            The first date.
	 * @param d2
	 *            The second date.
	 * 
	 * @return The number of days between the two dates. Zero is returned if the
	 *         dates are the same, one if the dates are adjacent, etc. If from
	 *         is after to, they are reversed, so the return value is always >=
	 *         0
	 */
	public static long calculateDaysBetween(Date from, Date to) {
		if (from == null || to == null) {
			throw new IllegalArgumentException("null from or to date");
		}

		Calendar d1 = new GregorianCalendar();
		Calendar d2 = new GregorianCalendar();
		d1.clear();
		d2.clear();
		if (from.after(to)) {
			d1.setTime(to);
			d2.setTime(from);
		} else {
			d1.setTime(from);
			d2.setTime(to);
		}

		int days = d2.get(Calendar.DAY_OF_YEAR) - d1.get(Calendar.DAY_OF_YEAR);
		int y2 = d2.get(Calendar.YEAR);
		if (d1.get(Calendar.YEAR) != y2) {
			d1 = (Calendar) d1.clone();
			do {
				days += d1.getActualMaximum(Calendar.DAY_OF_YEAR);
				d1.add(Calendar.YEAR, 1);
			} while (d1.get(Calendar.YEAR) != y2);
		}
		return days;
	}

	public static long calculateWorkingDaysBetween(Date from, Date to) {
		Date earlierDate, laterDate;
		int startDayOfWeek, endDayOfWeek;
		if (from.after(to)) {
			earlierDate = moveForwardFromWeekend(to);
			laterDate = moveBackwardFromWeekend(from);
		} else {
			earlierDate = moveForwardFromWeekend(from);
			laterDate = moveBackwardFromWeekend(to);
		}
		long daysBetween = calculateDaysBetween(earlierDate, laterDate);
		startDayOfWeek = getDayOfWeek(earlierDate);
		endDayOfWeek = getDayOfWeek(laterDate);
		long adjustment = 0;
		if (startDayOfWeek <= endDayOfWeek) {
			adjustment = 5;
		}

		return 5 * (daysBetween / 7) + (Calendar.SATURDAY - startDayOfWeek - 1) + (endDayOfWeek - 1)
				- adjustment;
	}

	public static int getDayOfWeek(Date date) {
		Calendar cal = new GregorianCalendar();
		cal.clear();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_WEEK);
	}

	public static int getWeekOfYear(Date date) {
		Calendar cal = new GregorianCalendar();
		cal.clear();
		cal.setTime(date);
		return cal.get(Calendar.WEEK_OF_YEAR);
	}

	public static Date moveForwardFromWeekend(Date date) {
		Calendar cal = new GregorianCalendar();

		cal.clear();
		cal.setTime(date);
		if ((cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)) {
			return new Date(date.getTime() + 2 * MS_PER_DAY);
		} else if ((cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)) {
			return new Date(date.getTime() + MS_PER_DAY);
		} else {
			return date;
		}
	}

	public static Date moveBackwardFromWeekend(Date date) {
		Calendar cal = new GregorianCalendar();

		cal.clear();
		cal.setTime(date);
		if ((cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)) {
			return new Date(date.getTime() - MS_PER_DAY);
		} else if ((cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)) {
			return new Date(date.getTime() - 2 * MS_PER_DAY);
		} else {
			return date;
		}
	}

	public static Date createStartOfDay(int year, int actualMonth, int day) {
		Calendar calendar = new GregorianCalendar();
		calendar.clear();
		calendar.set(year, actualMonth - 1, day);
		return zeroOutTime(calendar);
	}

	public static Date zeroOutTime(Date date) {
		if (date == null) {
			return null;
		}
		Calendar calendar = new GregorianCalendar();
		calendar.clear();
		calendar.setTime(date);
		return zeroOutTime(calendar);
	}

	public static Date maxOutTime(Date date) {
		if (date == null) {
			return null;
		}
		Calendar calendar = new GregorianCalendar();
		calendar.clear();
		calendar.setTime(date);
		return maxOutTime(calendar);
	}

	public static Date zeroOutTime(Calendar calendar) {
		if (calendar == null) {
			return null;
		}
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	public static Date maxOutTime(Calendar calendar) {
		if (calendar == null) {
			return null;
		}
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime();
	}

	/**
	 * Create a date with a time at the start of the next day.
	 * 
	 * @param Date
	 *            referenceDate
	 * @return Date the reference date + 1 day, with the time cleared.
	 */
	public static Date createStartOfNextDay(Date referenceDate) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(referenceDate);
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		return zeroOutTime(calendar);
	}

	// /**
	// * Create a date with a time at the start of tommorrow.
	// *
	// * @return Date tommorrow, with the time cleared.
	// */
	// public static Date createStartOfNextDay() {
	// Calendar calendar = new GregorianCalendar();
	// calendar.setTime(CurrentTimeService.getCurrentDateTime());
	// calendar.add(Calendar.DAY_OF_YEAR, 1);
	// return zeroOutTime(calendar);
	// }

	/**
	 * Create a date with a time at the start of the previous day.
	 * 
	 * @param Date
	 *            referenceDate
	 * @return Date the reference date - 1 day, with the time cleared.
	 */
	public static Date createStartOfPreviousDay(Date referenceDate) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(referenceDate);
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		return zeroOutTime(calendar);
	}

	/**
	 * Create a date with a time at the start of the previous day.
	 * 
	 * @return Date today - 1 day, with the time cleared.
	 */
	// public static Date createStartOfPreviousDay() {
	// Calendar calendar = new GregorianCalendar();
	// calendar.setTime(CurrentTimeService.getCurrentDateTime());
	// calendar.add(Calendar.DAY_OF_YEAR, -1);
	// return zeroOutTime(calendar);
	// }

	/**
	 * Parses the string argument as a Date.
	 * 
	 * @param value
	 *            Date in string.
	 * @param pattern
	 *            date format of date.
	 * @return the date represented by the argument in Date.
	 * @throws IllegalArgumentException
	 *             If the value is not parsable
	 */
	// public static Date parseStringForDate(String value, String pattern)
	// throws IllegalArgumentException {
	// if (StringUtils.isEmpty(pattern)) {
	// throw new IllegalArgumentException("Invalid pattern :" + pattern);
	// }
	// if (StringUtils.isEmpty(value)) {
	// throw new IllegalArgumentException("Invalid value :" + pattern);
	// }
	// try {
	// return new SimpleDateFormat(pattern).parse(value);
	// }
	// catch (ParseException e) {
	// throw new IllegalArgumentException(e.getMessage());
	// }
	// }

	/**
	 * method to find date by adding offset like days, weeks, months. Ignores
	 * month end issues.
	 * 
	 * @param Date
	 *            baseDate, The Base Date
	 * @param int unit, offset field which is reperesented by calendar object
	 * @param int value, number of offsets to move - can be negative
	 * @return Date, The New Date after adding the offset
	 */
	public static Date addRawOffsetToDate(Date baseDate, int unit, int value) {
		Calendar calendar = new GregorianCalendar();
		calendar.clear();
		calendar.setTime(baseDate);
		calendar.add(unit, value);
		return calendar.getTime();
	}

	/**
	 * Check if a date is between two dates (inclusive - so between(a,a,a) ==
	 * true).
	 * 
	 * @param target
	 * @param left
	 *            (null = -INF) (ie. don't check that left &lt;= target)
	 * @param right
	 *            (null = +INF) (ie. don't check that target &lt;= right)
	 * @return true IFF left &lt;= target &lt;= right
	 */
	public static boolean between(Date target, Date left, Date right) {
		return !((left != null && target.before(left)) || (right != null && target.after(right)));
	}

	public static String formattedDate(Date date) {
		return DEFAULT_DATE_FORMAT.format(date);
	}

	public static String formattedDateAndTime(Date date) {
		return DATE_AND_TIME_FORMAT.format(date);
	}

	public static Date parse(String input) throws ParseException {
		if (input == null) {
			return null;
		} else {
			return DEFAULT_DATE_FORMAT.parse(input);
		}
	}
	public static Date parse(String input,String fromformat) throws ParseException {
		if (input == null || fromformat==null) {
			return null;
		} else {
			return new SimpleDateFormat(fromformat).parse(input);
		}
	}

	/**
	 * acutalMonthInNumberic. Java stores months 0,1,2 as Jan, Feb, Mar This
	 * util method gives 1,2,3 as Jan, Feb, Mar
	 */
	public static String actualMonthInNumeric(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int month = calendar.get(Calendar.MONTH) + 1;
		if (month < 10) {
			return "0" + month;
		}
		return "" + month;
	}

	public static Date endOfDay(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime();
	}

	/**
	 * Finds the number of calendar months and days between two dates.
	 * 
	 * @param date1
	 * @param date2
	 * @return A long array with two elements such that long[0] = Number of
	 *         calendar months between date1 and date2 long[1] = Number of
	 *         additional days that don't fall into calendar month
	 * @throws IllegalArgumentException
	 *             If date1 or date2 is null
	 * @throws IllegalArgumentException
	 *             If date1 is after date2
	 */
	public static long[] monthsBetween(Date date1, Date date2) {
		if (date1 == null || date2 == null) {
			throw new IllegalArgumentException("null date");
		}
		if (date1.after(date2)) {
			throw new IllegalArgumentException("date1 must be on or before date2");
		}

		DateTime dateTime1 = new DateTime(date1);
		DateTime dateTime2 = new DateTime(date2);
		Period actualPeriod = new Period(dateTime1, dateTime2, PeriodType.yearMonthDayTime());
		Period extraDays = actualPeriod.minusMonths(actualPeriod.getMonths());
		return new long[] { actualPeriod.getYears() * 12 + actualPeriod.getMonths(), extraDays.getDays() };

	}

	public static boolean areConsecutiveMonths(Calendar calendar1, Calendar calendar2) {
		return Math.abs(calendar1.get(Calendar.MONTH) - calendar2.get(Calendar.MONTH)) == 1;
	}

	public static boolean areConsecutiveYears(Calendar calendar1, Calendar calendar2) {
		return Math.abs(calendar1.get(Calendar.YEAR) - calendar2.get(Calendar.YEAR)) == 1;
	}

	/**
	 * Get a date representing the end of the day before this one.
	 * 
	 * @param date
	 * @return
	 */
	public static Date getEndOfDayBefore(Date date) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		return endOfDay(calendar.getTime());
	}

	/**
	 * Get a date as defined by the following: - If the hour is less than the
	 * pivotHour then the end of the previous day - If the hour is greater than
	 * the pivotHour then the end of this day.
	 * 
	 * @param date
	 * @param pivotHour
	 * @return
	 */
	public static Date getEndOfDayUsingPivot(Date date, long pivotHour) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		if (calendar.get(Calendar.HOUR_OF_DAY) < pivotHour) {
			return getEndOfDayBefore(date);
		} else {
			return endOfDay(date);
		}
	}

	/**
	 * Get a end of month for a given date.
	 * 
	 * @param date
	 * 
	 * @return
	 */
	public static Date createEndOfMonth(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}

	/**
	 * Gets the starting day of the USA financial year that occured before the
	 * current date.
	 * 
	 * @param current
	 *            the date that is after the current financial year
	 * @return Start Date of the USA financial year note that if the current
	 *         date is the start of the financial year it will return that.
	 */
	public static Date startOfUSAFinancialYearBefore(Date current) {
		if (current == null) {
			throw new IllegalArgumentException("Invalid date to find start of USA financial year: Null");
		}

		Calendar calendar = new GregorianCalendar();
		calendar.setTime(current);
		int month = calendar.get(Calendar.MONTH) + 1;
		int year;

		if (month < 12) {
			year = calendar.get(Calendar.YEAR) - 1;
		} else {
			year = calendar.get(Calendar.YEAR);
		}
		return DateUtils.createStartOfDay(year, 12, 1);
	}

	public static int compareIgnoringTime(Date d1, Date d2) {
		d1 = zeroOutTime(d1);
		d2 = zeroOutTime(d2);

		return d1.compareTo(d2);
	}

	public static Date getStartOfMonth(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}

	public static Date getEndOfMonth(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}

	public static Date getStartOfPreviousMonth(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}

	public static Date getEndOfPreviousMonth(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}

	public static Date getReportStartOfMonth(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		if (calendar.get(Calendar.DAY_OF_MONTH) <= 15) {
			return getStartOfPreviousMonth(date);
		} else {
			return getStartOfMonth(date);
		}
	}

	public static Date getReportEndOfMonth(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		if (calendar.get(Calendar.DAY_OF_MONTH) <= 15) {
			return getEndOfPreviousMonth(date);
		} else {
			return getEndOfMonth(date);
		}
	}

	public static boolean doDateRangesOverlap(Date from1, Date to1, Date from2, Date to2) {
		if (from2.compareTo(from1) <= 0) {
			// F2 <= F1
			return to2.compareTo(from1) >= 0; // T2 >= F1
		} else {
			return from2.compareTo(to1) <= 0; // F2 <= T1
		}
	}

	public static int getFinancialYear(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(endOfFinancialYear(date));
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * Returns the financial year quarter for a date. Note: This is Strict mode,
	 * returns quarter for a given date.
	 */
	public static int getStrictUSFYQuarter(Date date) {
		int strictMonth = month(date);

		if (strictMonth == 11) {
			strictMonth = -1;
		}
		++strictMonth;

		return (strictMonth / 3) + 1;
	}

	public static int month(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH);
	}

	public static int year(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}

	public static Date endOfFinancialYear(Date date) {
		if (date == null) {
			throw new IllegalArgumentException("Invalid date to find end of financial year: Null");
		}
		Calendar calendar = new GregorianCalendar();
		calendar.clear();
		calendar.setTime(date);

		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);

		calendar.set(Calendar.YEAR, (month <= (MONTH_OF_FINANCIAL_YEAR_END - 1)) ? year : year + 1);
		calendar.set(Calendar.MONTH, (MONTH_OF_FINANCIAL_YEAR_END - 1));
		calendar.set(Calendar.DATE, DAY_OF_MONTH_OF_FINANCIAL_YEAR_END);

		return DateUtils.endOfDay(calendar.getTime());
	}

	public static String getReportFormattedUSFinancialQuarter(Date date) {
		StringBuffer buffer = new StringBuffer();
		switch (getStrictUSFYQuarter(date)) {
		case 1:
			buffer.append("1st");
			break;

		case 2:
			buffer.append("2nd");
			break;

		case 3:
			buffer.append("3rd");
			break;

		case 4:
			buffer.append("4th");
			break;

		default:
		}
		buffer.append(" ");
		buffer.append("Q ");
		buffer.append(getFinancialYear(date));
		return buffer.toString();
	}

	/**
	 * Returns the financial year quarter for a date. Note: This is Strict mode,
	 * returns quarter end date for a given date.
	 */
	public static Date getStrictUSFYQuarterEndDate(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.set(Calendar.YEAR, getFinancialYear(date));
		if (getStrictUSFYQuarter(date) == 1) {
			calendar.set(Calendar.MONTH, Calendar.FEBRUARY);
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		} else if (getStrictUSFYQuarter(date) == 2) {
			calendar.set(Calendar.MONTH, Calendar.MAY);
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		} else if (getStrictUSFYQuarter(date) == 3) {
			calendar.set(Calendar.MONTH, Calendar.AUGUST);
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		} else if (getStrictUSFYQuarter(date) == 4) {
			calendar.set(Calendar.MONTH, Calendar.NOVEMBER);
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		}
		return zeroOutTime(calendar);
	}

	public static Date getHalfYearlyStartDate(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);

		if (calendar.get(Calendar.MONTH) <= 5) {
			calendar.set(Calendar.MONTH, Calendar.JANUARY);
			calendar.set(Calendar.DAY_OF_MONTH, 1);
		} else {
			calendar.set(Calendar.MONTH, Calendar.JULY);
			calendar.set(Calendar.DAY_OF_MONTH, 1);
		}
		return calendar.getTime();
	}

	public static Date getHalfYearlyEndDate(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);

		if (calendar.get(Calendar.MONTH) <= 5) {
			calendar.set(Calendar.MONTH, Calendar.JUNE);
			calendar.set(Calendar.DAY_OF_MONTH, 30);
		} else {
			calendar.set(Calendar.MONTH, Calendar.DECEMBER);
			calendar.set(Calendar.DAY_OF_MONTH, 31);
		}
		return calendar.getTime();
	}
}
