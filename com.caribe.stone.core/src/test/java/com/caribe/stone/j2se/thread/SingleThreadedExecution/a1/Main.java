package com.caribe.stone.j2se.thread.SingleThreadedExecution.a1;

public class Main {

	public static void main(String[] args) {
		//no synchronized, how to broken quickly?
		//延长临界区间(critical section),就能提高检出错误的可能性
		System.out.println("Testing Gate, hit CTRL+C to exit!");
		Gate gate = new Gate();
		new UserThread(gate, "Alice", "Alaska").start();
		new UserThread(gate, "Burce", "Brazil").start();
		new UserThread(gate, "Chris", "Canada").start();
	}
}
