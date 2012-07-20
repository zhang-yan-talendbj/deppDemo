package org.com.caribe.stone.datastructure.list;

public class DLNode implements Node {

	private Object item;
	private DLNode pre;
	private DLNode next;

	public DLNode(Object item, DLNode pre, DLNode next) {
		this.item = item;
		this.pre = pre;
		this.next = next;
	}

	public DLNode() {
		this(null,null,null);
	}

	public DLNode getPre() {
		return pre;
	}

	public void setPre(DLNode pre) {
		this.pre = pre;
	}

	public DLNode getNext() {
		return next;
	}

	public void setNext(DLNode next) {
		this.next = next;
	}

	@Override
	public Object getItem() {
		return item;
	}

	@Override
	public void setItem(Object item) {
		this.item = item;
	}

}
