package adt;

public class DoubleLinkedList<T> {
	private Node<T> head;
	private Node<T> last;
	private int size;
	
	public DoubleLinkedList() {
		head = null;
		last = null;
		size = 0;
	}
	
	public void add(T value) {
		Node<T> newNode = new Node<T>();
		newNode.setValue(value);
		if(head == null && last == null) {
			head = newNode;
			last = newNode;
			++size;
		} else {
		last.setNext(newNode);
		last = newNode;
		size++;
		}
	}
	
	public void remove(int index) {
		if(index == 0 && index < size) {
			head = head.getNext();
			size--;
			return;
		}
		Node<T> current = head;
		int counter = 0;
		while(counter < index-1 && current.getNext() != null) {
			current = current.getNext();
			counter++;
		}
		if(counter == size-2) {
			current.setNext(null);
			size--;
			return;
		} else {
			current.setNext(current.getNext().getNext());
			size--;
			return;
		}
	}
	
	public T get(int index) {
		if(index > size-1)
			return null;
		if(index == size-1)
			return last.getValue();
		Node<T> current = head;
		for(int i = 0; i < index; i++) {
			current = current.getNext();
		}
		return current.getValue();
	}
	
	public int size() {
		return size;
	}
	
	public void clear() {
		this.head = null;
		this.last = null;
		this.size = 0;
	}
}
