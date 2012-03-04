package com.caribe.stone.word;

import java.util.Date;

public class Word {
	private String wordContent;
	private Integer cycleLevel;
	private Date lastReviewTime;

	@Override
	public String toString() {
		return "Word [word=" + wordContent + ", cycleLevel=" + cycleLevel + ", alarmTime=" + lastReviewTime + "]";
	}

	public Word(String word, Integer cycleLevel) {
		this(word, cycleLevel, new Date());
	}

	public Word(String wordContent, Integer cycleLevel, Date lastReviewTime) {
		this.wordContent = wordContent;
		this.cycleLevel = cycleLevel;
		this.lastReviewTime = lastReviewTime;
	}

	public String getWordContent() {
		return wordContent;
	}

	public void setWordContent(String word) {
		this.wordContent = word;
	}

	public Integer getCycleLevel() {
		return cycleLevel;
	}

	public void setCycleLevel(Integer wordLevel) {
		this.cycleLevel = wordLevel;
	}

	public Date getLastReviewTime() {
		return lastReviewTime;
	}

	public void setLastReviewTime(Date alarmTime) {
		this.lastReviewTime = alarmTime;
	}
}
