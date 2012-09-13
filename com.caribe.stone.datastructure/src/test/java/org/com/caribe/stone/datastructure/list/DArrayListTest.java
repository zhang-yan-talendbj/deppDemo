package org.com.caribe.stone.datastructure.list;

import static org.junit.Assert.*;

import javax.management.InstanceAlreadyExistsException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DArrayListTest {

	private static final String GOLD_COIN = "gold coin1";
	private DListArray list;
	private Object rmb;

	@Before
	public void setup() {
		list = new DListArray();
	}

	@After
	public void teardown() {
		list = null;
	}

	@Test
	public void testGetListSize() throws DOutOfBoundaryException {
		addOneElementToList();
		listSizeShouldBe(1);
		listShouldNotBeEmpty();
	}

	@Test
	public void testEmptyList() throws Exception {
		getAnEmptyList();
		listShouldBeEmpty();
	}

	@Test
	public void testInsertElementIntoList() throws Exception {
		insertAnElementAtIndex(1, "one");
		insertAnElementAtIndex(2, "two");
		insertAnElementAtIndex(1, "zero");

		listShouldHasThreeElement();
	}

	@Test(expected = DOutOfBoundaryException.class)
	public void testInsertAtZero() throws Exception {
		insertAnElementAtIndex(0, "zero");
	}
	
	@Test(expected=DOutOfBoundaryException.class)
	public void testRemoveOutOfBoundary() throws Exception {
		removeIndexZero();
	}
	
	private void removeIndexZero() throws DOutOfBoundaryException {
		list.remove(0);
	}

	@Test(expected = DOutOfBoundaryException.class)
	public void testInsertIndexGreaterThenSize() throws Exception {
		insertAnElementAtIndex(2, "two");
	}
	
	@Test
	public void testInsertNull() throws Exception {
		insertAnElementAtIndex(1, null);
		listSizeShouldBe(1);
	}

	@Test
	public void testRemoveElementWithIndex() throws Exception {
		givenAListWithThreeElement();
		whenRemoveTheSecondElement();
		thenListSizeIsTwo();
		thenGetSecondElementIsThree();
		try {
			thenGetThreeElementIsNull();
			fail("error.");
		} catch (Exception e) {
			assertTrue(e instanceof DOutOfBoundaryException);
		}
	}
	
	@Test
	public void testContains() throws Exception {
		givenAListWithGoldCoinAndRMB();
		thenContainGoldCoin();
		thenDonotContainDollar();
	}
	
	@Test
	public void testIndexOfList() throws Exception {
		givenAListWithThreeElement();
		thenOneIndexIsOne();
		thenTwoIndexIsTwo();
		thenOneHundredIsZero();
	}
	
	@Test
	public void testRemoveElementWithElement() throws Exception {
		givenAListWithThreeElement();
		whenRemoveOne();
		thenListSizeIsTwo();
	}
	
	@Test
	public void testInserBeforElement() throws Exception {
		givenAListWithThreeElement();
		whenInsertZeroBeforeOne();
		thenIndexOneIsZero();
	}
	@Test
	public void testInserAfterElement() throws Exception {
		givenAListWithThreeElement();
		whenInsertFourBeforeThree();
		thenIndexFourIsFour();
	}
	
	@Test
	public void testReplace() throws Exception {
		givenAListWithGoldCoinAndRMB();
		whenReplaceRMBToDollar();
		thenIWillHappy();
	}
	
	@Test
	public void testInsertManyElement() throws Exception {
		givenAListWithThreeElement();
		givenAListWithThreeElement();
		givenAListWithGoldCoinAndRMB();
		thenAddABook();
	}
	private void thenAddABook() throws DOutOfBoundaryException {
		list.insert(1, "book");
		
	}

	private void thenIWillHappy() throws DOutOfBoundaryException {
		assertEquals("RMB", rmb);
		assertEquals("dollar", list.get(2));
	}

	private void whenReplaceRMBToDollar() throws DOutOfBoundaryException {
		rmb=list.replace(2, "dollar");
	}

	private void thenIndexFourIsFour() throws DOutOfBoundaryException {
		assertEquals("four", list.get(4));
	}

	private void whenInsertFourBeforeThree() throws DOutOfBoundaryException {
		list.insertAfter("three", "four");
	}

	private void thenIndexOneIsZero() throws DOutOfBoundaryException {
		assertEquals("zero", list.get(1));
	}

	private void whenInsertZeroBeforeOne() throws DOutOfBoundaryException {
		list.insertBefore("one", "zero");
	}

	private void whenRemoveOne() throws DOutOfBoundaryException {
		list.remove("one");
	}

	private void thenOneHundredIsZero() {
		assertEquals(0, list.indexOf("100"));
	}

	private void thenTwoIndexIsTwo() {
		assertEquals(2, list.indexOf("two"));
	}

	private void thenOneIndexIsOne() {
		assertEquals(1, list.indexOf("one"));
	}

	private void thenDonotContainDollar() {
		assertFalse(list.contains("Dollar"));
	}

	private void thenContainGoldCoin() {
		assertTrue(list.contains(GOLD_COIN));
	}

	private void givenAListWithGoldCoinAndRMB() throws DOutOfBoundaryException {
		list.insert(1, GOLD_COIN);
		list.insert(2, "RMB");
	}

	private void thenGetThreeElementIsNull() throws DOutOfBoundaryException {
		assertEquals(null, list.get(3));
	}

	private void thenGetSecondElementIsThree() throws DOutOfBoundaryException {
		assertEquals("three", list.get(2));
	}

	private void thenListSizeIsTwo() {
		assertEquals(2, list.size());
	}

	private void whenRemoveTheSecondElement() throws DOutOfBoundaryException {
		list.remove(2);
	}

	private void givenAListWithThreeElement() throws DOutOfBoundaryException {
		insertAnElementAtIndex(1, "one");
		insertAnElementAtIndex(2, "two");
		insertAnElementAtIndex(3, "three");
	}

	private void listShouldHasThreeElement() {
		assertEquals(3, list.size());
	}

	private void insertAnElementAtIndex(int index, Object e) throws DOutOfBoundaryException {
		list.insert(index, e);
	}

	private void listShouldBeEmpty() {
		assertEquals(true, list.isEmpty());
	}

	private void getAnEmptyList() {
		list = new DListArray(0);
	}

	private void listShouldNotBeEmpty() {
		assertEquals(false, list.isEmpty());
	}

	private void listSizeShouldBe(int i) {
		assertEquals(i, list.size());
	}

	private void addOneElementToList() throws DOutOfBoundaryException {
		list.insert(1, "one");
	}

}
