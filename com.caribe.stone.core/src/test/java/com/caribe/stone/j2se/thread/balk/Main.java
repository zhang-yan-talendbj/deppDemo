package com.caribe.stone.j2se.thread.balk;

public class Main {

	public static void main(String[] args) {
		Data data=new Data("data.txt", "");
		new ChangerThread(data).start();
		new SaverThread(data).start();
	}
}
