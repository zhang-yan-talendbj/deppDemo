package com.caribe.stone.mockito;

import static org.mockito.Mockito.*;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;
import org.junit.Test;

public class MockitoDemo {
	@Test
	public void someBehaviour() throws Exception {
		// mock creation
		List mockedList = mock(List.class);

		// using mock object
		mockedList.add("one");
		mockedList.add("two");
		mockedList.add("two");
		mockedList.clear();

		// verification
		verify(mockedList).add("one");
		verify(mockedList, times(2)).add("two");
		verify(mockedList).clear();

		// Once created, mock will remember all interactions. Then you can
		// selectively verify whatever interaction you are interested in.
	}

	@Test
	public void concreteClasses() throws Exception {
		// You can mock concrete classes, not only interfaces
		LinkedList mockedList = mock(LinkedList.class);

		// stubbing
		when(mockedList.get(0)).thenReturn("first");
		when(mockedList.get(1)).thenThrow(new RuntimeException());

		assertEquals("first", mockedList.get(0));

		// following throws runtime exception
		try {
			System.out.println(mockedList.get(1));
			fail();
		} catch (Exception e) {

		}
		// following prints "null" because get(999) was not stubbed
		// By default, for all methods that return value, mock returns null, an
		// empty collection or appropriate primitive/primitive wrapper value
		// (e.g: 0, false, ... for int/Integer, boolean/Boolean, ...).
		assertNull(mockedList.get(999));
		// Although it is possible to verify a stubbed invocation, usually it's
		// just redundant
		// If your code cares what get(0) returns then something else breaks
		// (often before even verify() gets executed).
		// If your code doesn't care what get(0) returns then it should not be
		// stubbed. Not convinced? See here.
		verify(mockedList).get(0);

		// Stubbing can be overridden: for example common stubbing can go to
		// fixture setup but the test methods can override it. Please note that
		// overridding stubbing is a potential code smell that points out too
		// much stubbing
		// Once stubbed, the method will always return stubbed value regardless
		// of how many times it is called.
		when(mockedList.get(3)).thenReturn(1);
		assertEquals(1, mockedList.get(3));
		assertEquals(1, mockedList.get(3));
		assertEquals(1, mockedList.get(3));

		// Last stubbing is more important - when you stubbed the same method
		// with the same arguments many times.

	}

	@Test
	public void testName() throws Exception {
		List mockedList = mock(List.class);
		mockedList.add("once");

		mockedList.add("twice");
		mockedList.add("twice");

		mockedList.add("three times");
		mockedList.add("three times");
		mockedList.add("three times");

		// following two verifications work exactly the same - times(1) is used
		// by default
		verify(mockedList).add("once");
		verify(mockedList, times(1)).add("once");

		verify(mockedList, times(2)).add("twice");

		verify(mockedList, times(3)).add("three times");
	}
}
