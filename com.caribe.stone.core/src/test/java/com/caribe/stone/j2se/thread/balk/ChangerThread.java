package com.caribe.stone.j2se.thread.balk;

import java.util.Random;

public class ChangerThread extends Thread {
	private final Data data;
	private final Random r;

	public ChangerThread(Data data) {
		this.data = data;
		r = new Random();
	}

	@Override
	public void run() {
		try {
			while (true) {
				data.change("info" + r.nextInt(1000));
				Thread.sleep(r.nextInt(1000));
				data.save();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
