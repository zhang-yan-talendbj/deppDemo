package com.caribe.stone.thread.GuardedSuspension.sanguosha;

public class King {
	private int hp;
	private String name;
	private String lastWords;
	private int kill;
	private int save;

	public King(int hp, String name, String lastWords) {
		this.hp = hp;
		this.name = name;
		this.lastWords = lastWords;
	}

	@Override
	public String toString() {
		return "King [hp=" + hp + ", name=" + name + "]";
	}

	public synchronized int save() {
		save++;
		while (hp >= 100) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		hp++;
		return hp;
	}

	public synchronized int kill() {
		kill++;
		if (hp <= 0) {
			System.out.println(lastWords);
			System.out.println("Kill:"+kill+":"+"Save:"+save);
			System.exit(0);
		} else {
			hp--;
			notifyAll();
		}
		return hp;
	}
	
	public String getName() {
		return name;
	}
}
