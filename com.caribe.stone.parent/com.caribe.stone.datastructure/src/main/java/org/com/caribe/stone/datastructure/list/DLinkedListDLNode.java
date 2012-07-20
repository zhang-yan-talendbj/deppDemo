package org.com.caribe.stone.datastructure.list;

public class DLinkedListDLNode implements DLinkedList {

	private DLNode head;
	private DLNode tail;
	private int size;

	@Override
	public int size() {
		return size;
	}

	public DLinkedListDLNode() {
		head = new DLNode();
		tail = new DLNode();
		size = 0;
		head.setNext(tail);
		tail.setPre(head);
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Node first() throws DOutOfBoundaryException {
		if (isEmpty()) {
			throw new DOutOfBoundaryException();
		}
		return head.getNext();
	}

	@Override
	public Node last() throws DOutOfBoundaryException {
		if (isEmpty()) {
			throw new DOutOfBoundaryException();
		}
		return tail.getPre();
	}

	@Override
	public Node getNext(Node p) throws InvalidNodeException, DOutOfBoundaryException {
		DLNode node = checkPosition(p);
		DLNode next = node.getNext();
		if(next==tail){
			throw new DOutOfBoundaryException();
		}
		return next;
	}

	@Override
	public Node getPre(Node p) throws InvalidNodeException, DOutOfBoundaryException {
		DLNode node = checkPosition(p);
		DLNode pre = node.getPre();
		if(pre==head){
			throw new DOutOfBoundaryException();
		}
		return pre;
	}

	@Override
	public Node insertFirst(Object e) {
		DLNode node = new DLNode(e, head, head.getNext());
		head.getNext().setPre(node);
		head.setNext(node);
		size++;
		return node;
	}

	@Override
	public Node insertLast(Object e) {
		DLNode node = new DLNode(e, tail.getPre(), tail);
		tail.getPre().setNext(node);
		tail.setPre(node);
		size++;
		return node;
	}

	@Override
	public Node insertAfter(Node p, Object e) throws InvalidNodeException {
		DLNode node = checkPosition(p);
		DLNode next = node.getNext();
		DLNode newNode = new DLNode(e, node, next);
		next.setPre(newNode);
		node.setNext(newNode);
		size++;
		return newNode;
	}

	public DLNode checkPosition(Node p) throws InvalidNodeException {
		if (p == null)
			throw new InvalidNodeException("错误：p为空。");
		if (p == head)
			throw new InvalidNodeException("错误：p指向头节点，非法。");
		if (p == tail)
			throw new InvalidNodeException("错误：p指向尾结点，非法。");
		DLNode node = (DLNode) p;
		return node;
	}

	@Override
	public Node insertBefore(Node p, Object e) throws InvalidNodeException {
		DLNode node = checkPosition(p);
		DLNode pre = node.getPre();
		DLNode newNode = new DLNode(e, pre, node);
		pre.setNext(newNode);
		node.setPre(newNode);
		size++;
		return newNode;
	}

	@Override
	public Object remove(Node p) throws InvalidNodeException {
		DLNode node = checkPosition(p);
		DLNode pre = node.getPre();
		DLNode next = node.getNext();
		pre.setNext(next);
		next.setPre(pre);
		size--;
		return node.getItem();
	}

	@Override
	public Object removeFirst() throws DOutOfBoundaryException {
		if (isEmpty()) {
			throw new DOutOfBoundaryException();
		}
		DLNode first = head.getNext();
		DLNode next = first.getNext();
		head.setNext(next);
		next.setPre(head);
		size--;
		return first.getItem();
	}

	@Override
	public Object removeLast() throws DOutOfBoundaryException {
		if (isEmpty()) {
			throw new DOutOfBoundaryException();
		}
		DLNode last = tail.getPre();
		DLNode pre = last.getPre();
		pre.setNext(tail);
		tail.setPre(pre);
		size--;
		return last.getItem();
	}

	@Override
	public Object replace(Node p, Object e) throws InvalidNodeException {
		DLNode node = checkPosition(p);
		Object obj = node.getItem();
		node.setItem(e);
		return obj;
	}

	@Override
	public Iterator elements() {
		return null;
	}

}
