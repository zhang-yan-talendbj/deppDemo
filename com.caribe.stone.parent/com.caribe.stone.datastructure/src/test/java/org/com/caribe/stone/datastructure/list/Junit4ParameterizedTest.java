package org.com.caribe.stone.datastructure.list;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class Junit4ParameterizedTest {

	@Parameters
	public static Collection data() {
		return Arrays.asList(new Object[][] { { 2, 4 }, { 0, 0 }, { -3, 9 }, });
	}

	private int j;
	private int i;

	@Test
	public void test() {
		assertEquals(1, 1);
	}

	public Junit4ParameterizedTest(int i,int j) {
		this.i=i;
		this.j=j;
	}

}
