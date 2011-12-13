package com.caribe.stone.thread.introduce;

public class Bank {

	private int money;
	private String name;

	public Bank(int money, String name) {
		super();
		this.money = money;
		this.name = name;
	}

	public void deposit(int m) {
		this.money += m;
	}

	public boolean widthdraw(int m) {
		if (money >= m) {
			money -= m;
			check();
			return true;
		} else {
			return false;
		}
	}

	private void check() {
		if (this.money < 0) {
			System.out.println("可用余额为负数！money=" + money);
			try {
				throw new Exception("over~");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.exit(0);
			}
		}
	}
}
