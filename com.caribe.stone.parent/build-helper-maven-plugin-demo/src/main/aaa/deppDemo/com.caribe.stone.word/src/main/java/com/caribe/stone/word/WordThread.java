package com.caribe.stone.word;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class WordThread extends Thread {
	private Word word;
	private Logger LOG = LoggerFactory.getLogger(this.getClass());
	private WordLevelTime wordLevelTime;

	public WordLevelTime getWordLevelTime() {
		return wordLevelTime;
	}

	public void setWordLevelTime(WordLevelTime wordLevelTime) {
		this.wordLevelTime = wordLevelTime;
	}

	public Word getWord() {
		return word;
	}

	public void setWord(Word word) {
		this.word = word;
	}

	private WordService wordService;

	public WordService getWordService() {
		return wordService;
	}

	public void setWordService(WordService wordService) {
		this.wordService = wordService;
	}

	public WordThread(Word word, WordLevelTime wordLevelTime, WordService wordService) {
		// TODO Auto-generated constructor stub
		this.word = word;
		this.wordService = wordService;
		this.wordLevelTime = wordLevelTime;
	}

	@Override
	public void run() {
		Integer cycleLevel = wordService.getWordLevel(getWord().getWordContent());
		LOG.info("cycleLevel:" + cycleLevel);
		getWord().setCycleLevel(cycleLevel);
		if (cycleLevel >= getWordLevelTime().getSize()) {
			return;
		}
		try {
			Date lastReviewTime = getWord().getLastReviewTime();
			LOG.info(lastReviewTime.toString());
			Long index = getWordLevelTime().isOut(cycleLevel, lastReviewTime, new Date());
			LOG.info("Next word:" + index / 1000L);
			Thread.sleep(index);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ReviewFrame reviewFrame = new ReviewFrame(getWord().getWordContent());

		reviewFrame.getRemeberBtn().addActionListener(new WordListener(reviewFrame, this, true));
		reviewFrame.getForgetBtn().addActionListener(new WordListener(reviewFrame, this, false));
	}
}