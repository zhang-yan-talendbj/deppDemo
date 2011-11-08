<<<<<<< HEAD


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import junit.framework.TestCase;
import junit.textui.TestRunner;

/**
 * @author srini
 * 
 *         Created: 2/07/2004
 * 
 */
public class DateUtilsTest extends TestCase {
    public DateUtilsTest(String testName) {
        super(testName);
    }

    public static void main(String[] args) {
        // TestRunner.run(DateUtilsTest.class);
        TestRunner.run(new DateUtilsTest("testMonthsBetween28Feb2000And1Oct2000"));
    }

    public void testDaysBetweenTwoSameDays() {
        Date from = new Date();
        assertEquals(0, DateUtils.calculateDaysBetween(from, new Date()));
    }

    public void testDaysBetweenDaysWithDifferentTimestamps() throws Exception {
        Date from = new Date();
        Thread.sleep(500);
        assertEquals(0, DateUtils.calculateDaysBetween(from, new Date()));
    }

    public void testDaysBetweenDaysWithOneDayDifference() throws Exception {
        Date from = DateUtils.createStartOfDay(1900, 1, 1);
        Date to = DateUtils.createStartOfDay(1900, 1, 2);
        assertEquals(1, DateUtils.calculateDaysBetween(from, to));
    }

    public void testDaysBetweenDaysWithTwoDaysDifference() throws Exception {
        Date from = DateUtils.createStartOfDay(1900, 1, 1);
        Date to = DateUtils.createStartOfDay(1900, 1, 3);
        assertEquals(2, DateUtils.calculateDaysBetween(from, to));
    }

    public void testDaysBetweenDaysWithOneMonthDifference() throws Exception {
        Date from = DateUtils.createStartOfDay(1900, 1, 1);
        Date to = DateUtils.createStartOfDay(1900, 2, 1);
        assertEquals(31, DateUtils.calculateDaysBetween(from, to));
    }

    public void test28DaysInNonLeapFeburary() throws Exception {
        Date from = DateUtils.createStartOfDay(1901, 2, 1);
        Date to = DateUtils.createStartOfDay(1901, 3, 1);
        assertEquals(28, DateUtils.calculateDaysBetween(from, to));
    }

    public void test29DaysInLeapFeburary() throws Exception {
        Date from = DateUtils.createStartOfDay(2004, 2, 1);
        Date to = DateUtils.createStartOfDay(2004, 3, 1);
        assertEquals(29, DateUtils.calculateDaysBetween(from, to));
    }

    private void testBetween(Date yesterday, Date today, Date tomorrow) {
        assertTrue("test dates invalid", yesterday.before(today));
        assertTrue("test dates invalid", today.before(tomorrow));

        // test between
        assertTrue("left < target < right", DateUtils.between(today, yesterday, tomorrow));
        assertTrue("left = target = right", DateUtils.between(today, today, today));
        assertTrue("left = target < right", DateUtils.between(today, today, tomorrow));
        assertTrue("left < target = right", DateUtils.between(today, yesterday, today));
        assertTrue("left < target, right = null", DateUtils.between(today, yesterday, null));
        assertTrue("left = null, target < right", DateUtils.between(today, null, tomorrow));
        assertTrue("left = null, right = null, target != null", DateUtils.between(today, null, null));

        // test not between
        assertTrue("left < right < target", !DateUtils.between(tomorrow, yesterday, today));
        assertTrue("target < left < right", !DateUtils.between(yesterday, today, tomorrow));
        assertTrue("left, right < target", !DateUtils.between(today, yesterday, yesterday));
        assertTrue("left, right > target", !DateUtils.between(today, tomorrow, tomorrow));
        assertTrue("left = null, right > target", !DateUtils.between(tomorrow, null, today));
        assertTrue("target < left, right = null", !DateUtils.between(yesterday, today, null));
    }

    public void testBetween() {
        Calendar calendar = Calendar.getInstance();
        Date today = new Date();
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        Date yesterday = calendar.getTime();
        calendar.add(Calendar.DAY_OF_YEAR, 2);
        Date tomorrow = calendar.getTime();
        this.testBetween(yesterday, today, tomorrow);

        // make sure we don't actually need different days
        Date now = new Date();
        Date justBefore = new Date(now.getTime() - 1);
        Date justAfter = new Date(now.getTime() + 1);
        this.testBetween(justBefore, now, justAfter);
    }

    /**
     * Test addRawOffsetToDate. Ignore issues dealing with maintaining month end
     * etc.
     */
    public void testAddRawOffsetToDate() throws Exception {
        int month = Calendar.MONTH;
        Date tempDate;

        // basic stuff
        tempDate = DateUtils.addRawOffsetToDate(mar2801, month, 1);
        assertEquals("mar2801 + 1 month", april2801, tempDate);
        tempDate = DateUtils.addRawOffsetToDate(jan2801, month, 2);
        assertEquals("jan2801 + 2 month", mar2801, tempDate);
        tempDate = DateUtils.addRawOffsetToDate(jan3101, month, 12);
        assertEquals("jan3101 + 12 month", jan3102, tempDate);
        tempDate = DateUtils.addRawOffsetToDate(jan3101, month, 3);
        assertEquals("jan3101 + 3 month", april3001, tempDate);
        tempDate = DateUtils.addRawOffsetToDate(jan3101, month, 4);
        assertEquals("jan3101 + 4 month", may3101, tempDate);
        tempDate = DateUtils.addRawOffsetToDate(mar3101, month, 2);
        assertEquals("mar3101 + 2 month", may3101, tempDate);
        tempDate = DateUtils.addRawOffsetToDate(jan3101, month, 2);
        assertEquals("jan3101 + 2 month", mar3101, tempDate);
        tempDate = DateUtils.addRawOffsetToDate(mar3101, month, -2);
        assertEquals("mar3101 - 2 month", jan3101, tempDate);
        tempDate = DateUtils.createStartOfDay(2001, 1, 32);
        assertEquals("jan3201 -> feb0101", feb0101, tempDate);

        // test adding from jan to feb
        tempDate = DateUtils.addRawOffsetToDate(jan2801, month, 1);
        assertEquals("Jan2801 + 1 month", feb2801, tempDate);
        tempDate = DateUtils.addRawOffsetToDate(jan2901, month, 1);
        assertEquals("Jan2901 + 1 month", feb2801, tempDate);
        tempDate = DateUtils.addRawOffsetToDate(jan3001, month, 1);
        assertEquals("Jan3001 + 1 month", feb2801, tempDate);
        tempDate = DateUtils.addRawOffsetToDate(jan3101, month, 1);
        assertEquals("Jan3101 + 1 month", feb2801, tempDate);

        // test going from march back to feb
        tempDate = DateUtils.addRawOffsetToDate(mar2801, month, -1);
        assertEquals("mar2801 - 1 month", feb2801, tempDate);
        tempDate = DateUtils.addRawOffsetToDate(mar2901, month, -1);
        assertEquals("mar2901 - 1 month", feb2801, tempDate);
        tempDate = DateUtils.addRawOffsetToDate(mar3001, month, -1);
        assertEquals("mar3001 - 1 month", feb2801, tempDate);
        tempDate = DateUtils.addRawOffsetToDate(mar3101, month, -1);
        assertEquals("mar3101 - 1 month", feb2801, tempDate);

        // test going from feb to mar/jan
        // end of month adjustments NOT done automatically - expected.
        tempDate = DateUtils.addRawOffsetToDate(feb2801, month, 1);
        assertEquals("feb2801 + 1 month", mar2801, tempDate);
        tempDate = DateUtils.addRawOffsetToDate(feb2801, month, -1);
        assertEquals("feb2801 - 1 month", jan2801, tempDate);

        // some basic leap year "month" stuff
        tempDate = DateUtils.addRawOffsetToDate(feb2904, month, 1);
        assertEquals("feb2904 + 1 month", mar2904, tempDate);
        tempDate = DateUtils.addRawOffsetToDate(feb2904, month, -1);
        assertEquals("feb2904 - 1 month", jan2904, tempDate);
        tempDate = DateUtils.addRawOffsetToDate(jan2804, month, 1);
        assertEquals("Jan2804 + 1 month", feb2804, tempDate);
        tempDate = DateUtils.addRawOffsetToDate(jan2904, month, 1);
        assertEquals("Jan2904 + 1 month", feb2904, tempDate);
        tempDate = DateUtils.addRawOffsetToDate(jan3004, month, 1);
        assertEquals("Jan3004 + 1 month", feb2904, tempDate);
        tempDate = DateUtils.addRawOffsetToDate(jan3104, month, 1);
        assertEquals("Jan3104 + 1 month", feb2904, tempDate);
        tempDate = DateUtils.addRawOffsetToDate(mar2804, month, -1);
        assertEquals("mar2804 - 1 month", feb2804, tempDate);
        tempDate = DateUtils.addRawOffsetToDate(mar2904, month, -1);
        assertEquals("mar2904 - 1 month", feb2904, tempDate);
        tempDate = DateUtils.addRawOffsetToDate(mar3004, month, -1);
        assertEquals("mar3004 - 1 month", feb2904, tempDate);
        tempDate = DateUtils.addRawOffsetToDate(mar3104, month, -1);
        assertEquals("mar3104 - 1 month", feb2904, tempDate);

        // leap year "year" stuff
        tempDate = DateUtils.addRawOffsetToDate(feb2904, Calendar.YEAR, 3);
        assertEquals("feb2904 + 3 year", feb2807, tempDate);
        tempDate = DateUtils.addRawOffsetToDate(feb2904, Calendar.YEAR, 4);
        assertEquals("feb2904 + 4 year", feb2908, tempDate);
        tempDate = DateUtils.addRawOffsetToDate(feb2908, Calendar.YEAR, -1);
        assertEquals("feb2908 - 1 year", feb2807, tempDate);
    }

    /**
     * Method testSimpleDateParsing.
     * 
     * @throws Exception
     */

