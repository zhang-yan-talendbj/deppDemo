package org.com.caribe.stone.datastructure.list;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DLinkedListTest {

	private DLinkedListDLNode list;

	@Before
	public void setup() {
		list = new DLinkedListDLNode();
	}

	@Test(expected = DOutOfBoundaryException.class)
	public void testFirstNodeException() throws DOutOfBoundaryException {
		list.first();
	}

	@Test(expected = DOutOfBoundaryException.class)
	public void testLastNodeException() throws DOutOfBoundaryException {
		list.last();
	}

	@Test
	public void testInsertFirst() throws Exception {
		Node first = list.insertFirst("one");
		assertEquals("one", first.getItem());
		assertEquals(1, list.size());
		assertSame(first, list.first());
	}

	@Test
	public void testInsertLast() throws Exception {
		Node last = list.insertLast("last");
		assertEquals("last", last.getItem());
		assertEquals(1, list.size());
		assertFalse(list.isEmpty());
		assertSame(last, list.last());
	}

	@Test
	public void testInsertBefore() throws Exception {
		Node first = list.insertFirst("one");
		Node zero = list.insertBefore(first, "zero");
		Node n1 = list.insertBefore(first, "-1");
		assertEquals("-1", n1.getItem());
		assertEquals("zero", zero.getItem());
		assertEquals(3, list.size());
	}

	@Test
	public void testInsertAfter() throws Exception {
		Node first = list.insertFirst("one");
		Node two = list.insertAfter(first, "two");
		assertEquals(2, list.size());
	}

	@Test
	public void testRemoveFirst() throws Exception {
		list.insertFirst("one");
		Object first = list.removeFirst();
		assertEquals("one", first);
		assertEquals(0, list.size());
	}

	@Test
	public void testRemoveLast() throws Exception {
		list.insertFirst("one");
		Object first = list.removeLast();
		assertEquals("one", first);
		assertEquals(0, list.size());
	}

	@Test
	public void testReplace() throws Exception {
		Node first = list.insertFirst("one");
		Object one = list.replace(first, "zero");
		assertEquals("one", one);
	}

	@Test
	public void testRemove() throws Exception {
		Node first = list.insertFirst("one");
		Node zero = list.insertBefore(first, "zero");
		list.insertAfter(first, "two");

		assertEquals(3, list.size());
		list.remove(first);
		assertEquals(2, list.size());
		assertEquals("zero", list.first().getItem());
		assertEquals("two", list.last().getItem());
	}
	
	@Test
	public void testPreNodeAndNextNode() throws Exception {
		Node first = list.insertFirst("one");
		Node zero = list.insertBefore(first, "zero");
		list.insertAfter(first, "two");
		Node first2 = list.first();
		assertEquals("one", list.getNext(first2).getItem());
		
		assertEquals("two", list.getNext(list.getNext(first2)).getItem());
	}

}
