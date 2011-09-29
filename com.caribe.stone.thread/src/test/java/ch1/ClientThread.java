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
