<<<<<<< HEAD
package foo.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertiesUtils {
	private PropertiesUtils() {
	}
	private static Logger log = LoggerFactory.getLogger(PropertiesUtils.class);
	private static Properties pro = null;
	public static void loadProperties(String config) {
		try {
			if(pro==null){
				pro=new Properties();
			}
			pro.load(new FileInputStream(config));
		} catch (FileNotFoundException e) {
			log.warn("IOException", e);
			e.printStackTrace();
		} catch (IOException e) {
			log.warn("IOException", e);
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
	
}
=======
package foo.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertiesUtils {
	private PropertiesUtils() {
	}
	private static Logger log = LoggerFactory.getLogger(PropertiesUtils.class);
	private static Properties pro = null;
	public static void loadProperties(String config) {
		try {
			if(pro==null){
				pro=new Properties();
			}
			pro.load(new FileInputStream(config));
		} catch (FileNotFoundException e) {
			log.warn("IOException", e);
			e.printStackTrace();
		} catch (IOException e) {
			log.warn("IOException", e);
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
	
}
>>>>>>> d64a92cd44c1cd1279e7c9921940f7cb9d860b60
