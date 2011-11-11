package ch1;

public class FinalThread {

	public static void main(String[] args) {
		new Thread(new Runnable() {
			public void run() {
				while(true){
					System.out.println("hello");
				}
			}
		}).start();
		new Thread(new Runnable() {
			public void run() {
				while(true){
					System.out.println("word");
				}
			}
		}).start();
	}
}
