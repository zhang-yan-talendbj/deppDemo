package z.java.util;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import junit.framework.Assert;

import org.junit.Test;

import com.caribe.stone.utils.DateUtils;

public class GregorianCalendarTest {

	@Test
	public void test() throws ParseException {
		Calendar calendar = new GregorianCalendar();
		Date date = DateUtils.parse("2013/1/1");
		calendar.setTime(date);
		calendar.add(Calendar.DATE, 1);
		Assert.assertEquals(DateUtils.parse("2013/1/2"), calendar.getTime());
	}

}
