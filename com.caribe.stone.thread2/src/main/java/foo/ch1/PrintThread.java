package foo.ch1;

public class PrintThread extends Thread {
	private String name;

	public PrintThread(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			System.out.println(this.name);
		}
	}
	public static void main(String[] args) {
		new PrintThread("Hello").start();
		new PrintThread("Word").start();
	}
}