    private Date feb2801, mar2801, mar2901, mar3001, mar3101, jan2801, jan2901, jan3001, jan3101;
    private Date april2801, april3001, may3101;
    private Date feb2804, feb2904, mar2804, mar2904, mar3004, mar3104, jan2804, jan2904, jan3004, jan3104;
    private Date feb2807, feb2908, feb0101;
    private Date jan3102;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        feb0101 = DateUtils.createStartOfDay(2001, 2, 1);
        feb2801 = DateUtils.createStartOfDay(2001, 2, 28);
        mar2801 = DateUtils.createStartOfDay(2001, 3, 28);
        mar2901 = DateUtils.createStartOfDay(2001, 3, 29);
        mar3001 = DateUtils.createStartOfDay(2001, 3, 30);
        mar3101 = DateUtils.createStartOfDay(2001, 3, 31);
        jan2801 = DateUtils.createStartOfDay(2001, 1, 28);
        jan2901 = DateUtils.createStartOfDay(2001, 1, 29);
        jan3001 = DateUtils.createStartOfDay(2001, 1, 30);
        jan3101 = DateUtils.createStartOfDay(2001, 1, 31);
        april2801 = DateUtils.createStartOfDay(2001, 4, 28);
        april3001 = DateUtils.createStartOfDay(2001, 4, 30);
        may3101 = DateUtils.createStartOfDay(2001, 5, 31);
        feb2804 = DateUtils.createStartOfDay(2004, 2, 28);
        feb2904 = DateUtils.createStartOfDay(2004, 2, 29);
        mar2804 = DateUtils.createStartOfDay(2004, 3, 28);
        mar2904 = DateUtils.createStartOfDay(2004, 3, 29);
        mar3004 = DateUtils.createStartOfDay(2004, 3, 30);
        mar3104 = DateUtils.createStartOfDay(2004, 3, 31);
        jan2804 = DateUtils.createStartOfDay(2004, 1, 28);
        jan2904 = DateUtils.createStartOfDay(2004, 1, 29);
        jan3004 = DateUtils.createStartOfDay(2004, 1, 30);
        jan3104 = DateUtils.createStartOfDay(2004, 1, 31);
        feb2807 = DateUtils.createStartOfDay(2007, 2, 28);
        feb2908 = DateUtils.createStartOfDay(2008, 2, 29);
        jan3102 = DateUtils.createStartOfDay(2002, 1, 31);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testDatesBetween22ndOct2004And11May1998() {
        Date firstDate = DateUtils.createStartOfDay(2004, 10, 22);
        Date secondDate = DateUtils.createStartOfDay(1998, 5, 11);
        // 234 + 365 + 366 + 365 + 365 + 365 + 295 = 2356
        assertEquals(2356, DateUtils.calculateDaysBetween(secondDate, firstDate));
    }

    public void testDatesBetweenTwoSameDates() {
        Date firstDate = DateUtils.createStartOfDay(2004, 10, 22);
        Date secondDate = (Date) firstDate.clone();
        assertEquals(0, DateUtils.calculateDaysBetween(secondDate, firstDate));
    }

    public void testMonthsBetweenSameDays() {
        Date date1 = DateUtils.createStartOfDay(2000, 1, 1);
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date1);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(0, monthsBetweenDays[0]);
        assertEquals(0, monthsBetweenDays[1]);
    }

    public void testMonthsBetweenOneExactMonth() {
        Date date1 = DateUtils.createStartOfDay(2000, 1, 1);
        Date date2 = DateUtils.createStartOfDay(2000, 2, 1);
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(1, monthsBetweenDays[0]);
        assertEquals(0, monthsBetweenDays[1]);
    }

    public void testMonthsBetweenOneMonthAndOneDay() {
        Date date1 = DateUtils.createStartOfDay(2000, 1, 1);
        Date date2 = DateUtils.createStartOfDay(2000, 2, 2);
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(1, monthsBetweenDays[0]);
        assertEquals(1, monthsBetweenDays[1]);
    }

    public void testMonthsBetweenOneMonthAnd29DaysInLeapYear() {
        Date date1 = DateUtils.createStartOfDay(2000, 1, 1);
        Date date2 = DateUtils.addRawOffsetToDate(DateUtils.addRawOffsetToDate(date1, Calendar.MONTH, 1), Calendar.DATE, 29);
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(2, monthsBetweenDays[0]);
        assertEquals(0, monthsBetweenDays[1]);
    }

    public void testMonthsBetweenOneMonthAnd29DaysInNonLeapYear() {
        Date date1 = DateUtils.createStartOfDay(2001, 1, 1);
        Date date2 = DateUtils.addRawOffsetToDate(DateUtils.addRawOffsetToDate(date1, Calendar.MONTH, 1), Calendar.DATE, 29);
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(2, monthsBetweenDays[0]);
        assertEquals(1, monthsBetweenDays[1]);
    }

    public void testMonthsBetweenDaysLessThanOneMonthApart() {
        Date date1 = DateUtils.createStartOfDay(2001, 3, 5);
        Date date2 = DateUtils.createStartOfDay(2001, 4, 4);
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(0, monthsBetweenDays[0]);
        assertEquals(30, monthsBetweenDays[1]);
    }

//    public void testMonthsBetweenConsecutiveDays() {
//        Date date1 = DateUtils.createStartOfDay();
//        Date date2 = DateUtils.createStartOfNextDay();
//        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
//        assertEquals(2, monthsBetweenDays.length);
//        assertEquals(0, monthsBetweenDays[0]);
//        assertEquals(1, monthsBetweenDays[1]);
//    }
//
//    public void testMonthsBetweenConsecutiveDaysInterchanged() {
//        Date date1 = DateUtils.createStartOfDay();
//        Date date2 = DateUtils.createStartOfNextDay();
//        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
//        assertEquals(2, monthsBetweenDays.length);
//        assertEquals(0, monthsBetweenDays[0]);
//        assertEquals(1, monthsBetweenDays[1]);
//    }

    public void testMonthsBetweenConsecutiveDaysOnMonthBorder() {
        Date date1 = DateUtils.createStartOfDay(2001, 1, 31);
        Date date2 = DateUtils.createStartOfNextDay(date1);
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(0, monthsBetweenDays[0]);
        assertEquals(1, monthsBetweenDays[1]);
    }

    public void testMonthsBetweenConsecutiveMonthsInLeapYearInterchanged() {
        Date date1 = DateUtils.createStartOfDay(2000, 2, 25);
        Date date2 = DateUtils.createStartOfDay(2000, 3, 25);
        try {
            DateUtils.monthsBetween(date2, date1);
            fail("IllegalArgumentException expected");
        }
        catch (IllegalArgumentException e) {
        }
    }

    public void testMonthsBetween23102004And24112004() {
        Date date1 = DateUtils.createStartOfDay(2004, 10, 23);
        Date date2 = DateUtils.createStartOfDay(2004, 11, 24);
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(1, monthsBetweenDays[0]);
        assertEquals(1, monthsBetweenDays[1]);
    }

    public void testMonthsBetween23102004And24122005() {
        Date date1 = DateUtils.createStartOfDay(2004, 10, 23);
        Date date2 = DateUtils.createStartOfDay(2005, 12, 24);
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(14, monthsBetweenDays[0]);
        assertEquals(1, monthsBetweenDays[1]);
    }

    public void testMonthsBetweenDaysOnYearBorder() {
        Date date1 = DateUtils.createStartOfDay(2004, 11, 30);
        Date date2 = DateUtils.createStartOfDay(2005, 1, 1);
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(1, monthsBetweenDays[0]);
        assertEquals(2, monthsBetweenDays[1]);
    }

    public void testMonthsBetweenDaysOnYearBorder2() {
        Date date1 = DateUtils.createStartOfDay(2004, 12, 30);
        Date date2 = DateUtils.createStartOfDay(2005, 1, 1);
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(0, monthsBetweenDays[0]);
        assertEquals(2, monthsBetweenDays[1]);
    }

    public void testMonthsBetween1Feb2004And29Feb2004() {
        Date date1 = DateUtils.createStartOfDay(2004, 2, 1);
        Date date2 = DateUtils.createStartOfDay(2004, 2, 29);
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(0, monthsBetweenDays[0]);
        assertEquals(28, monthsBetweenDays[1]);
    }

    public void testMonthsBetween1Feb2004And30Feb2004() {
        Date date1 = DateUtils.createStartOfDay(2004, 2, 1);
        Date date2 = DateUtils.createStartOfDay(2004, 2, 30);
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(1, monthsBetweenDays[0]);
        assertEquals(0, monthsBetweenDays[1]);
    }

    public void testMonthsBetween1Feb2004And1Mar2004() {
        Date date1 = DateUtils.createStartOfDay(2004, 2, 1);
        Date date2 = DateUtils.createStartOfDay(2004, 3, 1);
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(1, monthsBetweenDays[0]);
        assertEquals(0, monthsBetweenDays[1]);
    }

    public void testMonthsBetween1Mar2004And31Mar2004() {
        Date date1 = DateUtils.createStartOfDay(2004, 3, 1);
        Date date2 = DateUtils.createStartOfDay(2004, 3, 31);
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(0, monthsBetweenDays[0]);
        assertEquals(30, monthsBetweenDays[1]);
    }

    public void testMonthsBetween1Mar2004And1Apr2004() {
        Date date1 = DateUtils.createStartOfDay(2004, 3, 1);
        Date date2 = DateUtils.createStartOfDay(2004, 4, 1);
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(1, monthsBetweenDays[0]);
        assertEquals(0, monthsBetweenDays[1]);
    }

    public void testMonthsBetween1Feb2004And2Mar2004() {
        Date date1 = DateUtils.createStartOfDay(2004, 2, 1);
        Date date2 = DateUtils.createStartOfDay(2004, 3, 2);
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(1, monthsBetweenDays[0]);
        assertEquals(1, monthsBetweenDays[1]);
    }

    public void testMonthsBetween1Feb2004And3Mar2004() {
        Date date1 = DateUtils.createStartOfDay(2004, 2, 1);
        Date date2 = DateUtils.createStartOfDay(2004, 3, 3);
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(1, monthsBetweenDays[0]);
        assertEquals(2, monthsBetweenDays[1]);
    }
