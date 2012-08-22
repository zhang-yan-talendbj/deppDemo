package com.caribe.stone.tdd.template;
import static org.junit.Assert.*;

import org.junit.Test;


public class TestExceptionTest {

	@Test
	public void testExceptionByTryCatch() {
		try{
			throwException();
			fail("This method should throw an exception.");
		}catch(Exception ex){
		}
	}

	@Test(expected=Exception.class)
	public void testExceptionByAnnotation() throws Exception {
		throwException();
	}
	
	public void throwException() throws Exception {
		throw new Exception("Only a test exception.");
	}
}
