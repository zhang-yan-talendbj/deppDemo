<<<<<<< HEAD
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
=======
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
>>>>>>> d64a92cd44c1cd1279e7c9921940f7cb9d860b60
