package foo;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Demo {

	public static void main(String[] args) {
		// DateTime dateTime = new DateTime(2000, 1, 1, 0, 0, 0, 0);
		Calendar c = new GregorianCalendar(2011,9,1);
		Date ddd = c.getTime();
		int cycle = 10;
		int days = cycle % 5;
		int weeks = cycle / 5;
		int weekDay = c.get(Calendar.DAY_OF_WEEK);
		System.out.println(weekDay == Calendar.FRIDAY);
		c.add(Calendar.WEEK_OF_YEAR, 1);
		System.out.println(c.getTime());
	}
}
