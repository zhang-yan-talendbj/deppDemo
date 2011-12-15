package com.caribe.stone.junit;
import NotificationTest;
import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;
import junit.framework.TestSuite;


public class Junit3SuiteDemo {

	public static Test suite() {
		JUnit4TestAdapter ad=new JUnit4TestAdapter(NotificationTest.class);
		TestSuite ts=new TestSuite();
		ts.addTest(ad);
		ts.addTest(ad);
		return ts;
	}
}
