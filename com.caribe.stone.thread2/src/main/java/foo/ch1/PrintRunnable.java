package foo.ch1;

public class PrintRunnable {

	public static void main(String[] args) {
		new Thread( new Runnable() {
			public void run() {
				for (int i = 0; i < 100; i++) {
					System.out.println("Hello~");
				}
			}
		}).start();
		new Thread( new Runnable() {
			public void run() {
				for (int i = 0; i < 100; i++) {
					System.out.println("World~");
				}
			}
		}).start();
	}
}
