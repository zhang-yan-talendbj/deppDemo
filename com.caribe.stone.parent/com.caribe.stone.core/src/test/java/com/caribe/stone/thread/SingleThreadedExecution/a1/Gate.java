package com.caribe.stone.thread.SingleThreadedExecution.a1;

public class Gate {
	private int counter = 0;
	private String name = "Nobody";
	private String address = "NowWhere";

	public void pass(String name, String address) {
		this.counter++;
		this.name = name;
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//sleep 1s then will broken. sleep between set name and set address.
		this.address = address;
		check();
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
