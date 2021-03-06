package org.com.caribe.stone.datastructure.list;

import java.util.Arrays;

public class DListArray implements DList {

	private int size = 0;
	private static int MAX_SIZE = 8;
	Object[] array = null;

	public DListArray(int size) {
		array = new Object[size];
	}

	public DListArray() {
		this(MAX_SIZE);
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public boolean contains(Object e) {
		if (e == null) {
			return false;
		}
		for (int i = 0; i < size; i++) {
			if (e.equals(array[i])) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int indexOf(Object e) {
		if (e != null) {
			for (int i = 0; i < size; i++) { // 数组下标和size差1,用<可补全
				if (e.equals(array[i]))
					return i + 1;
			}
		}
		return 0;
	}

	/**
	 * index为常数，从1开始
	 */
	@Override
	public void insert(int index, Object e) throws DOutOfBoundaryException {
		if (index <= 0 || index > size + 1) {
			throw new DOutOfBoundaryException();
		}

		if (size == MAX_SIZE) {
			expandSpace();
		}

		if (array[index - 1] != null) {
			for (int i = size; i >=index; i--) {
				// for 循环相当于数轴上的区间，i=size是从最后一个元素（size）的后一个元素开始，到index,i>=index
				// 因为index是从1开始的，和数组下标差1，so 最后一个元素（size）的后一个元素开始从size开始
				//index后一位-> i>=index
				array[i] = array[i - 1];
				// 也可以从i之后元素开始赋值为i，array[i+1]=array[i]
			}
		}
		array[index - 1] = e;
		size++;
	}

	private void expandSpace() {
		Object[] newArray = new Object[array.length * 3 / 2];
		for (int i = 0; i < array.length; i++) {
			newArray[i] = array[i];
		}
		array = newArray;
	}

	@Override
	public boolean insertBefore(Object obj, Object e) throws DOutOfBoundaryException {
		int index = indexOf(obj);
		if (index < 0) {
			return false;
		}
		insert(index, e);
		return true;
	}

	@Override
	public boolean insertAfter(Object obj, Object e) throws DOutOfBoundaryException {
		int index = indexOf(obj);
		if (index < 0) {
			return false;
		}
		insert(index + 1, e);
		return true;
	}

	@Override
	public Object remove(int index) throws DOutOfBoundaryException {
		if (index <= 0 || index > size) {
			throw new DOutOfBoundaryException();
		}
		Object e = null;
		e = array[index - 1];
		for (int i = index - 1; i < size - 1; i++) {
			//index,size 和数组下标差1
			// from index-1 to size-2
			array[i] = array[i + 1];
		}
		array[--size] = null;// last element is null
		return e;
	}

	@Override
	public boolean remove(Object e) throws DOutOfBoundaryException {
		int index = indexOf(e);
		if (index > 0) {
			remove(index);
			return true;
		}
		return false;
	}

	@Override
	public Object replace(int i, Object e) throws DOutOfBoundaryException {
		if (i <= 0 || i > size) {
			throw new DOutOfBoundaryException();
		}
		Object obj = array[i - 1];
		array[i - 1] = e;
		return obj;
	}

	@Override
	public Object get(int i) throws DOutOfBoundaryException {
		if (i <= 0 || i > size) {
			throw new DOutOfBoundaryException();
		}
		return array[i - 1];
	}

	@Override
	public String toString() {
		return "DListArray [array=" + Arrays.toString(array) + "]";
	}
}