/*
    public void testMonthsBetweenTodayAndMonthAndOneDayFromToday() {
        Date date1 = DateUtils.createStartOfDay();
        Date date2 = DateUtils.createStartOfNextDay(DateUtils.addRawOffsetToDate(date1, Calendar.MONTH, 1));
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(1, monthsBetweenDays[0]);
        assertEquals(1, monthsBetweenDays[1]);
    }

    
    public void testMonthsBetweenSept5AndOctober6MonthAndOneDay() {
        Date date1 = DateUtils.createStartOfDay(2011, 9, 2);
        Date date2 = DateUtils.createStartOfDay(2011, 10, 6);
        
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(1, monthsBetweenDays[0]);
        assertEquals(1, monthsBetweenDays[1]);
    }

  */  
    public void testMonthsBetween11Jun2004And11Dec2004() {
        Date date1 = DateUtils.createStartOfDay(2004, 6, 11);
        Date date2 = DateUtils.createStartOfDay(2004, 12, 11);
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(6, monthsBetweenDays[0]);
        assertEquals(0, monthsBetweenDays[1]);
    }

    public void testMonthsBetween11Jun2004And11Jan2005() {
        Date date1 = DateUtils.createStartOfDay(2004, 6, 11);
        Date date2 = DateUtils.createStartOfDay(2005, 1, 11);
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(7, monthsBetweenDays[0]);
        assertEquals(0, monthsBetweenDays[1]);
    }

    public void testMonthsBetween07Oct2004And06Jan2005() {
        Date date1 = DateUtils.createStartOfDay(2004, 10, 07);
        Date date2 = DateUtils.createStartOfDay(2005, 1, 07);
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(3, monthsBetweenDays[0]);
        assertEquals(0, monthsBetweenDays[1]);
    }

    public void testMonthsBetween29Feb2004And01Mar2004() {
        Date date1 = DateUtils.createStartOfDay(2004, 2, 29);
        Date date2 = DateUtils.createStartOfDay(2004, 3, 1);
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(0, monthsBetweenDays[0]);
        assertEquals(1, monthsBetweenDays[1]);
    }

    public void testMonthsBetween29Feb2004And29Mar2004() {
        Date date1 = DateUtils.createStartOfDay(2004, 2, 29);
        Date date2 = DateUtils.createStartOfDay(2004, 3, 29);
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(1, monthsBetweenDays[0]);
        assertEquals(0, monthsBetweenDays[1]);
    }

    public void testMonthsBetween29Feb2004And01Mar2005() {
        Date date1 = DateUtils.createStartOfDay(2004, 2, 29);
        Date date2 = DateUtils.createStartOfDay(2005, 3, 1);
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(12, monthsBetweenDays[0]);
        assertEquals(1, monthsBetweenDays[1]);
    }

    public void testMonthsBetween28Feb2004And29Mar2004() {
        Date date1 = DateUtils.createStartOfDay(2004, 2, 28);
        Date date2 = DateUtils.createStartOfDay(2004, 3, 29);
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(1, monthsBetweenDays[0]);
        assertEquals(1, monthsBetweenDays[1]);
    }

    public void testMonthsBetween1Feb2004And1Feb2005() {
        Date date1 = DateUtils.createStartOfDay(2004, 2, 1);
        Date date2 = DateUtils.createStartOfDay(2005, 2, 1);
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(12, monthsBetweenDays[0]);
        assertEquals(0, monthsBetweenDays[1]);
    }

    public void testMonthsBetween1Jan2004And1Feb2004() {
        Date date1 = DateUtils.createStartOfDay(2004, 1, 1);
        Date date2 = DateUtils.createStartOfDay(2004, 2, 1);
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(1, monthsBetweenDays[0]);
        assertEquals(0, monthsBetweenDays[1]);
    }

    public void testMonthsBetween1Jan2004And1Feb2005() {
        Date date1 = DateUtils.createStartOfDay(2004, 1, 1);
        Date date2 = DateUtils.createStartOfDay(2005, 2, 1);
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(13, monthsBetweenDays[0]);
        assertEquals(0, monthsBetweenDays[1]);
    }

    public void testMonthsBetween1Jan2000And1Mar2005() {
        Date date1 = DateUtils.createStartOfDay(2000, 1, 1);
        Date date2 = DateUtils.createStartOfDay(2005, 3, 1);
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(62, monthsBetweenDays[0]);
        assertEquals(0, monthsBetweenDays[1]);
    }

    public void testMonthsBetween1Feb2000And28Feb2000() {
        Date date1 = DateUtils.createStartOfDay(2000, 2, 1);
        Date date2 = DateUtils.createStartOfDay(2000, 2, 29);
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(0, monthsBetweenDays[0]);
        assertEquals(28, monthsBetweenDays[1]);
    }

    public void testMonthsBetween1Feb2000And29Feb2000() {
        Date date1 = DateUtils.createStartOfDay(2000, 2, 1);
        Date date2 = DateUtils.createStartOfDay(2000, 3, 1);
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(1, monthsBetweenDays[0]);
        assertEquals(0, monthsBetweenDays[1]);
    }

    public void testMonthsBetween10Sep1979And1Dec2004() {
        Date date1 = DateUtils.createStartOfDay(1979, 9, 10);
        Date date2 = DateUtils.createStartOfDay(2004, 12, 1);
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(302, monthsBetweenDays[0]);
        assertEquals(21, monthsBetweenDays[1]);
    }

    public void testMonthsBetween1Jan2008And29Feb2008() {
        Date date1 = DateUtils.createStartOfDay(2008, 1, 1);
        Date date2 = DateUtils.createStartOfDay(2008, 2, 29);
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(1, monthsBetweenDays[0]);
        assertEquals(28, monthsBetweenDays[1]);
    }

    public void testMonthsBetween1Jan2008And1Mar2008() {
        Date date1 = DateUtils.createStartOfDay(2008, 1, 1);
        Date date2 = DateUtils.createStartOfDay(2008, 3, 1);
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(2, monthsBetweenDays[0]);
        assertEquals(0, monthsBetweenDays[1]);
    }

    public void testMonthsBetween1Feb2100And28Feb2100() {
        Date date1 = DateUtils.createStartOfDay(2100, 2, 1);
        Date date2 = DateUtils.createStartOfDay(2100, 2, 28);
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(0, monthsBetweenDays[0]);
        assertEquals(27, monthsBetweenDays[1]);
    }

    public void testMonthsBetween28Feb2000And1Oct2000() {
        Date date1 = DateUtils.createStartOfDay(2000, 2, 28);
        Date date2 = DateUtils.createStartOfDay(2000, 10, 1);
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(7, monthsBetweenDays[0]);
        assertEquals(3, monthsBetweenDays[1]);
    }

    public void testMonthsBetween25Mar2000And1Oct2000() {
        Date date1 = DateUtils.createStartOfDay(2000, 3, 25);
        Date date2 = DateUtils.createStartOfDay(2000, 10, 1);
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(6, monthsBetweenDays[0]);
        assertEquals(6, monthsBetweenDays[1]);
    }

    public void testMonthsBetweenFebAndMarSameYearDay1MoreThanD2() {
        Date date1 = DateUtils.createStartOfDay(2000, 2, 29);
        Date date2 = DateUtils.createStartOfDay(2000, 3, 1);
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(0, monthsBetweenDays[0]);
        assertEquals(1, monthsBetweenDays[1]);
    }

    public void testMonthsBetweenFebAndMarSameYearDay1LessThanD2() {
        Date date1 = DateUtils.createStartOfDay(2000, 2, 1);
        Date date2 = DateUtils.createStartOfDay(2000, 3, 25);
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(1, monthsBetweenDays[0]);
        assertEquals(24, monthsBetweenDays[1]);
    }

    public void testMonthsBetweenFebAndMarSameYearDay1LessThanD2Interchanged() {
        Date date1 = DateUtils.createStartOfDay(2000, 2, 1);
        Date date2 = DateUtils.createStartOfDay(2000, 3, 25);
        try {
            DateUtils.monthsBetween(date2, date1);
            fail("IllegalArgumentException expected");
        }
        catch (IllegalArgumentException e) {
        }
    }

    public void testMonthsBetweenConsecutiveMonthsInLeapYear() {
        Date date1 = DateUtils.createStartOfDay(2000, 2, 25);
        Date date2 = DateUtils.createStartOfDay(2000, 3, 25);
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(1, monthsBetweenDays[0]);
        assertEquals(0, monthsBetweenDays[1]);
    }

    public void testWorkDays() throws Exception {
        // less than a week
        assertWorkDays(0, "24/03/2005", "24/03/2005");
        assertWorkDays(1, "24/03/2005", "25/03/2005");
        assertWorkDays(1, "25/03/2005", "24/03/2005");
        assertWorkDays(4, "21/03/2005", "25/03/2005");
        assertWorkDays(1, "25/03/2005", "28/03/2005");

        // more than a week
        assertWorkDays(20, "1/03/2005", "29/03/2005");
        assertWorkDays(19, "1/03/2005", "28/03/2005");
        assertWorkDays(18, "1/03/2005", "25/03/2005");
        assertWorkDays(5, "1/03/2005", "8/03/2005");
        assertWorkDays(4, "1/03/2005", "7/03/2005");

        // days on weekends
        assertWorkDays(3, "1/03/2005", "4/03/2005");
        assertWorkDays(3, "1/03/2005", "6/03/2005");
        assertWorkDays(3, "1/03/2005", "5/03/2005");
        assertWorkDays(7, "4/03/2005", "15/03/2005");
        assertWorkDays(6, "5/03/2005", "15/03/2005");
        assertWorkDays(6, "6/03/2005", "15/03/2005");
        assertWorkDays(6, "7/03/2005", "15/03/2005");

        assertWorkDays(0, "31/12/2004", "1/1/2005");
        assertWorkDays(260, "31/12/2004", "30/12/2005");

        // Times within a day
        assertWorkDaysTime(0, "24/03/2005 02:30", "24/03/2005 21:30");
        assertWorkDaysTime(0, "24/03/2005 00:00", "24/03/2005 23:59");
        assertWorkDaysTime(1, "24/03/2005 23:59", "25/03/2005 00:00");
        assertWorkDaysTime(1, "24/03/2005 02:30", "25/03/2005 21:30");
    }

    public void assertWorkDays(int days, String from, String to) throws Exception {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        assertEquals(days, DateUtils.calculateWorkingDaysBetween(df.parse(from), df.parse(to)));
    }

    public void assertWorkDaysTime(int days, String from, String to) throws Exception {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        assertEquals(days, DateUtils.calculateWorkingDaysBetween(df.parse(from), df.parse(to)));
    }

    public void assertDatesEqual(String expected, String got) throws Exception {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss:SSS");
        assertEquals(df.parse(expected), df.parse(got));
    }

    public void assertEndOfDayIs(String day, String endOfDay) throws Exception {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");
        assertEquals(df.parse(endOfDay), DateUtils.endOfDay(df.parse(day)));
    }

    public void assertEndOfDayWithPivotHourIs(long pivotHour, String day, String endOfDay) throws Exception {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");
        assertEquals(df.parse(endOfDay), DateUtils.getEndOfDayUsingPivot(df.parse(day), pivotHour));
    }

    public void assertEndOfPreviousDay(String day, String endOfDay) throws Exception {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");
        assertEquals(df.parse(endOfDay), DateUtils.getEndOfDayBefore(df.parse(day)));
    }

    public void testEndOfDay() throws Exception {
        assertEndOfDayIs("24/03/2005 1:30:00.000", "24/03/2005 23:59:59.999");
        assertEndOfDayIs("24/03/2005 22:30:00.000", "24/03/2005 23:59:59.999");
        assertEndOfDayWithPivotHourIs(8, "24/03/2005 22:30:00.000", "24/03/2005 23:59:59.999");
        assertEndOfDayWithPivotHourIs(8, "24/03/2005 8:30:00.000", "24/03/2005 23:59:59.999");
        assertEndOfDayWithPivotHourIs(8, "24/03/2005 7:30:00.000", "23/03/2005 23:59:59.999");
        assertEndOfPreviousDay("24/03/2005 7:30:00.000", "23/03/2005 23:59:59.999");
        assertEndOfPreviousDay("24/03/2005 00:00:00.000", "23/03/2005 23:59:59.999");
        assertEndOfPreviousDay("24/03/2005 23:59:59.999", "23/03/2005 23:59:59.999");
    }

    public void testGetEndOfMonth() throws Exception {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");
        Date endOfMonth = DateUtils.createEndOfMonth(df.parse("24/04/2005 1:30:00.000"));
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(endOfMonth);
        assertTrue(calendar.get(Calendar.DATE) == 30);
    }

    public void testGetStartUSAFinancialYear() {
        Date financialYearStart = DateUtils.createStartOfDay(2003, 12, 1);
        Date toDate = DateUtils.createStartOfDay(2003, 12, 4);
        assertEquals("Near beginning of financial year", financialYearStart, DateUtils.startOfUSAFinancialYearBefore(toDate));

        toDate = DateUtils.createStartOfDay(2004, 8, 24);
        assertEquals("Middle of financial year", financialYearStart, DateUtils.startOfUSAFinancialYearBefore(toDate));

        toDate = DateUtils.createStartOfDay(2004, 11, 30);
        assertEquals("Last day of financial year", financialYearStart, DateUtils.startOfUSAFinancialYearBefore(toDate));

        toDate = DateUtils.createStartOfDay(2003, 12, 1);
        assertEquals("first day of financial", financialYearStart, DateUtils.startOfUSAFinancialYearBefore(toDate));

        toDate = DateUtils.createStartOfDay(2005, 3, 1);
        assertEquals("Next Financial year", DateUtils.createStartOfDay(2004, 12, 1), DateUtils.startOfUSAFinancialYearBefore(toDate));

        try {
            DateUtils.startOfUSAFinancialYearBefore((Date) null);
            fail("An Illegal Argument Exception should have been thrown: null date");
        }
        catch (IllegalArgumentException iae) {
            // This should happen
            assertTrue(true);
        }
    }

    public void testGetStartOfMonth() {
        assertEquals(DateUtils.createStartOfDay(2005, 1, 1), DateUtils.getStartOfMonth(DateUtils.createStartOfDay(2005, 1, 17)));
        assertEquals(DateUtils.createStartOfDay(2004, 7, 1), DateUtils.getStartOfMonth(DateUtils.createStartOfDay(2004, 7, 1)));
        assertEquals(DateUtils.createStartOfDay(2004, 7, 1), DateUtils.getStartOfMonth(DateUtils.createStartOfDay(2004, 7, 8)));
        assertEquals(DateUtils.createStartOfDay(2004, 7, 1), DateUtils.getStartOfMonth(DateUtils.createStartOfDay(2004, 7, 28)));
    }

    public void testGetEndOfMonth2() {
        assertEquals(DateUtils.createStartOfDay(2005, 1, 31), DateUtils.getEndOfMonth(DateUtils.createStartOfDay(2005, 1, 17)));
        assertEquals(DateUtils.createStartOfDay(2005, 2, 28), DateUtils.getEndOfMonth(DateUtils.createStartOfDay(2005, 2, 15)));
        assertEquals(DateUtils.createStartOfDay(2004, 2, 29), DateUtils.getEndOfMonth(DateUtils.createStartOfDay(2004, 2, 15)));
        assertEquals(DateUtils.createStartOfDay(2004, 7, 31), DateUtils.getEndOfMonth(DateUtils.createStartOfDay(2004, 7, 8)));
        assertEquals(DateUtils.createStartOfDay(2004, 4, 30), DateUtils.getEndOfMonth(DateUtils.createStartOfDay(2004, 4, 28)));
    }

    public void testGetStartOfPreviousMonth() {
        assertEquals(DateUtils.createStartOfDay(2004, 12, 1), DateUtils.getStartOfPreviousMonth(DateUtils.createStartOfDay(2005, 1, 17)));
        assertEquals(DateUtils.createStartOfDay(2004, 6, 1), DateUtils.getStartOfPreviousMonth(DateUtils.createStartOfDay(2004, 7, 1)));
        assertEquals(DateUtils.createStartOfDay(2004, 6, 1), DateUtils.getStartOfPreviousMonth(DateUtils.createStartOfDay(2004, 7, 8)));
        assertEquals(DateUtils.createStartOfDay(2004, 6, 1), DateUtils.getStartOfPreviousMonth(DateUtils.createStartOfDay(2004, 7, 28)));
    }

    public void testGetEndOfPreviousMonth() {
        assertEquals(DateUtils.createStartOfDay(2004, 12, 31), DateUtils.getEndOfPreviousMonth(DateUtils.createStartOfDay(2005, 1, 17)));
        assertEquals(DateUtils.createStartOfDay(2005, 1, 31), DateUtils.getEndOfPreviousMonth(DateUtils.createStartOfDay(2005, 2, 15)));
        assertEquals(DateUtils.createStartOfDay(2004, 2, 29), DateUtils.getEndOfPreviousMonth(DateUtils.createStartOfDay(2004, 3, 15)));
        assertEquals(DateUtils.createStartOfDay(2004, 7, 31), DateUtils.getEndOfPreviousMonth(DateUtils.createStartOfDay(2004, 8, 8)));
        assertEquals(DateUtils.createStartOfDay(2004, 4, 30), DateUtils.getEndOfPreviousMonth(DateUtils.createStartOfDay(2004, 5, 28)));
    }

    public void testGetStartOfReportMonth() {
        assertEquals(DateUtils.createStartOfDay(2004, 12, 1), DateUtils.getReportStartOfMonth(DateUtils.createStartOfDay(2005, 1, 15)));
        assertEquals(DateUtils.createStartOfDay(2004, 6, 1), DateUtils.getReportStartOfMonth(DateUtils.createStartOfDay(2004, 7, 1)));
        assertEquals(DateUtils.createStartOfDay(2004, 7, 1), DateUtils.getReportStartOfMonth(DateUtils.createStartOfDay(2004, 7, 16)));
        assertEquals(DateUtils.createStartOfDay(2004, 7, 1), DateUtils.getReportStartOfMonth(DateUtils.createStartOfDay(2004, 7, 28)));
    }

    public void testGetEndOfReportMonth() {
        assertEquals(DateUtils.createStartOfDay(2004, 12, 31), DateUtils.getReportEndOfMonth(DateUtils.createStartOfDay(2005, 1, 15)));
        assertEquals(DateUtils.createStartOfDay(2005, 1, 31), DateUtils.getReportEndOfMonth(DateUtils.createStartOfDay(2005, 1, 16)));
    }

    public void testUSFinancialQuarter() {
        assertEquals(1, DateUtils.getStrictUSFYQuarter(DateUtils.createStartOfDay(2005, 12, 1)));
        assertEquals(4, DateUtils.getStrictUSFYQuarter(DateUtils.createStartOfDay(2005, 11, 1)));
        assertEquals(4, DateUtils.getStrictUSFYQuarter(DateUtils.createStartOfDay(2005, 11, 30)));
    }

    public void testUSFinancialYear() {
        assertEquals(2006, DateUtils.getFinancialYear(DateUtils.createStartOfDay(2005, 12, 1)));
        assertEquals(2007, DateUtils.getFinancialYear(DateUtils.createStartOfDay(2006, 12, 1)));
    }

    public void testMonth() {
        assertEquals(11, DateUtils.month(DateUtils.createStartOfDay(2005, 12, 1)));
    }

    public void testHalfYearlyStartDate() {
        Date halfYearlyStartdate = DateUtils.zeroOutTime(DateUtils.getHalfYearlyStartDate(DateUtils.createStartOfDay(2007, 01, 01)));
        assertEquals(DateUtils.zeroOutTime(DateUtils.createStartOfDay(2007, 01, 01)), halfYearlyStartdate);

        halfYearlyStartdate = DateUtils.zeroOutTime(DateUtils.getHalfYearlyStartDate(DateUtils.createStartOfDay(2007, 03, 11)));
        assertEquals(DateUtils.zeroOutTime(DateUtils.createStartOfDay(2007, 01, 01)), halfYearlyStartdate);

        halfYearlyStartdate = DateUtils.zeroOutTime(DateUtils.getHalfYearlyStartDate(DateUtils.createStartOfDay(2007, 06, 30)));
        assertEquals(DateUtils.zeroOutTime(DateUtils.createStartOfDay(2007, 01, 01)), halfYearlyStartdate);

        halfYearlyStartdate = DateUtils.zeroOutTime(DateUtils.getHalfYearlyStartDate(DateUtils.createStartOfDay(2007, 07, 1)));
        assertNotSame(DateUtils.zeroOutTime(DateUtils.createStartOfDay(2007, 01, 01)), halfYearlyStartdate);
    }

    public void testHalfYearlyEndDate() {
        Date halfYearlyEnddate = DateUtils.zeroOutTime(DateUtils.getHalfYearlyEndDate(DateUtils.createStartOfDay(2007, 07, 01)));
        assertEquals(DateUtils.zeroOutTime(DateUtils.createStartOfDay(2007, 12, 31)), halfYearlyEnddate);

        halfYearlyEnddate = DateUtils.zeroOutTime(DateUtils.getHalfYearlyEndDate(DateUtils.createStartOfDay(2007, 9, 11)));
        assertEquals(DateUtils.zeroOutTime(DateUtils.createStartOfDay(2007, 12, 31)), halfYearlyEnddate);

        halfYearlyEnddate = DateUtils.zeroOutTime(DateUtils.getHalfYearlyEndDate(DateUtils.createStartOfDay(2007, 12, 31)));
        assertEquals(DateUtils.zeroOutTime(DateUtils.createStartOfDay(2007, 12, 31)), halfYearlyEnddate);

        halfYearlyEnddate = DateUtils.zeroOutTime(DateUtils.getHalfYearlyEndDate(DateUtils.createStartOfDay(2007, 05, 01)));
        assertNotSame(DateUtils.zeroOutTime(DateUtils.createStartOfDay(2007, 07, 01)), halfYearlyEnddate);

        halfYearlyEnddate = DateUtils.zeroOutTime(DateUtils.getHalfYearlyEndDate(DateUtils.createStartOfDay(2007, 01, 31)));
        assertEquals(DateUtils.zeroOutTime(DateUtils.createStartOfDay(2007, 06, 30)), halfYearlyEnddate);

        halfYearlyEnddate = DateUtils.zeroOutTime(DateUtils.getHalfYearlyEndDate(DateUtils.createStartOfDay(2007, 03, 31)));
        assertEquals(DateUtils.zeroOutTime(DateUtils.createStartOfDay(2007, 06, 30)), halfYearlyEnddate);

        halfYearlyEnddate = DateUtils.zeroOutTime(DateUtils.getHalfYearlyEndDate(DateUtils.createStartOfDay(2007, 04, 30)));
        assertEquals(DateUtils.zeroOutTime(DateUtils.createStartOfDay(2007, 06, 30)), halfYearlyEnddate);
    }
}
=======


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import junit.framework.TestCase;
import junit.textui.TestRunner;

