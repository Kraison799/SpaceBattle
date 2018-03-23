package adt;

public class List <T> {
	private Node<T> head;
	private int size;
	
	public List() {
		head = null;
		size = 0;
	}

	public void add(T value) {
		Node<T> newNode = new Node<T>();
		newNode.setValue(value);
		if(head == null) {
			head = newNode;
			size++;
			return;
		}
		Node<T> current = head;
		while(current.getNext() != null) {
			current = current.getNext();
		}
		current.setNext(newNode);
		size++;
	}
	
	public void remove(T value) {
		if(head != null && head.getValue() == value) {
			head = head.getNext();
			size--;
		}
		Node<T> current = new Node<T>();
		while(current.getNext() != null && current.getNext().getValue() != value) {
			current = current.getNext();
		}
		if(current.getNext().getValue() == value) {
			current.setNext(current.getNext().getNext());
			size--;
		}
	}
	
	public T get(int index) {
		if(index > size-1)
			return null;
		Node<T> current = head;
		for(int i = 0; i != index; i++) {
			current = current.getNext();
		}
		return current.getValue();
	}
	
	public int size() {
		return size;
	}
}