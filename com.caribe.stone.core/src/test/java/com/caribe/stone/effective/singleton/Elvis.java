package com.caribe.stone.effective.singleton;

//singleton with pulbic final field
class Elvis {
	private Elvis() {
		if (INSTANCE != null) {
			throw new IllegalStateException("Singleton" + " instance already created.");
		}
	}

	public static final Elvis INSTANCE = new Elvis();

	public String say() {
		return "Hi, I'm Elvis.";
	}
}

// Singleton with static factory
class ElvisStaticFactory {

	private static final ElvisStaticFactory INSTANCE = new ElvisStaticFactory();

	private ElvisStaticFactory() {

	}

	public static ElvisStaticFactory getInstance() {
		return INSTANCE;
	}

	public String say() {
		return "Hi, I'm ElvisStaticFactory.";
	}
}