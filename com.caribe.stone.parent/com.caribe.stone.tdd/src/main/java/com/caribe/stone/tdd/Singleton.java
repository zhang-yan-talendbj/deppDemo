package com.caribe.stone.tdd;

public class Singleton {

	private static final Singleton INSTANCE = new Singleton();

	private Singleton() {

	}

	public static Singleton getInstance() {
		System.out.println("thinkdeeply ");
		return INSTANCE;
	}
}
