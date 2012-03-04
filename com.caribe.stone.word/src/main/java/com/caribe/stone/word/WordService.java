package com.caribe.stone.word;

import java.util.List;


public interface  WordService {

	List<Word> getReviewWord();

	void insertRecord(WordThread thread, boolean flag);

	Integer getWordLevel(String word);

	void insertWord(Word words);

}
