<<<<<<< HEAD
package ch1;

import static org.junit.Assert.*;

import org.junit.Test;

public class BankTest {

	@Test
	public void testWidthdraw() {
		final Bank bank = new Bank(1000, "Bruce");
		new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 100000; i++) {
					bank.deposit(10);
				}
			}
		}).start();
		new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 110000; i++) {
					bank.widthdraw(10);
				}
			}
		}).start();
		System.out.println();
	}

	public static void main(String[] args) {

		Bank bank = new Bank(1000, "Bruce");
		new ClientThread(bank).start();
		new ClientThread(bank).start();
		new ClientThread(bank).start();
		new ClientThread(bank).start();
		new ClientThread(bank).start();
		new ClientThread(bank).start();
		new ClientThread(bank).start();
		new ClientThread(bank).start();
		new ClientThread(bank).start();
		new ClientThread(bank).start();
		new ClientThread(bank).start();
		new ClientThread(bank).start();
		new ClientThread(bank).start();
		new ClientThread(bank).start();
		new ClientThread(bank).start();
		new ClientThread(bank).start();
	}
}
=======
package ch1;

import static org.junit.Assert.*;

import org.junit.Test;

public class BankTest {

	@Test
	public void testWidthdraw() {
		final Bank bank = new Bank(1000, "Bruce");
		new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 100000; i++) {
					bank.deposit(10);
				}
			}
		}).start();
		new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 110000; i++) {
					bank.widthdraw(10);
				}
			}
		}).start();
		System.out.println();
	}

	public static void main(String[] args) {

		Bank bank = new Bank(1000, "Bruce");
		new ClientThread(bank).start();
		new ClientThread(bank).start();
		new ClientThread(bank).start();
		new ClientThread(bank).start();
		new ClientThread(bank).start();
		new ClientThread(bank).start();
		new ClientThread(bank).start();
		new ClientThread(bank).start();
		new ClientThread(bank).start();
		new ClientThread(bank).start();
		new ClientThread(bank).start();
		new ClientThread(bank).start();
		new ClientThread(bank).start();
		new ClientThread(bank).start();
		new ClientThread(bank).start();
		new ClientThread(bank).start();
	}
}
>>>>>>> d64a92cd44c1cd1279e7c9921940f7cb9d860b60
