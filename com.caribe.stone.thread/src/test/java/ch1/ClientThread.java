<<<<<<< HEAD
package ch1;

public class ClientThread extends Thread {
	private Bank bank = null;

	public ClientThread(Bank bank) {
		this.bank = bank;
	}

	@Override
	public void run() {
		while(true){
			boolean ok = bank.widthdraw(1000);
			if(ok){
				bank.deposit(1000);
			}
		}
	}

}
=======
package ch1;

public class ClientThread extends Thread {
	private Bank bank = null;

	public ClientThread(Bank bank) {
		this.bank = bank;
	}

	@Override
	public void run() {
		while(true){
			boolean ok = bank.widthdraw(1000);
			if(ok){
				bank.deposit(1000);
			}
		}
	}

}
>>>>>>> d64a92cd44c1cd1279e7c9921940f7cb9d860b60
