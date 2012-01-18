package com.caribe.stone.thread.SingleThreadedExecution.a2;

/**Why gate toString method synchronized?
 * @author bsnpbag
 *
 */
public class Main {

	public static void main(String[] args) {
		System.out.println("Testing Gate, hit CTRL+C to exit!");
		Gate gate = new Gate();
		new UserThread(gate, "Alice", "Alaska").start();
		new UserThread(gate, "Burce", "Brazil").start();
		new UserThread(gate, "Chris", "Canada").start();
	}
}
