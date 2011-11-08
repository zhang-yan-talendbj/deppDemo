<<<<<<< HEAD
package foo.util;

import static org.junit.Assert.*;

import java.io.File;
import java.net.URL;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertiesUtilsTest {

	@Test
	public void test() {
		String resource = this.getClass().getResource("/")+"test-config.properties";
		File f=new File(resource.substring(5,resource.length()));
		System.out.println(resource);
		System.out.println(f.getAbsolutePath());
		System.out.println(f.getPath());
		System.out.println(f.exists());
		
		Logger log = LoggerFactory.getLogger(PropertiesUtilsTest.class);
		log.info("");
//		PropertiesUtils.loadProperties("test-config.properties");
		
	}

}
=======
package foo.util;

import static org.junit.Assert.*;

import java.io.File;
import java.net.URL;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertiesUtilsTest {

	@Test
	public void test() {
		String resource = this.getClass().getResource("/")+"test-config.properties";
		File f=new File(resource.substring(5,resource.length()));
		System.out.println(resource);
		System.out.println(f.getAbsolutePath());
		System.out.println(f.getPath());
		System.out.println(f.exists());
		
		Logger log = LoggerFactory.getLogger(PropertiesUtilsTest.class);
		log.info("");
//		PropertiesUtils.loadProperties("test-config.properties");
		
	}

}
>>>>>>> d64a92cd44c1cd1279e7c9921940f7cb9d860b60
