package com.caribe.stone.word;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class WordLevelTimeTest {

	@Test
	public void testIsOut() throws ParseException {
		WordLevelTime wordLevelTime = new WordLevelTime();
		wordLevelTime.setTimeList(getTestTimeList());

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		;
		Long out = wordLevelTime.isOut(2, sdf.parse("2012-03-03 14:14:00"), sdf.parse("2012-03-03 14:14:00"));
		assertTrue(10000L == out);
	}

	private static List<Long> getTestTimeList() {
		List<Long> testTimeList = new LinkedList<Long>();
		testTimeList.add(1000L);
		testTimeList.add(5000L);// 5s
		testTimeList.add(10000L);// 10s
		testTimeList.add(100000L);// 100s
		testTimeList.add(1000000L);
		return testTimeList;
	}
}
