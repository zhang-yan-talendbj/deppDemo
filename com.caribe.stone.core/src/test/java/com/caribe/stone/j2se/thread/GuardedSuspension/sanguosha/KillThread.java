package com.caribe.stone.j2se.thread.GuardedSuspension.sanguosha;

import java.util.Random;

public class KillThread extends Thread {
	private King king;
	private Random r;
	private String msg;

	public KillThread(King k, String name, String msg) {
		super(name);
		this.msg=msg;
		this.king = k;
		r = new Random();
	}

	@Override
	public void run() {
		while (true) {
			int hp = king.kill();
			System.out.print(Thread.currentThread().getName()+":"+msg);
			System.out.println("\t主公血量:"+hp);
			try {
				Thread.sleep(r.nextInt(10));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
