package deppDemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Hello world!
 * 
 */
public class WarDemo {
	public static void main(String[] args) {
		try {
			Process p = Runtime.getRuntime().exec("cmd /c time");
			// p.waitFor();
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));

			if (!br.ready()) {
				Thread.sleep(100);
			}
			while (true) {
				String s = br.readLine();
				if (s == null)
					break;
				System.out.println(s);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
