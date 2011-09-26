package foo.util;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class BreanTest {

	public static void main(String[] args) {
		int i;
		for(i=0;i<100;i++){
			if(i==50){
				break;
			}
		}
		System.out.println(i);
		
	}


	private static void testLastModifed() {
		Timer time = new Timer();
		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				File f = new File("tmp");
				try {
					System.out.println(f.createNewFile());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(new Date(f.lastModified()));
				f.setLastModified(new Date().getTime());

			}
		};
		time.schedule(task, 1000, 1000);
	}
}
