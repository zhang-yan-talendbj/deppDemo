package com.caribe.stone.effective.inheritance;

import java.util.Date;

public class Sub extends Super {
	private final Date date;// Blank final,set by constructor

	Sub() {
		date = new Date();
	}

	@Override
	public void overriderMe() {
		System.out.println(date);
	}

	public static void main(String[] args) {
		Sub sub = new Sub();
		sub.overriderMe();
	}
}