/**
 * @author srini
 * 
 *         Created: 2/07/2004
 * 
 */
public class DateUtilsTest extends TestCase {
    public DateUtilsTest(String testName) {
        super(testName);
    }

    public static void main(String[] args) {
        // TestRunner.run(DateUtilsTest.class);
        TestRunner.run(new DateUtilsTest("testMonthsBetween28Feb2000And1Oct2000"));
    }

    public void testDaysBetweenTwoSameDays() {
        Date from = new Date();
        assertEquals(0, DateUtils.calculateDaysBetween(from, new Date()));
    }

    public void testDaysBetweenDaysWithDifferentTimestamps() throws Exception {
        Date from = new Date();
        Thread.sleep(500);
        assertEquals(0, DateUtils.calculateDaysBetween(from, new Date()));
    }

    public void testDaysBetweenDaysWithOneDayDifference() throws Exception {
        Date from = DateUtils.createStartOfDay(1900, 1, 1);
        Date to = DateUtils.createStartOfDay(1900, 1, 2);
        assertEquals(1, DateUtils.calculateDaysBetween(from, to));
    }

    public void testDaysBetweenDaysWithTwoDaysDifference() throws Exception {
        Date from = DateUtils.createStartOfDay(1900, 1, 1);
        Date to = DateUtils.createStartOfDay(1900, 1, 3);
        assertEquals(2, DateUtils.calculateDaysBetween(from, to));
    }

