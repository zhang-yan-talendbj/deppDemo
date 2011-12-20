package com.caribe.stone.thread.GuardedSuspension.sanguosha;

public class Main {

	public static void main(String[] args) {
		King king = new King(500, "甄姬", "悼良会之永绝兮，哀一逝而异乡");

		new SaveThread(king, "华佗", "别急，有老夫在").start();
		
		new KillThread(king, "关羽", "关羽在此，尔等受死/看尔乃插标卖首").start();
		new KillThread(king, "吕布", "谁能挡我").start();
		new KillThread(king, "赵云", "喝！能进能退，乃真正法器").start();
		new KillThread(king, "马超", "全军突击！").start();
		new KillThread(king, "司马", "天意,哈哈").start();
	}
}
