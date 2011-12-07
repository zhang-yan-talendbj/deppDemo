package com.caribe.stone.effective.inheritance;

public class Super {

	// Broken - constructor invokes an overridable method.
	public Super() {
		overriderMe();
	}

	public void overriderMe() {
	}
}
