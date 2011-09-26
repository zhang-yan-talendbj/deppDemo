package foo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtils {
	private PropertiesUtils() {
	}

	private static Properties pro = null;
	public static void loadProperties(String config) {
		try {
			if(pro==null){
				pro=new Properties();
			}
			pro.load(new FileInputStream(config));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getProperty(String property) {
		return pro.getProperty(property);
	}

	/**Don't update properties
	 * @return
	 */
	public static Properties getProperties() {
		return pro;
	}
	
	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getContextClassLoader().getResource(""));
	}
}
