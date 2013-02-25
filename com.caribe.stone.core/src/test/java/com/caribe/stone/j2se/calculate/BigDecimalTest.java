package com.caribe.stone.j2se.calculate;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;


public class BigDecimalTest {

	@Test
	public void add() throws Exception {
		BigDecimal b1 = new BigDecimal(200);
		BigDecimal b2 = new BigDecimal(100);
		assertEquals(new BigDecimal("300"),b1.add(b2));
		assertEquals(new BigDecimal("100"),b1.subtract(b2));
		assertEquals(new BigDecimal("20000"),b1.multiply(b2));
		assertEquals(new BigDecimal("2"),b1.divide(b2));
		
		BigDecimal b3 = new BigDecimal("2.0002");
		BigDecimal b4 = new BigDecimal(100);
		assertEquals(new BigDecimal("0.020002"),b3.divide(b4,6,BigDecimal.ROUND_HALF_UP));
		
		System.out.println(new BigDecimal(10).divide(new BigDecimal(22211),3));;
	}
}
