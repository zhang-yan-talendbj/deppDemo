package com.caribe.stone.thread.balk;

import java.util.Random;

public class SaverThread extends Thread {
	private final Data data;
	private final Random r;

	public SaverThread(Data data) {
		this.data = data;
		this.r = new Random();
	}

	@Override
	public void run() {

		try {
			while (true) {
				data.save();
				Thread.sleep(r.nextInt(1000));
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
