package org.com.caribe.stone;


import org.junit.runner.RunWith;

import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;
import cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@Cucumber.Options(
		features={"src/test/resources/multString.feature"},
		format = {"pretty", "html:target/cucumber"})
public class RunCukesTest {

}
