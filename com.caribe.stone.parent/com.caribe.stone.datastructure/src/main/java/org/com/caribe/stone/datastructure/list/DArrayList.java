package org.com.caribe.stone.datastructure.list;

import java.util.Arrays;

public class DArrayList implements DList {

	private int size = 0;
	private static int MAX_SIZE = 8;
	Object[] array = null;

	public DArrayList(int size) {
		array = new Object[size];
	}

	public DArrayList() {
		this(MAX_SIZE);
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public boolean contains(Object e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int indexOf(Object e) {
		if (e != null) {
			for (int i = 0; i < size; i++) { // 数组下标和size差1,用>可补全
				if (e.equals(array[i]))
					return i+1;
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
			throw new DOutOfBoundaryException("Error, out of boundary!");
		}
		if (e != null) {
			if (array[index - 1] != null) {
				for (int i = size; i > index - 1; i--) {
					// for 循环相当于数轴上的区间，i=size是从最后一个元素的后一个元素开始，到index前一位
					// 因为index是从1开始的，和数组下标差1，so i>index-1
					// 也可以从最后一个元素开始，到index元素，array[i+1]=array[i]
					array[i] = array[i - 1];
				}
			}
			array[index - 1] = e;
		}
		size++;
	}

	@Override
	public boolean insertBefore(Object obj, Object e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertAfter(Object obj, Object e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object remove(int index) throws DOutOfBoundaryException {
		if (index <= 0 || index > size) {
			throw new DOutOfBoundaryException("Error,out of boundary.");
		}
		Object e = null;
		e = array[index - 1];
		for (int i = index - 1; i < size - 1; i++) {
			// from index to size-1
			array[i] = array[i + 1];
		}
		array[--size] = null;// last element is null
		return e;
	}

	@Override
	public boolean remove(Object e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object replace(int i, Object e) throws DOutOfBoundaryException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object get(int i) throws DOutOfBoundaryException {
		if (array[i - 1] != null) {
			return array[i - 1];
		}
		throw new DOutOfBoundaryException("Error,out of boundary.");
	}

	@Override
	public String toString() {
		return "DArrayList [array=" + Arrays.toString(array) + "]";
	}

}
