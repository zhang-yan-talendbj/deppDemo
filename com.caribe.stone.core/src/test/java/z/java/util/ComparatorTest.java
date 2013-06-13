package z.java.util;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;

public class ComparatorTest {

	@Test
	public void test() {
		Comparator<Integer> compartor = new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o2.compareTo(o1);
			}
		};
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(400);
		list.add(5);
		Collections.sort(list, compartor);
		assertEquals(new Integer(400), list.get(0));
	}
}
