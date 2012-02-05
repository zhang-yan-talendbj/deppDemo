package com.caribe.stone.thread.SingleThreadedExecution.a1;

public class UserThread extends Thread {
	private final Gate gate;
	private String name;
	private String address;

	public UserThread(Gate gate, String name, String address) {
		this.gate = gate;
		this.name = name;
		this.address = address;
	}

	@Override
	public void run() {
		System.out.println(name + " Begin.");
		while (true) {
			gate.pass(name, address);
		}
	}

}