    public void testDaysBetweenDaysWithOneMonthDifference() throws Exception {
        Date from = DateUtils.createStartOfDay(1900, 1, 1);
        Date to = DateUtils.createStartOfDay(1900, 2, 1);
        assertEquals(31, DateUtils.calculateDaysBetween(from, to));
    }

    public void test28DaysInNonLeapFeburary() throws Exception {
        Date from = DateUtils.createStartOfDay(1901, 2, 1);
        Date to = DateUtils.createStartOfDay(1901, 3, 1);
        assertEquals(28, DateUtils.calculateDaysBetween(from, to));
    }

    public void test29DaysInLeapFeburary() throws Exception {
        Date from = DateUtils.createStartOfDay(2004, 2, 1);
        Date to = DateUtils.createStartOfDay(2004, 3, 1);
        assertEquals(29, DateUtils.calculateDaysBetween(from, to));
    }

    private void testBetween(Date yesterday, Date today, Date tomorrow) {
        assertTrue("test dates invalid", yesterday.before(today));
        assertTrue("test dates invalid", today.before(tomorrow));

        // test between
        assertTrue("left < target < right", DateUtils.between(today, yesterday, tomorrow));
        assertTrue("left = target = right", DateUtils.between(today, today, today));
        assertTrue("left = target < right", DateUtils.between(today, today, tomorrow));
        assertTrue("left < target = right", DateUtils.between(today, yesterday, today));
        assertTrue("left < target, right = null", DateUtils.between(today, yesterday, null));
        assertTrue("left = null, target < right", DateUtils.between(today, null, tomorrow));
        assertTrue("left = null, right = null, target != null", DateUtils.between(today, null, null));

        // test not between
        assertTrue("left < right < target", !DateUtils.between(tomorrow, yesterday, today));
        assertTrue("target < left < right", !DateUtils.between(yesterday, today, tomorrow));
        assertTrue("left, right < target", !DateUtils.between(today, yesterday, yesterday));
        assertTrue("left, right > target", !DateUtils.between(today, tomorrow, tomorrow));
        assertTrue("left = null, right > target", !DateUtils.between(tomorrow, null, today));
        assertTrue("target < left, right = null", !DateUtils.between(yesterday, today, null));
    }

    public void testBetween() {
        Calendar calendar = Calendar.getInstance();
        Date today = new Date();
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        Date yesterday = calendar.getTime();
        calendar.add(Calendar.DAY_OF_YEAR, 2);
        Date tomorrow = calendar.getTime();
        this.testBetween(yesterday, today, tomorrow);

        // make sure we don't actually need different days
        Date now = new Date();
        Date justBefore = new Date(now.getTime() - 1);
        Date justAfter = new Date(now.getTime() + 1);
        this.testBetween(justBefore, now, justAfter);
    }

    /**
     * Test addRawOffsetToDate. Ignore issues dealing with maintaining month end
     * etc.
     */
    public void testAddRawOffsetToDate() throws Exception {
        int month = Calendar.MONTH;
        Date tempDate;

        // basic stuff
        tempDate = DateUtils.addRawOffsetToDate(mar2801, month, 1);
        assertEquals("mar2801 + 1 month", april2801, tempDate);
        tempDate = DateUtils.addRawOffsetToDate(jan2801, month, 2);
        assertEquals("jan2801 + 2 month", mar2801, tempDate);
        tempDate = DateUtils.addRawOffsetToDate(jan3101, month, 12);
        assertEquals("jan3101 + 12 month", jan3102, tempDate);
        tempDate = DateUtils.addRawOffsetToDate(jan3101, month, 3);
        assertEquals("jan3101 + 3 month", april3001, tempDate);
        tempDate = DateUtils.addRawOffsetToDate(jan3101, month, 4);
        assertEquals("jan3101 + 4 month", may3101, tempDate);
        tempDate = DateUtils.addRawOffsetToDate(mar3101, month, 2);
        assertEquals("mar3101 + 2 month", may3101, tempDate);
        tempDate = DateUtils.addRawOffsetToDate(jan3101, month, 2);
        assertEquals("jan3101 + 2 month", mar3101, tempDate);
        tempDate = DateUtils.addRawOffsetToDate(mar3101, month, -2);
        assertEquals("mar3101 - 2 month", jan3101, tempDate);
        tempDate = DateUtils.createStartOfDay(2001, 1, 32);
        assertEquals("jan3201 -> feb0101", feb0101, tempDate);

        // test adding from jan to feb
        tempDate = DateUtils.addRawOffsetToDate(jan2801, month, 1);
        assertEquals("Jan2801 + 1 month", feb2801, tempDate);
        tempDate = DateUtils.addRawOffsetToDate(jan2901, month, 1);
        assertEquals("Jan2901 + 1 month", feb2801, tempDate);
        tempDate = DateUtils.addRawOffsetToDate(jan3001, month, 1);
        assertEquals("Jan3001 + 1 month", feb2801, tempDate);
        tempDate = DateUtils.addRawOffsetToDate(jan3101, month, 1);
        assertEquals("Jan3101 + 1 month", feb2801, tempDate);

        // test going from march back to feb
        tempDate = DateUtils.addRawOffsetToDate(mar2801, month, -1);
        assertEquals("mar2801 - 1 month", feb2801, tempDate);
        tempDate = DateUtils.addRawOffsetToDate(mar2901, month, -1);
        assertEquals("mar2901 - 1 month", feb2801, tempDate);
        tempDate = DateUtils.addRawOffsetToDate(mar3001, month, -1);
        assertEquals("mar3001 - 1 month", feb2801, tempDate);
        tempDate = DateUtils.addRawOffsetToDate(mar3101, month, -1);
        assertEquals("mar3101 - 1 month", feb2801, tempDate);

        // test going from feb to mar/jan
        // end of month adjustments NOT done automatically - expected.
        tempDate = DateUtils.addRawOffsetToDate(feb2801, month, 1);
        assertEquals("feb2801 + 1 month", mar2801, tempDate);
        tempDate = DateUtils.addRawOffsetToDate(feb2801, month, -1);
        assertEquals("feb2801 - 1 month", jan2801, tempDate);

        // some basic leap year "month" stuff
        tempDate = DateUtils.addRawOffsetToDate(feb2904, month, 1);
        assertEquals("feb2904 + 1 month", mar2904, tempDate);
        tempDate = DateUtils.addRawOffsetToDate(feb2904, month, -1);
        assertEquals("feb2904 - 1 month", jan2904, tempDate);
        tempDate = DateUtils.addRawOffsetToDate(jan2804, month, 1);
        assertEquals("Jan2804 + 1 month", feb2804, tempDate);
        tempDate = DateUtils.addRawOffsetToDate(jan2904, month, 1);
        assertEquals("Jan2904 + 1 month", feb2904, tempDate);
        tempDate = DateUtils.addRawOffsetToDate(jan3004, month, 1);
        assertEquals("Jan3004 + 1 month", feb2904, tempDate);
        tempDate = DateUtils.addRawOffsetToDate(jan3104, month, 1);
        assertEquals("Jan3104 + 1 month", feb2904, tempDate);
        tempDate = DateUtils.addRawOffsetToDate(mar2804, month, -1);
        assertEquals("mar2804 - 1 month", feb2804, tempDate);
        tempDate = DateUtils.addRawOffsetToDate(mar2904, month, -1);
        assertEquals("mar2904 - 1 month", feb2904, tempDate);
        tempDate = DateUtils.addRawOffsetToDate(mar3004, month, -1);
        assertEquals("mar3004 - 1 month", feb2904, tempDate);
        tempDate = DateUtils.addRawOffsetToDate(mar3104, month, -1);
        assertEquals("mar3104 - 1 month", feb2904, tempDate);

        // leap year "year" stuff
        tempDate = DateUtils.addRawOffsetToDate(feb2904, Calendar.YEAR, 3);
        assertEquals("feb2904 + 3 year", feb2807, tempDate);
        tempDate = DateUtils.addRawOffsetToDate(feb2904, Calendar.YEAR, 4);
        assertEquals("feb2904 + 4 year", feb2908, tempDate);
        tempDate = DateUtils.addRawOffsetToDate(feb2908, Calendar.YEAR, -1);
        assertEquals("feb2908 - 1 year", feb2807, tempDate);
    }

    /**
     * Method testSimpleDateParsing.
     * 
     * @throws Exception
     */

