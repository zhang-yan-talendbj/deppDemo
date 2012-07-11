package org.com.caribe.stone.datastructure.list;

import java.util.Arrays;

public class DArrayList implements DList {

	private int size = 0;
	private static int MAX_SIZE = 20;
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void insert(int i, Object e) throws DOutOfBoundaryException {
		if (i < 0 || i >= MAX_SIZE) {
			throw new DOutOfBoundaryException("Error, out of boundary!");
		}
		if (e != null) {
			if (array[i - 1] != null) {
				for (int j = array.length - 1; j > i - 1; j--) {
					if (array[j] != null) {
						array[j + 1] = array[j];
					}
				}
			}
			array[i-1]=e;
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
	public Object remove(int i) throws DOutOfBoundaryException {
		// TODO Auto-generated method stub
		return null;
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
		if (array[i-1] != null) {
			return array[i-1];
		}
		throw new DOutOfBoundaryException("Error,out of boundary.");
	}

	@Override
	public String toString() {
		return "DArrayList [array=" + Arrays.toString(array) + "]";
	}

}
