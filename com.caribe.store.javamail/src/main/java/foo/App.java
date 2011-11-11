package foo;

import java.util.Timer;

public class App {

	public static void main(String[] args) {
		Timer timer=new Timer();   
		timer.schedule(new AppTask(),1000,1000*60*60);   
	}
}
