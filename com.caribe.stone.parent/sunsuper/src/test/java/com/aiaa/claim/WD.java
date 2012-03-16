package com.aiaa.claim;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class WD {

	public static void main(String[] args) {
		DateTimeFormatter cedPattern = DateTimeFormat.forPattern("aaaa/dd-MMM");
		System.out.println(new DateTime().toString(cedPattern));
	}
}
