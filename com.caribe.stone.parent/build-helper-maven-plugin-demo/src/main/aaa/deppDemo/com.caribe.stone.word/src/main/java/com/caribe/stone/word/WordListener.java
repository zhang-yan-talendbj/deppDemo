package com.caribe.stone.word;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class WordListener implements ActionListener {
	private JFrame frame;
	private WordThread thread;
	private boolean flag;
	private Logger LOG = LoggerFactory.getLogger(this.getClass());
	public WordListener(JFrame j, WordThread t, boolean b) {
		super();
		this.frame = j;
		this.thread = t;
		this.flag = b;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		frame.dispose();
		thread.getWordService().insertRecord(thread, flag);

		if (thread.getWord().getCycleLevel() <= 4) {
			thread = new WordThread(thread.getWord(), thread.getWordLevelTime(), thread.getWordService());
			thread.start();
		}
	}
}