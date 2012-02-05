package com.caribe.stone.mockito;

import static org.mockito.Mockito.*;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;
import org.junit.Test;

public class MockitoDemo {
	@Test
	public void easyMock() throws Exception {
		// mock creation
		List mockedList = mock(List.class);

		// using mock object
		mockedList.add("one");
		mockedList.add("two");
		mockedList.clear();

		// verification
		verify(mockedList).add("one");
		verify(mockedList).add("two");
		verify(mockedList).clear();
	}
	
	@Test
	public void concreteClasses() throws Exception {
		//You can mock concrete classes, not only interfaces
		LinkedList mockedList = mock(LinkedList.class);
		 
		//stubbing
		when(mockedList.get(0)).thenReturn("first");
		when(mockedList.get(1)).thenThrow(new RuntimeException());
		 
		//following prints "first"
		System.out.println(mockedList.get(0));
		 
		//following throws runtime exception
		try {
			System.out.println(mockedList.get(1));
			fail();
		} catch (Exception e) {
			
		}
		//following prints "null" because get(999) was not stubbed
		Object object = mockedList.get(999);
		assertNull(object);
		//Although it is possible to verify a stubbed invocation, usually it's just redundant
		//If your code cares what get(0) returns then something else breaks (often before even verify() gets executed).
		//If your code doesn't care what get(0) returns then it should not be stubbed. Not convinced? See here.
		verify(mockedList).get(0);
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
		 
		//following two verifications work exactly the same - times(1) is used by default
		verify(mockedList).add("once");
		verify(mockedList, times(1)).add("once");

		verify(mockedList, times(2)).add("twice");

		verify(mockedList, times(3)).add("three times");
	}
}
