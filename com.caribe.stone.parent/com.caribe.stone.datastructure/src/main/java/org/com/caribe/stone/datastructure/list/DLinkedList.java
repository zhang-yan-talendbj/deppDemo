package org.com.caribe.stone.datastructure.list;

public class DLinkedList implements DList {
	private int size;
	private Node header;

	public DLinkedList() {
		header=new Node(null, null);
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size == 0;
	}

	@Override
	public boolean contains(Object e) {
		return indexOf(e) != 0;
	}

	@Override
	public int indexOf(Object e) {
		Node p = header;
		int index = 0;
		while (p != null && index < size) {
			p = p.getNext();
			index++;
			if (e.equals(p.getItem())) {
				return index;
			}
		}
		return 0;
	}

	@Override
	public void insert(int index, Object e) throws DOutOfBoundaryException {
		checkIndex(index);
		Node p = getNode(index-1);
		Node node2 = new Node(e, p.getNext());
		p.setNext(node2);
		size++;
	}

	@Override
	public boolean insertBefore(Object obj, Object e) throws DOutOfBoundaryException {
		int index = indexOf(obj);
		if (index > 0) {
			insert(index, e);
			return true;
		}
		return false;
	}

	@Override
	public boolean insertAfter(Object obj, Object e) throws DOutOfBoundaryException {
		int index = indexOf(obj);
		if (index > 0) {
			insert(index + 1, e);
			return true;
		}
		return false;
	}

	@Override
	public Object remove(int index) throws DOutOfBoundaryException {
		checkIndex(index);
		int j = 0;
		Node p = header;
		while (j < index - 1) {
			p = p.getNext();
			j++;
		}
		Node q = p.getNext();
		p.setNext(q.getNext());
		size--;
		return q.getItem();
	}

	private void checkIndex(int i) throws DOutOfBoundaryException {
		if (i < 1 || i > size + 1) {
			throw new DOutOfBoundaryException();
		}
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
	public Object replace(int index, Object e) throws DOutOfBoundaryException {
		if (index < 0 || index > size) {
			throw new DOutOfBoundaryException();
		}
		Node p = getNode(index);
		Object obj = p.getItem();
		p.setItem(e);
		return obj;
	}

	private Node getNode(int index) throws DOutOfBoundaryException {
		int j = 0;
		Node p = header;
		while (p != null && j < index) {
			p = p.getNext();
			j++;
		}
		return p;
	}

	@Override
	public Object get(int index) throws DOutOfBoundaryException {
		if (index < 1 || index > size) {
			throw new DOutOfBoundaryException();
		}
		Node p = getNode(index);
		return p.getItem();
	}


	@Override
	public String toString() {
		return "DLinkedList [size=" + size + ", header=" + header + "]";
	}

}
