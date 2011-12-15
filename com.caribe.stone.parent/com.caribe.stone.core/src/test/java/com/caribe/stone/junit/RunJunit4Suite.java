package com.caribe.stone.junit;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({})
public class RunJunit4Suite extends TestCase {
	public static TestSuite suite() {
		// add junit3 test
		TestSuite suite = new TestSuite();
		return suite;
	}
}