package com.caribe.stone.word;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class AppTest {

	public static void main(String[] args) throws IOException {
		App app = new App();
		List<Long> testTimeList = getTestTimeList();
		WordLevelTime wordLevelTime = new WordLevelTime();
		wordLevelTime.setTimeList(testTimeList);
		app.setWordLevelTime(wordLevelTime);
		WordServiceImpl wordService = new WordServiceImpl();
		wordService.setJdbcUrl("jdbc:h2:tcp://localhost/~/word");
		app.setWordService(wordService);

		app.start();
	}

	private static List<Long> getTestTimeList() {
		List<Long> testTimeList = new LinkedList<Long>();
		long second = 1000L;
		testTimeList.add(second);
		testTimeList.add(second*1);
		testTimeList.add(second*1);
		testTimeList.add(second*1);
		testTimeList.add(second*1);
		return testTimeList;
	}
}