    private Date feb2801, mar2801, mar2901, mar3001, mar3101, jan2801, jan2901, jan3001, jan3101;
    private Date april2801, april3001, may3101;
    private Date feb2804, feb2904, mar2804, mar2904, mar3004, mar3104, jan2804, jan2904, jan3004, jan3104;
    private Date feb2807, feb2908, feb0101;
    private Date jan3102;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        feb0101 = DateUtils.createStartOfDay(2001, 2, 1);
        feb2801 = DateUtils.createStartOfDay(2001, 2, 28);
        mar2801 = DateUtils.createStartOfDay(2001, 3, 28);
        mar2901 = DateUtils.createStartOfDay(2001, 3, 29);
        mar3001 = DateUtils.createStartOfDay(2001, 3, 30);
        mar3101 = DateUtils.createStartOfDay(2001, 3, 31);
        jan2801 = DateUtils.createStartOfDay(2001, 1, 28);
        jan2901 = DateUtils.createStartOfDay(2001, 1, 29);
        jan3001 = DateUtils.createStartOfDay(2001, 1, 30);
        jan3101 = DateUtils.createStartOfDay(2001, 1, 31);
        april2801 = DateUtils.createStartOfDay(2001, 4, 28);
        april3001 = DateUtils.createStartOfDay(2001, 4, 30);
        may3101 = DateUtils.createStartOfDay(2001, 5, 31);
        feb2804 = DateUtils.createStartOfDay(2004, 2, 28);
        feb2904 = DateUtils.createStartOfDay(2004, 2, 29);
        mar2804 = DateUtils.createStartOfDay(2004, 3, 28);
        mar2904 = DateUtils.createStartOfDay(2004, 3, 29);
        mar3004 = DateUtils.createStartOfDay(2004, 3, 30);
        mar3104 = DateUtils.createStartOfDay(2004, 3, 31);
        jan2804 = DateUtils.createStartOfDay(2004, 1, 28);
        jan2904 = DateUtils.createStartOfDay(2004, 1, 29);
        jan3004 = DateUtils.createStartOfDay(2004, 1, 30);
        jan3104 = DateUtils.createStartOfDay(2004, 1, 31);
        feb2807 = DateUtils.createStartOfDay(2007, 2, 28);
        feb2908 = DateUtils.createStartOfDay(2008, 2, 29);
        jan3102 = DateUtils.createStartOfDay(2002, 1, 31);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testDatesBetween22ndOct2004And11May1998() {
        Date firstDate = DateUtils.createStartOfDay(2004, 10, 22);
        Date secondDate = DateUtils.createStartOfDay(1998, 5, 11);
        // 234 + 365 + 366 + 365 + 365 + 365 + 295 = 2356
        assertEquals(2356, DateUtils.calculateDaysBetween(secondDate, firstDate));
    }

    public void testDatesBetweenTwoSameDates() {
        Date firstDate = DateUtils.createStartOfDay(2004, 10, 22);
        Date secondDate = (Date) firstDate.clone();
        assertEquals(0, DateUtils.calculateDaysBetween(secondDate, firstDate));
    }

    public void testMonthsBetweenSameDays() {
        Date date1 = DateUtils.createStartOfDay(2000, 1, 1);
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date1);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(0, monthsBetweenDays[0]);
        assertEquals(0, monthsBetweenDays[1]);
    }

    public void testMonthsBetweenOneExactMonth() {
        Date date1 = DateUtils.createStartOfDay(2000, 1, 1);
        Date date2 = DateUtils.createStartOfDay(2000, 2, 1);
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(1, monthsBetweenDays[0]);
        assertEquals(0, monthsBetweenDays[1]);
    }

    public void testMonthsBetweenOneMonthAndOneDay() {
        Date date1 = DateUtils.createStartOfDay(2000, 1, 1);
        Date date2 = DateUtils.createStartOfDay(2000, 2, 2);
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(1, monthsBetweenDays[0]);
        assertEquals(1, monthsBetweenDays[1]);
    }

    public void testMonthsBetweenOneMonthAnd29DaysInLeapYear() {
        Date date1 = DateUtils.createStartOfDay(2000, 1, 1);
        Date date2 = DateUtils.addRawOffsetToDate(DateUtils.addRawOffsetToDate(date1, Calendar.MONTH, 1), Calendar.DATE, 29);
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(2, monthsBetweenDays[0]);
        assertEquals(0, monthsBetweenDays[1]);
    }

    public void testMonthsBetweenOneMonthAnd29DaysInNonLeapYear() {
        Date date1 = DateUtils.createStartOfDay(2001, 1, 1);
        Date date2 = DateUtils.addRawOffsetToDate(DateUtils.addRawOffsetToDate(date1, Calendar.MONTH, 1), Calendar.DATE, 29);
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(2, monthsBetweenDays[0]);
        assertEquals(1, monthsBetweenDays[1]);
    }

    public void testMonthsBetweenDaysLessThanOneMonthApart() {
        Date date1 = DateUtils.createStartOfDay(2001, 3, 5);
        Date date2 = DateUtils.createStartOfDay(2001, 4, 4);
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(0, monthsBetweenDays[0]);
        assertEquals(30, monthsBetweenDays[1]);
    }

//    public void testMonthsBetweenConsecutiveDays() {
//        Date date1 = DateUtils.createStartOfDay();
//        Date date2 = DateUtils.createStartOfNextDay();
//        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
//        assertEquals(2, monthsBetweenDays.length);
//        assertEquals(0, monthsBetweenDays[0]);
//        assertEquals(1, monthsBetweenDays[1]);
//    }
//
//    public void testMonthsBetweenConsecutiveDaysInterchanged() {
//        Date date1 = DateUtils.createStartOfDay();
//        Date date2 = DateUtils.createStartOfNextDay();
//        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
//        assertEquals(2, monthsBetweenDays.length);
//        assertEquals(0, monthsBetweenDays[0]);
//        assertEquals(1, monthsBetweenDays[1]);
//    }

    public void testMonthsBetweenConsecutiveDaysOnMonthBorder() {
        Date date1 = DateUtils.createStartOfDay(2001, 1, 31);
        Date date2 = DateUtils.createStartOfNextDay(date1);
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(0, monthsBetweenDays[0]);
        assertEquals(1, monthsBetweenDays[1]);
    }

    public void testMonthsBetweenConsecutiveMonthsInLeapYearInterchanged() {
        Date date1 = DateUtils.createStartOfDay(2000, 2, 25);
        Date date2 = DateUtils.createStartOfDay(2000, 3, 25);
        try {
            DateUtils.monthsBetween(date2, date1);
            fail("IllegalArgumentException expected");
        }
        catch (IllegalArgumentException e) {
        }
    }

    public void testMonthsBetween23102004And24112004() {
        Date date1 = DateUtils.createStartOfDay(2004, 10, 23);
        Date date2 = DateUtils.createStartOfDay(2004, 11, 24);
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(1, monthsBetweenDays[0]);
        assertEquals(1, monthsBetweenDays[1]);
    }

    public void testMonthsBetween23102004And24122005() {
        Date date1 = DateUtils.createStartOfDay(2004, 10, 23);
        Date date2 = DateUtils.createStartOfDay(2005, 12, 24);
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(14, monthsBetweenDays[0]);
        assertEquals(1, monthsBetweenDays[1]);
    }

    public void testMonthsBetweenDaysOnYearBorder() {
        Date date1 = DateUtils.createStartOfDay(2004, 11, 30);
        Date date2 = DateUtils.createStartOfDay(2005, 1, 1);
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(1, monthsBetweenDays[0]);
        assertEquals(2, monthsBetweenDays[1]);
    }

    public void testMonthsBetweenDaysOnYearBorder2() {
        Date date1 = DateUtils.createStartOfDay(2004, 12, 30);
        Date date2 = DateUtils.createStartOfDay(2005, 1, 1);
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(0, monthsBetweenDays[0]);
        assertEquals(2, monthsBetweenDays[1]);
    }

    public void testMonthsBetween1Feb2004And29Feb2004() {
        Date date1 = DateUtils.createStartOfDay(2004, 2, 1);
        Date date2 = DateUtils.createStartOfDay(2004, 2, 29);
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(0, monthsBetweenDays[0]);
        assertEquals(28, monthsBetweenDays[1]);
    }

    public void testMonthsBetween1Feb2004And30Feb2004() {
        Date date1 = DateUtils.createStartOfDay(2004, 2, 1);
        Date date2 = DateUtils.createStartOfDay(2004, 2, 30);
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(1, monthsBetweenDays[0]);
        assertEquals(0, monthsBetweenDays[1]);
    }

    public void testMonthsBetween1Feb2004And1Mar2004() {
        Date date1 = DateUtils.createStartOfDay(2004, 2, 1);
        Date date2 = DateUtils.createStartOfDay(2004, 3, 1);
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(1, monthsBetweenDays[0]);
        assertEquals(0, monthsBetweenDays[1]);
    }

    public void testMonthsBetween1Mar2004And31Mar2004() {
        Date date1 = DateUtils.createStartOfDay(2004, 3, 1);
        Date date2 = DateUtils.createStartOfDay(2004, 3, 31);
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(0, monthsBetweenDays[0]);
        assertEquals(30, monthsBetweenDays[1]);
    }

    public void testMonthsBetween1Mar2004And1Apr2004() {
        Date date1 = DateUtils.createStartOfDay(2004, 3, 1);
        Date date2 = DateUtils.createStartOfDay(2004, 4, 1);
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(1, monthsBetweenDays[0]);
        assertEquals(0, monthsBetweenDays[1]);
    }

    public void testMonthsBetween1Feb2004And2Mar2004() {
        Date date1 = DateUtils.createStartOfDay(2004, 2, 1);
        Date date2 = DateUtils.createStartOfDay(2004, 3, 2);
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(1, monthsBetweenDays[0]);
        assertEquals(1, monthsBetweenDays[1]);
    }

    public void testMonthsBetween1Feb2004And3Mar2004() {
        Date date1 = DateUtils.createStartOfDay(2004, 2, 1);
        Date date2 = DateUtils.createStartOfDay(2004, 3, 3);
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(1, monthsBetweenDays[0]);
        assertEquals(2, monthsBetweenDays[1]);
    }
