package org.com.caribe.stone.datastructure.list;

public class Node {

	private Object item;
	private Node next;

	public Object getItem() {
		return item;
	}

	public void setItem(Object item) {
		this.item = item;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	public Node() {
		this(null, null);
	}

	public Node(Object item, Node next) {
		this.item = item;
		this.next = next;
	}

}
