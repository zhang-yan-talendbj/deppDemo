package com.caribe.stone.thread.ch1;

public class Gate {
	private int counter = 0;
	private String name = "Nobody";
	private String address = "NowWhere";

	public void pass(String name, String address) {
		this.counter++;
		this.name = name;
		this.address = address;
//		check();
		safeMethod("safe1","safe1");
		safeMethod("safe2","safe2");
	}

	private void safeMethod(String string, String string2) {
		if(!string.equals(string2)){
			System.out.println("Not equals");
		}
	}

	private void check() {
		if (name.charAt(0) != address.charAt(0)) {
			System.out.println("**************BROKEN***********" + toString());
		}
	}

	@Override
	public String toString() {
		return "No." + counter + ":" + name + "," + address;
	}

}