/*
    public void testMonthsBetweenTodayAndMonthAndOneDayFromToday() {
        Date date1 = DateUtils.createStartOfDay();
        Date date2 = DateUtils.createStartOfNextDay(DateUtils.addRawOffsetToDate(date1, Calendar.MONTH, 1));
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(1, monthsBetweenDays[0]);
        assertEquals(1, monthsBetweenDays[1]);
    }

    
    public void testMonthsBetweenSept5AndOctober6MonthAndOneDay() {
        Date date1 = DateUtils.createStartOfDay(2011, 9, 2);
        Date date2 = DateUtils.createStartOfDay(2011, 10, 6);
        
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(1, monthsBetweenDays[0]);
        assertEquals(1, monthsBetweenDays[1]);
    }

  */  
    public void testMonthsBetween11Jun2004And11Dec2004() {
        Date date1 = DateUtils.createStartOfDay(2004, 6, 11);
        Date date2 = DateUtils.createStartOfDay(2004, 12, 11);
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(6, monthsBetweenDays[0]);
        assertEquals(0, monthsBetweenDays[1]);
    }

    public void testMonthsBetween11Jun2004And11Jan2005() {
        Date date1 = DateUtils.createStartOfDay(2004, 6, 11);
        Date date2 = DateUtils.createStartOfDay(2005, 1, 11);
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(7, monthsBetweenDays[0]);
        assertEquals(0, monthsBetweenDays[1]);
    }

    public void testMonthsBetween07Oct2004And06Jan2005() {
        Date date1 = DateUtils.createStartOfDay(2004, 10, 07);
        Date date2 = DateUtils.createStartOfDay(2005, 1, 07);
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(3, monthsBetweenDays[0]);
        assertEquals(0, monthsBetweenDays[1]);
    }

    public void testMonthsBetween29Feb2004And01Mar2004() {
        Date date1 = DateUtils.createStartOfDay(2004, 2, 29);
        Date date2 = DateUtils.createStartOfDay(2004, 3, 1);
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(0, monthsBetweenDays[0]);
        assertEquals(1, monthsBetweenDays[1]);
    }

    public void testMonthsBetween29Feb2004And29Mar2004() {
        Date date1 = DateUtils.createStartOfDay(2004, 2, 29);
        Date date2 = DateUtils.createStartOfDay(2004, 3, 29);
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(1, monthsBetweenDays[0]);
        assertEquals(0, monthsBetweenDays[1]);
    }

    public void testMonthsBetween29Feb2004And01Mar2005() {
        Date date1 = DateUtils.createStartOfDay(2004, 2, 29);
        Date date2 = DateUtils.createStartOfDay(2005, 3, 1);
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(12, monthsBetweenDays[0]);
        assertEquals(1, monthsBetweenDays[1]);
    }

    public void testMonthsBetween28Feb2004And29Mar2004() {
        Date date1 = DateUtils.createStartOfDay(2004, 2, 28);
        Date date2 = DateUtils.createStartOfDay(2004, 3, 29);
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(1, monthsBetweenDays[0]);
        assertEquals(1, monthsBetweenDays[1]);
    }

    public void testMonthsBetween1Feb2004And1Feb2005() {
        Date date1 = DateUtils.createStartOfDay(2004, 2, 1);
        Date date2 = DateUtils.createStartOfDay(2005, 2, 1);
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(12, monthsBetweenDays[0]);
        assertEquals(0, monthsBetweenDays[1]);
    }

    public void testMonthsBetween1Jan2004And1Feb2004() {
        Date date1 = DateUtils.createStartOfDay(2004, 1, 1);
        Date date2 = DateUtils.createStartOfDay(2004, 2, 1);
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(1, monthsBetweenDays[0]);
        assertEquals(0, monthsBetweenDays[1]);
    }

    public void testMonthsBetween1Jan2004And1Feb2005() {
        Date date1 = DateUtils.createStartOfDay(2004, 1, 1);
        Date date2 = DateUtils.createStartOfDay(2005, 2, 1);
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(13, monthsBetweenDays[0]);
        assertEquals(0, monthsBetweenDays[1]);
    }

    public void testMonthsBetween1Jan2000And1Mar2005() {
        Date date1 = DateUtils.createStartOfDay(2000, 1, 1);
        Date date2 = DateUtils.createStartOfDay(2005, 3, 1);
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(62, monthsBetweenDays[0]);
        assertEquals(0, monthsBetweenDays[1]);
    }

    public void testMonthsBetween1Feb2000And28Feb2000() {
        Date date1 = DateUtils.createStartOfDay(2000, 2, 1);
        Date date2 = DateUtils.createStartOfDay(2000, 2, 29);
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(0, monthsBetweenDays[0]);
        assertEquals(28, monthsBetweenDays[1]);
    }

    public void testMonthsBetween1Feb2000And29Feb2000() {
        Date date1 = DateUtils.createStartOfDay(2000, 2, 1);
        Date date2 = DateUtils.createStartOfDay(2000, 3, 1);
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(1, monthsBetweenDays[0]);
        assertEquals(0, monthsBetweenDays[1]);
    }

    public void testMonthsBetween10Sep1979And1Dec2004() {
        Date date1 = DateUtils.createStartOfDay(1979, 9, 10);
        Date date2 = DateUtils.createStartOfDay(2004, 12, 1);
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(302, monthsBetweenDays[0]);
        assertEquals(21, monthsBetweenDays[1]);
    }

    public void testMonthsBetween1Jan2008And29Feb2008() {
        Date date1 = DateUtils.createStartOfDay(2008, 1, 1);
        Date date2 = DateUtils.createStartOfDay(2008, 2, 29);
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(1, monthsBetweenDays[0]);
        assertEquals(28, monthsBetweenDays[1]);
    }

    public void testMonthsBetween1Jan2008And1Mar2008() {
        Date date1 = DateUtils.createStartOfDay(2008, 1, 1);
        Date date2 = DateUtils.createStartOfDay(2008, 3, 1);
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(2, monthsBetweenDays[0]);
        assertEquals(0, monthsBetweenDays[1]);
    }

    public void testMonthsBetween1Feb2100And28Feb2100() {
        Date date1 = DateUtils.createStartOfDay(2100, 2, 1);
        Date date2 = DateUtils.createStartOfDay(2100, 2, 28);
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(0, monthsBetweenDays[0]);
        assertEquals(27, monthsBetweenDays[1]);
    }

    public void testMonthsBetween28Feb2000And1Oct2000() {
        Date date1 = DateUtils.createStartOfDay(2000, 2, 28);
        Date date2 = DateUtils.createStartOfDay(2000, 10, 1);
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(7, monthsBetweenDays[0]);
        assertEquals(3, monthsBetweenDays[1]);
    }

    public void testMonthsBetween25Mar2000And1Oct2000() {
        Date date1 = DateUtils.createStartOfDay(2000, 3, 25);
        Date date2 = DateUtils.createStartOfDay(2000, 10, 1);
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(6, monthsBetweenDays[0]);
        assertEquals(6, monthsBetweenDays[1]);
    }

    public void testMonthsBetweenFebAndMarSameYearDay1MoreThanD2() {
        Date date1 = DateUtils.createStartOfDay(2000, 2, 29);
        Date date2 = DateUtils.createStartOfDay(2000, 3, 1);
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(0, monthsBetweenDays[0]);
        assertEquals(1, monthsBetweenDays[1]);
    }

    public void testMonthsBetweenFebAndMarSameYearDay1LessThanD2() {
        Date date1 = DateUtils.createStartOfDay(2000, 2, 1);
        Date date2 = DateUtils.createStartOfDay(2000, 3, 25);
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(1, monthsBetweenDays[0]);
        assertEquals(24, monthsBetweenDays[1]);
    }

    public void testMonthsBetweenFebAndMarSameYearDay1LessThanD2Interchanged() {
        Date date1 = DateUtils.createStartOfDay(2000, 2, 1);
        Date date2 = DateUtils.createStartOfDay(2000, 3, 25);
        try {
            DateUtils.monthsBetween(date2, date1);
            fail("IllegalArgumentException expected");
        }
        catch (IllegalArgumentException e) {
        }
    }

    public void testMonthsBetweenConsecutiveMonthsInLeapYear() {
        Date date1 = DateUtils.createStartOfDay(2000, 2, 25);
        Date date2 = DateUtils.createStartOfDay(2000, 3, 25);
        long[] monthsBetweenDays = DateUtils.monthsBetween(date1, date2);
        assertEquals(2, monthsBetweenDays.length);
        assertEquals(1, monthsBetweenDays[0]);
        assertEquals(0, monthsBetweenDays[1]);
    }

    public void testWorkDays() throws Exception {
        // less than a week
        assertWorkDays(0, "24/03/2005", "24/03/2005");
        assertWorkDays(1, "24/03/2005", "25/03/2005");
        assertWorkDays(1, "25/03/2005", "24/03/2005");
        assertWorkDays(4, "21/03/2005", "25/03/2005");
        assertWorkDays(1, "25/03/2005", "28/03/2005");

        // more than a week
        assertWorkDays(20, "1/03/2005", "29/03/2005");
        assertWorkDays(19, "1/03/2005", "28/03/2005");
        assertWorkDays(18, "1/03/2005", "25/03/2005");
        assertWorkDays(5, "1/03/2005", "8/03/2005");
        assertWorkDays(4, "1/03/2005", "7/03/2005");

        // days on weekends
        assertWorkDays(3, "1/03/2005", "4/03/2005");
        assertWorkDays(3, "1/03/2005", "6/03/2005");
        assertWorkDays(3, "1/03/2005", "5/03/2005");
        assertWorkDays(7, "4/03/2005", "15/03/2005");
        assertWorkDays(6, "5/03/2005", "15/03/2005");
        assertWorkDays(6, "6/03/2005", "15/03/2005");
        assertWorkDays(6, "7/03/2005", "15/03/2005");

        assertWorkDays(0, "31/12/2004", "1/1/2005");
        assertWorkDays(260, "31/12/2004", "30/12/2005");

        // Times within a day
        assertWorkDaysTime(0, "24/03/2005 02:30", "24/03/2005 21:30");
        assertWorkDaysTime(0, "24/03/2005 00:00", "24/03/2005 23:59");
        assertWorkDaysTime(1, "24/03/2005 23:59", "25/03/2005 00:00");
        assertWorkDaysTime(1, "24/03/2005 02:30", "25/03/2005 21:30");
    }

    public void assertWorkDays(int days, String from, String to) throws Exception {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        assertEquals(days, DateUtils.calculateWorkingDaysBetween(df.parse(from), df.parse(to)));
    }

    public void assertWorkDaysTime(int days, String from, String to) throws Exception {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        assertEquals(days, DateUtils.calculateWorkingDaysBetween(df.parse(from), df.parse(to)));
    }

    public void assertDatesEqual(String expected, String got) throws Exception {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss:SSS");
        assertEquals(df.parse(expected), df.parse(got));
    }

    public void assertEndOfDayIs(String day, String endOfDay) throws Exception {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");
        assertEquals(df.parse(endOfDay), DateUtils.endOfDay(df.parse(day)));
    }

    public void assertEndOfDayWithPivotHourIs(long pivotHour, String day, String endOfDay) throws Exception {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");
        assertEquals(df.parse(endOfDay), DateUtils.getEndOfDayUsingPivot(df.parse(day), pivotHour));
    }

    public void assertEndOfPreviousDay(String day, String endOfDay) throws Exception {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");
        assertEquals(df.parse(endOfDay), DateUtils.getEndOfDayBefore(df.parse(day)));
    }

    public void testEndOfDay() throws Exception {
        assertEndOfDayIs("24/03/2005 1:30:00.000", "24/03/2005 23:59:59.999");
        assertEndOfDayIs("24/03/2005 22:30:00.000", "24/03/2005 23:59:59.999");
        assertEndOfDayWithPivotHourIs(8, "24/03/2005 22:30:00.000", "24/03/2005 23:59:59.999");
        assertEndOfDayWithPivotHourIs(8, "24/03/2005 8:30:00.000", "24/03/2005 23:59:59.999");
        assertEndOfDayWithPivotHourIs(8, "24/03/2005 7:30:00.000", "23/03/2005 23:59:59.999");
        assertEndOfPreviousDay("24/03/2005 7:30:00.000", "23/03/2005 23:59:59.999");
        assertEndOfPreviousDay("24/03/2005 00:00:00.000", "23/03/2005 23:59:59.999");
        assertEndOfPreviousDay("24/03/2005 23:59:59.999", "23/03/2005 23:59:59.999");
    }

    public void testGetEndOfMonth() throws Exception {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");
        Date endOfMonth = DateUtils.createEndOfMonth(df.parse("24/04/2005 1:30:00.000"));
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(endOfMonth);
        assertTrue(calendar.get(Calendar.DATE) == 30);
    }

    public void testGetStartUSAFinancialYear() {
        Date financialYearStart = DateUtils.createStartOfDay(2003, 12, 1);
        Date toDate = DateUtils.createStartOfDay(2003, 12, 4);
        assertEquals("Near beginning of financial year", financialYearStart, DateUtils.startOfUSAFinancialYearBefore(toDate));

        toDate = DateUtils.createStartOfDay(2004, 8, 24);
        assertEquals("Middle of financial year", financialYearStart, DateUtils.startOfUSAFinancialYearBefore(toDate));

        toDate = DateUtils.createStartOfDay(2004, 11, 30);
        assertEquals("Last day of financial year", financialYearStart, DateUtils.startOfUSAFinancialYearBefore(toDate));

        toDate = DateUtils.createStartOfDay(2003, 12, 1);
        assertEquals("first day of financial", financialYearStart, DateUtils.startOfUSAFinancialYearBefore(toDate));

        toDate = DateUtils.createStartOfDay(2005, 3, 1);
        assertEquals("Next Financial year", DateUtils.createStartOfDay(2004, 12, 1), DateUtils.startOfUSAFinancialYearBefore(toDate));

        try {
            DateUtils.startOfUSAFinancialYearBefore((Date) null);
            fail("An Illegal Argument Exception should have been thrown: null date");
        }
        catch (IllegalArgumentException iae) {
            // This should happen
            assertTrue(true);
        }
    }

    public void testGetStartOfMonth() {
        assertEquals(DateUtils.createStartOfDay(2005, 1, 1), DateUtils.getStartOfMonth(DateUtils.createStartOfDay(2005, 1, 17)));
        assertEquals(DateUtils.createStartOfDay(2004, 7, 1), DateUtils.getStartOfMonth(DateUtils.createStartOfDay(2004, 7, 1)));
        assertEquals(DateUtils.createStartOfDay(2004, 7, 1), DateUtils.getStartOfMonth(DateUtils.createStartOfDay(2004, 7, 8)));
        assertEquals(DateUtils.createStartOfDay(2004, 7, 1), DateUtils.getStartOfMonth(DateUtils.createStartOfDay(2004, 7, 28)));
    }

    public void testGetEndOfMonth2() {
        assertEquals(DateUtils.createStartOfDay(2005, 1, 31), DateUtils.getEndOfMonth(DateUtils.createStartOfDay(2005, 1, 17)));
        assertEquals(DateUtils.createStartOfDay(2005, 2, 28), DateUtils.getEndOfMonth(DateUtils.createStartOfDay(2005, 2, 15)));
        assertEquals(DateUtils.createStartOfDay(2004, 2, 29), DateUtils.getEndOfMonth(DateUtils.createStartOfDay(2004, 2, 15)));
        assertEquals(DateUtils.createStartOfDay(2004, 7, 31), DateUtils.getEndOfMonth(DateUtils.createStartOfDay(2004, 7, 8)));
        assertEquals(DateUtils.createStartOfDay(2004, 4, 30), DateUtils.getEndOfMonth(DateUtils.createStartOfDay(2004, 4, 28)));
    }

    public void testGetStartOfPreviousMonth() {
        assertEquals(DateUtils.createStartOfDay(2004, 12, 1), DateUtils.getStartOfPreviousMonth(DateUtils.createStartOfDay(2005, 1, 17)));
        assertEquals(DateUtils.createStartOfDay(2004, 6, 1), DateUtils.getStartOfPreviousMonth(DateUtils.createStartOfDay(2004, 7, 1)));
        assertEquals(DateUtils.createStartOfDay(2004, 6, 1), DateUtils.getStartOfPreviousMonth(DateUtils.createStartOfDay(2004, 7, 8)));
        assertEquals(DateUtils.createStartOfDay(2004, 6, 1), DateUtils.getStartOfPreviousMonth(DateUtils.createStartOfDay(2004, 7, 28)));
    }

    public void testGetEndOfPreviousMonth() {
        assertEquals(DateUtils.createStartOfDay(2004, 12, 31), DateUtils.getEndOfPreviousMonth(DateUtils.createStartOfDay(2005, 1, 17)));
        assertEquals(DateUtils.createStartOfDay(2005, 1, 31), DateUtils.getEndOfPreviousMonth(DateUtils.createStartOfDay(2005, 2, 15)));
        assertEquals(DateUtils.createStartOfDay(2004, 2, 29), DateUtils.getEndOfPreviousMonth(DateUtils.createStartOfDay(2004, 3, 15)));
        assertEquals(DateUtils.createStartOfDay(2004, 7, 31), DateUtils.getEndOfPreviousMonth(DateUtils.createStartOfDay(2004, 8, 8)));
        assertEquals(DateUtils.createStartOfDay(2004, 4, 30), DateUtils.getEndOfPreviousMonth(DateUtils.createStartOfDay(2004, 5, 28)));
    }

    public void testGetStartOfReportMonth() {
        assertEquals(DateUtils.createStartOfDay(2004, 12, 1), DateUtils.getReportStartOfMonth(DateUtils.createStartOfDay(2005, 1, 15)));
        assertEquals(DateUtils.createStartOfDay(2004, 6, 1), DateUtils.getReportStartOfMonth(DateUtils.createStartOfDay(2004, 7, 1)));
        assertEquals(DateUtils.createStartOfDay(2004, 7, 1), DateUtils.getReportStartOfMonth(DateUtils.createStartOfDay(2004, 7, 16)));
        assertEquals(DateUtils.createStartOfDay(2004, 7, 1), DateUtils.getReportStartOfMonth(DateUtils.createStartOfDay(2004, 7, 28)));
    }

    public void testGetEndOfReportMonth() {
        assertEquals(DateUtils.createStartOfDay(2004, 12, 31), DateUtils.getReportEndOfMonth(DateUtils.createStartOfDay(2005, 1, 15)));
        assertEquals(DateUtils.createStartOfDay(2005, 1, 31), DateUtils.getReportEndOfMonth(DateUtils.createStartOfDay(2005, 1, 16)));
    }

    public void testUSFinancialQuarter() {
        assertEquals(1, DateUtils.getStrictUSFYQuarter(DateUtils.createStartOfDay(2005, 12, 1)));
        assertEquals(4, DateUtils.getStrictUSFYQuarter(DateUtils.createStartOfDay(2005, 11, 1)));
        assertEquals(4, DateUtils.getStrictUSFYQuarter(DateUtils.createStartOfDay(2005, 11, 30)));
    }

    public void testUSFinancialYear() {
        assertEquals(2006, DateUtils.getFinancialYear(DateUtils.createStartOfDay(2005, 12, 1)));
        assertEquals(2007, DateUtils.getFinancialYear(DateUtils.createStartOfDay(2006, 12, 1)));
    }

    public void testMonth() {
        assertEquals(11, DateUtils.month(DateUtils.createStartOfDay(2005, 12, 1)));
    }

    public void testHalfYearlyStartDate() {
        Date halfYearlyStartdate = DateUtils.zeroOutTime(DateUtils.getHalfYearlyStartDate(DateUtils.createStartOfDay(2007, 01, 01)));
        assertEquals(DateUtils.zeroOutTime(DateUtils.createStartOfDay(2007, 01, 01)), halfYearlyStartdate);

        halfYearlyStartdate = DateUtils.zeroOutTime(DateUtils.getHalfYearlyStartDate(DateUtils.createStartOfDay(2007, 03, 11)));
        assertEquals(DateUtils.zeroOutTime(DateUtils.createStartOfDay(2007, 01, 01)), halfYearlyStartdate);

        halfYearlyStartdate = DateUtils.zeroOutTime(DateUtils.getHalfYearlyStartDate(DateUtils.createStartOfDay(2007, 06, 30)));
        assertEquals(DateUtils.zeroOutTime(DateUtils.createStartOfDay(2007, 01, 01)), halfYearlyStartdate);

        halfYearlyStartdate = DateUtils.zeroOutTime(DateUtils.getHalfYearlyStartDate(DateUtils.createStartOfDay(2007, 07, 1)));
        assertNotSame(DateUtils.zeroOutTime(DateUtils.createStartOfDay(2007, 01, 01)), halfYearlyStartdate);
    }

    public void testHalfYearlyEndDate() {
        Date halfYearlyEnddate = DateUtils.zeroOutTime(DateUtils.getHalfYearlyEndDate(DateUtils.createStartOfDay(2007, 07, 01)));
        assertEquals(DateUtils.zeroOutTime(DateUtils.createStartOfDay(2007, 12, 31)), halfYearlyEnddate);

        halfYearlyEnddate = DateUtils.zeroOutTime(DateUtils.getHalfYearlyEndDate(DateUtils.createStartOfDay(2007, 9, 11)));
        assertEquals(DateUtils.zeroOutTime(DateUtils.createStartOfDay(2007, 12, 31)), halfYearlyEnddate);

        halfYearlyEnddate = DateUtils.zeroOutTime(DateUtils.getHalfYearlyEndDate(DateUtils.createStartOfDay(2007, 12, 31)));
        assertEquals(DateUtils.zeroOutTime(DateUtils.createStartOfDay(2007, 12, 31)), halfYearlyEnddate);

        halfYearlyEnddate = DateUtils.zeroOutTime(DateUtils.getHalfYearlyEndDate(DateUtils.createStartOfDay(2007, 05, 01)));
        assertNotSame(DateUtils.zeroOutTime(DateUtils.createStartOfDay(2007, 07, 01)), halfYearlyEnddate);

        halfYearlyEnddate = DateUtils.zeroOutTime(DateUtils.getHalfYearlyEndDate(DateUtils.createStartOfDay(2007, 01, 31)));
        assertEquals(DateUtils.zeroOutTime(DateUtils.createStartOfDay(2007, 06, 30)), halfYearlyEnddate);

        halfYearlyEnddate = DateUtils.zeroOutTime(DateUtils.getHalfYearlyEndDate(DateUtils.createStartOfDay(2007, 03, 31)));
        assertEquals(DateUtils.zeroOutTime(DateUtils.createStartOfDay(2007, 06, 30)), halfYearlyEnddate);

        halfYearlyEnddate = DateUtils.zeroOutTime(DateUtils.getHalfYearlyEndDate(DateUtils.createStartOfDay(2007, 04, 30)));
        assertEquals(DateUtils.zeroOutTime(DateUtils.createStartOfDay(2007, 06, 30)), halfYearlyEnddate);
    }
}
>>>>>>> d64a92cd44c1cd1279e7c9921940f7cb9d860b60
