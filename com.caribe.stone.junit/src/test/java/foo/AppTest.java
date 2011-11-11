package foo;

import junit.framework.Test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 * Unit test for simple App.
 */
public class AppTest {
	@BeforeClass
	public static void init() {
		System.out.println("init class.");
	}

	@Before
	public void setUp() {
		System.out.println("set up method~");
	}
	@After
	public void tearDown(){
		System.out.println("tearDown method~");
	}
	@AfterClass
	public static void destroy(){
		System.out.println("destroy method~");
	}

	@org.junit.Test
	public void testDH() {
		System.out.println("Hi,DH.");
	}

	@org.junit.Test
	public void testDK() {
		System.out.println("Hi,DK.");
	}
}
