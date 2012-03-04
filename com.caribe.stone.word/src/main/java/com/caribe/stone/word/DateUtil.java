package com.caribe.stone.word;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");

	public static Date parse(String date) throws ParseException {
		return sdf.parse(date);
	}

	public static String format(Date date) {
		return sdf.format(date);
	}

	public static String format(Long time) {
		return sdf.format(time);
	}
}
