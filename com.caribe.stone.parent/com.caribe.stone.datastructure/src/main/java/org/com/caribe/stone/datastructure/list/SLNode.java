package org.com.caribe.stone.datastructure.list;

public class SLNode implements Node {

	private Object item;
	private SLNode next;

	/* (non-Javadoc)
	 * @see org.com.caribe.stone.datastructure.list.Node#getItem()
	 */
	@Override
	public Object getItem() {
		return item;
	}

	/* (non-Javadoc)
	 * @see org.com.caribe.stone.datastructure.list.Node#setItem(java.lang.Object)
	 */
	@Override
	public void setItem(Object item) {
		this.item = item;
	}

	public SLNode getNext() {
		return next;
	}

	public void setNext(SLNode next) {
		this.next = next;
	}

	public SLNode() {
		this(null, null);
	}

	public SLNode(Object item, SLNode next) {
		this.item = item;
		this.next = next;
	}

}
