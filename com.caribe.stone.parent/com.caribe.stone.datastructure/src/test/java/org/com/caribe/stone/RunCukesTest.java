package org.com.caribe.stone;


import org.junit.runner.RunWith;

import cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@Cucumber.Options(
		features={"src/test/resources/test.feature"}
//		,format = {"pretty", "html:target/cucumber"}
		)
public class RunCukesTest {
}
